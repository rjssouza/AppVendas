package com.app.appvenda.dao;

import android.content.Context;

import com.app.bdframework.baseEntidade.Entidade;
import com.app.bdframework.baseEntidade.ParCampoValor;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.conversor.ConversorHelper;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Robson on 27/11/2016.
 */

abstract class BaseDAO<TModelo, TEntidade extends Entidade> {

    protected Context context;
    protected Repositorio<TEntidade> repositorio;
    private int qtdThreads = 0;
    private ExecutorService executor;
    private EventoVoid<Boolean> eventoPosExecucao;
    private Class<TEntidade> pEntidade;
    private Class<TModelo> pModelo;
    private boolean sucesso = true;

    BaseDAO(Context context, Class<TEntidade> pEntidade, Class<TModelo> pModelo) {
        this.context = context;
        this.pEntidade = pEntidade;
        this.pModelo = pModelo;
        this.repositorio = obterRepositorio(context);
        this.executor = Executors.newFixedThreadPool(5);
    }

    public void salvar(final TModelo tModelo, final String... regrasIgnorar) {
        final TEntidade tEntidade = ConversorHelper.converterDePara(tModelo, pEntidade);
        salvar(tEntidade, regrasIgnorar);
    }

    public void salvar(final TEntidade tEntidade, final String... regrasIgnorar) {
        qtdThreads++;
        this.executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //repositorio.createTransaction();
                    repositorio.salvar(tEntidade, regrasIgnorar);
                    if (tEntidade != null)
                        posSalvar(tEntidade, regrasIgnorar);
                } catch (RegraNegocioException e) {
                    sucesso = false;
                    TratamentoExcecao.registrarRegraNegocioExcecao(e);
                } catch (Exception e) {
                    sucesso = false;
                    TratamentoExcecao.registrarExcecao(e);
                } finally {
                    //repositorio.endTransaction();
                    eventoFinal();
                    TratamentoExcecao.invocarEvento();
                }
            }
        });
    }

    public void deletar(final TModelo tModelo, final String... regrasIgnorar) {
        TEntidade tEntidade = ConversorHelper.converterDePara(tModelo, pEntidade);
        deletar(tEntidade, regrasIgnorar);
    }

    public void deletar(final TEntidade tEntidade, final String... regrasIgnorar) {
        qtdThreads++;
        this.executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //repositorio.createTransaction();
                    if (tEntidade != null)
                        preDeletar(tEntidade, regrasIgnorar);
                    repositorio.deletar(tEntidade, regrasIgnorar);
                } catch (RegraNegocioException e) {
                    sucesso = false;
                    TratamentoExcecao.registrarRegraNegocioExcecao(e);
                } catch (Exception e) {
                    sucesso = false;
                    TratamentoExcecao.registrarExcecao(e);
                } finally {
                    //repositorio.endTransaction();
                    eventoFinal();
                    TratamentoExcecao.invocarEvento();
                }
            }
        });
    }

    protected List<TEntidade> getLista(String queryString, boolean complementaEntidade, String... queryArg) {
        return this.repositorio.executarQuery(TEntidade.getTodasColunas(pEntidade), queryString, complementaEntidade, queryArg);
    }

    protected List<TEntidade> getLista(String queryString, String... queryArg) {
        return this.repositorio.executarQuery(TEntidade.getTodasColunas(pEntidade), queryString, queryArg);
    }

    protected TEntidade getUnico(String queryString, boolean complementaEntidade, String... queryArg) {
        return this.repositorio.executarUnico(TEntidade.getTodasColunas(pEntidade), queryString, complementaEntidade, queryArg);
    }

    protected TEntidade getUnico(String queryString, String... queryArg) {
        return this.repositorio.executarUnico(TEntidade.getTodasColunas(pEntidade), queryString, false, queryArg);
    }

    public TModelo obterPorID(Long id, Boolean complementaEntidade) {
        ParCampoValor chavePrimaria = TEntidade.getChavePrimaria(pEntidade);
        TEntidade _entidade = getUnico(chavePrimaria.getNomeCampo() + " = ?", complementaEntidade, new String[]{id == null ? "" : id.toString()});
        TModelo tModelo = ConversorHelper.converterParaDe(_entidade, pModelo);
        return tModelo;
    }

    public int getScalar(String queryString, String... queryArg) {
        return this.repositorio.executarScalar(queryString, queryArg);
    }

    public void setEventoPosExecucao(EventoVoid<Boolean> eventoPosExecucao) {
        this.eventoPosExecucao = eventoPosExecucao;
    }

    public void salvarBD() {
        this.repositorio.salvarBDLocal();
    }

    protected abstract void posSalvar(TEntidade tEntidade, String[] regrasIgnorar) throws RegraNegocioException, Exception;

    protected abstract void preDeletar(TEntidade tEntidade, String[] regrasIgnorar) throws RegraNegocioException, Exception;

    protected abstract Repositorio<TEntidade> obterRepositorio(Context context);

    protected void setRegraNegociosSalvar(RegraNegocio<TEntidade> regraNegocio) {
        this.repositorio.setRegraNegociosSalvar(regraNegocio);
    }

    protected void setRegraNegociosDeletar(RegraNegocio<TEntidade> regraNegocio) {
        this.repositorio.setRegraNegociosDeletar(regraNegocio);
    }

    private synchronized void eventoFinal() {
        qtdThreads--;
        if (qtdThreads <= 0) {
            if (eventoPosExecucao != null) {
                try {
                    eventoPosExecucao.executarEvento(sucesso);
                } catch (Exception e) {
                    TratamentoExcecao.registrarExcecao(e);
                } finally {
                    TratamentoExcecao.invocarEvento();
                }
            }
        }
    }

}
