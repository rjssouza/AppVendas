package com.app.bdframework.excecoes;

/**
 * Exceção de regra de negócio, caso não seja atendido esta exceção será disparada
 */

public class RegraNegocioException extends Exception {

    public RegraNegocioException(String mensagem){
        super(mensagem);
    }

}
