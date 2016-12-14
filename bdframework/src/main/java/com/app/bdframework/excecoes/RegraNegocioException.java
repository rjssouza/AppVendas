package com.app.bdframework.excecoes;

import com.app.bdframework.enums.EnumTipoMensagem;

/**
 * Exceção de regra de negócio, caso não seja atendido esta exceção será disparada
 */

public class RegraNegocioException extends Exception {

    private EnumTipoMensagem tipoMensagem;

    public RegraNegocioException(String mensagem, EnumTipoMensagem tipoMensagem) {
        super(mensagem);
        if (tipoMensagem == null)
            throw new NullPointerException("Tipo Mensagem");
        this.tipoMensagem = tipoMensagem;
    }

    public EnumTipoMensagem getTipoMensagem() {
        return tipoMensagem;
    }

}
