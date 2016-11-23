package com.app.appvenda.entidade;

import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.baseEntidade.Entidade;

public class PedidoProduto extends Entidade {

    public PedidoProduto(Cursor cursor){
        super(cursor);
    }

    @ChavePrimaria
    private int id_pedido_produto;
    @ColunaTabela
    private int id_pedido;

    public int getId_pedido_produto() {
        return id_pedido_produto;
    }

    public void setId_pedido_produto(int id_pedido_produto) {
        this.id_pedido_produto = id_pedido_produto;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getId_tipo_pedido() {
        return id_tipo_pedido;
    }

    public void setId_tipo_pedido(int id_tipo_pedido) {
        this.id_tipo_pedido = id_tipo_pedido;
    }
    @ColunaTabela
    private int id_produto;
    @ColunaTabela
    private int quantidade;
    @ColunaTabela
    private int id_tipo_pedido;

}
