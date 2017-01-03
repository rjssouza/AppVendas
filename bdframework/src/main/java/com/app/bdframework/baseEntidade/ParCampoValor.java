package com.app.bdframework.baseEntidade;

/**
 * Tupla nome de campo e valor de tabela a ser processado pelo repositorio generico
 */

public class ParCampoValor<T> {
    private final String nomeCampo;
    private final T valor;

    public String getNomeCampo() {
        return nomeCampo;
    }

    public T getValor() {
        return valor;
    }

    public ParCampoValor(T valor, String nomeCampo) {
        this.nomeCampo = nomeCampo;
        this.valor = valor;
    }

}
