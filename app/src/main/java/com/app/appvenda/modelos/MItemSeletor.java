package com.app.appvenda.modelos;

/**
 * Created by Robson on 23/01/2017.
 */

public class MItemSeletor {

    private int id;
    private String descricao;
    private String codigo;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return getCodigo() + " - " + getDescricao();
    }
}