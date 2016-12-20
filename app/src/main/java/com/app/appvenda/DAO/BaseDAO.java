package com.app.appvenda.DAO;

import android.content.Context;

import com.app.bdframework.baseEntidade.Entidade;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.conversor.ConversorHelper;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.TratamentoExcecao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Robson on 27/11/2016.
 */

abstract class BaseDAO<TModelo, TEntidade extends Entidade> {

    protected Context context;
    private int qtdThreads = 0;
    private ExecutorService executor;
    protected Repositorio<TEntidade> repositorio;
    private EventoVoid<Boolean> eventoPosExecucao;
    private Class<TEntidade> pEntidade;

    BaseDAO(Context context, Class<TEntidade> pEntidade) {
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
                TEntidade tEntidade = ConversorHelper.converterDePara(tModelo);
                repositorio.salvar(tEntidade, regrasIgnorar);
                posSalvar(tEntidade, regrasIgnorar);
                eventoFinal();
            }
        });
    }

    public void deletar(final TModelo tModelo, final String[] regrasIgnorar) {
        qtdThreads++;
        this.executor.submit(new Runnable() {
            @Override
            public void run() {
                TEntidade tEntidade = ConversorHelper.converterDePara(tModelo);
                preDeletar(tEntidade, regrasIgnorar);
                repositorio.deletar(tEntidade, regrasIgnorar);
                eventoFinal();
            }
        });
    }

    public List<TEntidade> getLista(String queryString, String[] queryArg){
        return this.repositorio.executarQuery(TEntidade.getTodasColunas(pEntidade), queryString, queryArg);
    }

    public TEntidade getUnico(String queryString, String[] queryArg){
        return this.repositorio.executarUnico(TEntidade.getTodasColunas(pEntidade), queryString, queryArg);
    }

    public int getScalar(String queryString, String[] queryArg){
        return this.repositorio.executarScalar(queryString, queryArg);
    }

    public void setEventoPosExecucao(EventoVoid<Boolean> eventoPosExecucao) {
        this.eventoPosExecucao = eventoPosExecucao;
    }

    protected abstract void posSalvar(TEntidade tEntidade, String[] regrasIgnorar);

    protected abstract void preDeletar(TEntidade tEntidade, String[] regrasIgnorar);

    protected abstract Repositorio<TEntidade> obterRepositorio(Context context);

    private synchronized void eventoFinal() {
        qtdThreads--;
        if (qtdThreads <= 0) {
            if (eventoPosExecucao != null) {
                try {
                    eventoPosExecucao.executarEvento(true);
                } catch (Exception e) {
                    TratamentoExcecao.registrarExcecao(e);
                } finally {
                    TratamentoExcecao.invocarEvento();
                }
            }
        }
    }

}
