package com.app.bdframework.excecoes;

import com.app.bdframework.enums.EnumTipoMensagem;
import com.app.bdframework.utils.TradutorMensagemException;

/**
 * Tradutor mensages regra de negocio
 */
public class RegraNegocioMensagem {

    private String mensagem = "";
    private RegraNegocioException regraNegocioException;
    private Exception exception;

    private EnumTipoMensagem tipoMensagem;

    public RegraNegocioMensagem(RegraNegocioException regraNegocioExceptions) {
        this.regraNegocioException = regraNegocioExceptions;

        mensagem += TradutorMensagemException.obterMensagem(regraNegocioExceptions, false);
        mensagem += "\n";

        tipoMensagem = regraNegocioExceptions.getTipoMensagem();
    }

    public RegraNegocioMensagem(Exception exception) {
        this.exception = exception;

        mensagem += TradutorMensagemException.obterMensagem(exception, false);
        mensagem += "\n";

        tipoMensagem = EnumTipoMensagem.ERRO;
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

    public EnumTipoMensagem getTipoMensagem() {
        return tipoMensagem;
    }

}
