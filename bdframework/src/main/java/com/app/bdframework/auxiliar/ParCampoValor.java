package com.app.bdframework.auxiliar;

/**
 * Created by Robson on 14/11/2016.
 */

public class ParCampoValor<T> {
    CampoTabela campoTabela;
    T valor;

    public String getNomeCampo() {
        return campoTabela.nomeCampo();
    }

    public T getValor() {
        return valor;
    }

    public ParCampoValor(T valor, CampoTabela campoTabela) {
        this.campoTabela = campoTabela;
        this.valor = valor;
    }

}
