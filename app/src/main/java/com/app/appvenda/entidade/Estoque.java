package com.app.appvenda.entidade;

import android.content.Context;
import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.baseEntidade.Entidade;

public class Estoque extends Entidade<Integer> {

    public final static String ID_ESTOQUE = "id_estoque";
    public final static String ID_PRODUTO = "id_produto";
    public final static String QUANTIDADE = "quantidade";

    public Estoque(Cursor cursor){
        super(cursor);
    }

    @Override
    public void complementarEntidade(Context context) {

    }

    @ChavePrimaria
    private int id_estoque;
    @ColunaTabela
    private int id_produto;
    @ColunaTabela
    private int quantidade;

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

}
