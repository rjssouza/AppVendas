package com.app.bdframework.excecoes;

import com.app.bdframework.utils.TradutorMensagemException;

import java.util.List;

/**
 * Tradutor mensages regra de negocio
 */
public class RegraNegocioMensagem {

    private String mensagem;
    private RegraNegocioException regraNegocioException;
    private Exception exception;

    public RegraNegocioMensagem(RegraNegocioException regraNegocioExceptions) {
        this.regraNegocioException = regraNegocioExceptions;

        mensagem += TradutorMensagemException.obterMensagem(regraNegocioExceptions, false);
        mensagem += "\n";
    }

    RegraNegocioMensagem(Exception exception, @SuppressWarnings("SameParameterValue") boolean somenteException) {
        this.exception = exception;

        mensagem += TradutorMensagemException.obterMensagem(exception, false);
        mensagem += "\n";
    }

    public String getMensagem() {
        return mensagem;
    }

    public RegraNegocioException getRegraNegocioException() {
        return regraNegocioException;
    }

    public Exception getException() {
        return exception;
    }

}
