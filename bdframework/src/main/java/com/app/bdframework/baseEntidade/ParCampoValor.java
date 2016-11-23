package com.app.bdframework.baseEntidade;

/**
 * Tupla nome de campo e valor de tabela a ser processado pelo repositorio generico
 */

class ParCampoValor<T> {
    private final String nomeCampo;
    private final T valor;

    String getNomeCampo() {
        return nomeCampo;
    }

    T getValor() {
        return valor;
    }

    ParCampoValor(T valor, String nomeCampo) {
        this.nomeCampo = nomeCampo;
        this.valor = valor;
    }

}
