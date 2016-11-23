package com.app.appvenda.entidade;

import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.baseEntidade.Entidade;

public class Estoque extends Entidade {

    public Estoque(Cursor cursor){
        super(cursor);
    }

    @ChavePrimaria
    private int id_estoque;

    public int getId_estoque() {
        return id_estoque;
    }

    public void setId_estoque(int id_estoque) {
        this.id_estoque = id_estoque;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    private int id_produto;

    private int quantidade;

}
