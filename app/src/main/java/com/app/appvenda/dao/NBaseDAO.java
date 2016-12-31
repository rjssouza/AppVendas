package com.app.appvenda.dao;

import android.content.Context;

import com.app.bdframework.baseEntidade.Entidade;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.conversor.ConversorHelper;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.TratamentoExcecao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Robson on 27/11/2016.
 */

abstract class NBaseDAO<TModelo, TEntidade extends Entidade> {

    protected Context context;
    private int qtdThreads = 0;
    private ExecutorService executor;
    protected Repositorio<TEntidade> repositorio;
    private EventoVoid<Boolean> eventoPosExecucao;
    private Class<TEntidade> pEntidade;
    private boolean sucesso = true;

    NBaseDAO(Context context, Class<TEntidade> pEntidade) {
        this.context = context;
        this.repositorio = obterRepositorio(context);
        this.pEntidade = pEntidade;
        this.executor = Executors.newFixedThreadPool(5);
    }

    public void salvar(final TModelo tModelo, final String[] regrasIgnorar) {
        qtdThreads++;
        this.executor.submit(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    try {
                        //repositorio.createTransaction();
                        TEntidade tEntidade = ConversorHelper.converterDePara(tModelo);
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
            }
        });
    }

    public void deletar(final TModelo tModelo, final String[] regrasIgnorar) {
        qtdThreads++;
        this.executor.submit(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    try {
                        //repositorio.createTransaction();
                        TEntidade tEntidade = ConversorHelper.converterDePara(tModelo);
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
            }
        });
    }

    public List<TEntidade> getLista(String queryString, String[] queryArg) {
        return this.repositorio.executarQuery(TEntidade.getTodasColunas(pEntidade), queryString, queryArg);
    }

    public TEntidade getUnico(String queryString, String[] queryArg) {
        return this.repositorio.executarUnico(TEntidade.getTodasColunas(pEntidade), queryString, queryArg);
    }

    public int getScalar(String queryString, String[] queryArg) {
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
