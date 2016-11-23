package com.app.bdframework.baseEntidade;

import com.app.bdframework.auxiliar.ChavePrimaria;

/**
 * Tupla nome de campo e valor de tabela a ser processado pelo repositorio generico
 */

class ParCampoValor<T> {
    private String nomeCampo;
    private T valor;

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
