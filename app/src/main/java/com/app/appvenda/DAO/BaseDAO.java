package com.app.appvenda.DAO;

import android.content.Context;

import com.app.bdframework.baseEntidade.Entidade;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.conversor.ConversorHelper;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.TratamentoExcecao;

import java.util.ArrayList;
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

    BaseDAO(Context context) {
        this.context = context;
        this.repositorio = obterRepositorio(context);
        this.executor = Executors.newFixedThreadPool(5);
    }

    protected abstract Repositorio<TEntidade> obterRepositorio(Context context);

    public void salvar(final TModelo tModelo, final String[] regrasIgnorar) {
        qtdThreads++;
        this.executor.submit(new Runnable() {
            @Override
            public void run() {
                efetuarsalvar(tModelo, regrasIgnorar);
                eventoFinal();
            }
        });
    }

    public void deletar(final TModelo tModelo, final String[] regrasIgnorar) {
        qtdThreads++;
        this.executor.submit(new Runnable() {
            @Override
            public void run() {
                efetuardeletar(tModelo, regrasIgnorar);
                eventoFinal();
            }
        });
    }

    protected abstract void efetuarsalvar(TModelo tModelo, String[] regrasIgnorar);

    protected abstract void efetuardeletar(TModelo tModelo, String[] regrasIgnorar);

    public void setEventoPosExecucao(EventoVoid<Boolean> eventoPosExecucao) {
        this.eventoPosExecucao = eventoPosExecucao;
    }

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
