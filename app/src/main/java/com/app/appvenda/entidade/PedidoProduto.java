package com.app.appvenda.entidade;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.repositorio.RPPedido;
import com.app.appvenda.repositorio.RPProduto;
import com.app.bdframework.IExecutorQuery;
import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.auxiliar.NomeTabela;
import com.app.bdframework.baseEntidade.Entidade;

@NomeTabela(nomeTabela = "tb_pedido_produto")
public class PedidoProduto extends Entidade<Integer> {

    public final static String ID_PEDIDO = "id_pedido";
    public final static String ID_PEDIDO_PRODUTO = "id_pedido_produto";
    public final static String ID_PRODUTO = "id_produto";
    public final static String ID_TIPO_PEDIDO = "id_tipo_pedido";
    public final static String QUANTIDADE = "quantidade";

    public PedidoProduto(Cursor cursor) {
        super(cursor);
    }

    @Override
    public void complementarEntidade(Context context) {
        IExecutorQuery<Pedido> iExecutorQueryPedido = new RPPedido(context);
        IExecutorQuery<Produto> iExecutorQueryProduto = new RPProduto(context);

        pedido = iExecutorQueryPedido.executarUnico(Pedido.getTodasColunas(Pedido.class), Pedido.ID_PEDIDO + "=?", false, id_pedido.toString());
        produto = iExecutorQueryProduto.executarUnico(Produto.getTodasColunas(Produto.class), Produto.ID_PRODUTO + "=?", true, id_produto.toString());
    }

    @ChavePrimaria
    private int id_pedido_produto;
    @ColunaTabela
    private Integer id_pedido;
    @ColunaTabela
    private Integer id_produto;
    @ColunaTabela
    private int quantidade;
    @ColunaTabela
    private int id_tipo_pedido;

    private Produto produto;
    private Pedido pedido;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

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

}
