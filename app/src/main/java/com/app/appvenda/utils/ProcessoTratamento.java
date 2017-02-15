package com.app.appvenda.utils;

import com.app.bdframework.excecoes.TratamentoExcecao;

/**
 * Created by Robson on 14/02/2017.
 */

public abstract class ProcessoTratamento implements Runnable {

    @Override
    public void run() {
        try {
            executar();
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    protected abstract void executar() throws Exception;
}
