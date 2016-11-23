package com.app.bdframework.excecoes;

import com.app.bdframework.negocio.RegraNegocio;
import com.app.bdframework.utils.TradutorMensagemException;

import java.util.List;

/**
 * Tradutor mensages regra de negocio
 */
public class RegraNegocioMensagem {

    private String mensagem;
    private List<RegraNegocioException> regraNegocioExceptions;
    private List<Exception> exceptions;

    public RegraNegocioMensagem(List<RegraNegocioException> regraNegocioExceptions) {
        this.regraNegocioExceptions = regraNegocioExceptions;
        for (RegraNegocioException exception : regraNegocioExceptions) {
            mensagem += TradutorMensagemException.obterMensagem(exception, false);
            mensagem += "\n";
        }
    }

    RegraNegocioMensagem(List<Exception> exceptions, @SuppressWarnings("SameParameterValue") boolean somenteException) {
        this.exceptions = exceptions;
        for (Exception exception : exceptions) {
            mensagem += TradutorMensagemException.obterMensagem(exception, false);
            mensagem += "\n";
        }
    }

    public String getMensagem() {
        return mensagem;
    }

    public List<RegraNegocioException> getRegraNegocioExceptions(){
        return regraNegocioExceptions;
    }

    public List<Exception> getExceptions(){
        return exceptions;
    }

}
