package com.app.appvenda.modelos;

/**
 * Created by Robson on 01/02/2017.
 */

public class MPedidoProduto  {

    private Integer IDPedidoProduto;
    private Integer quantidade;
    private MPedido mPedido;
    private MProduto mProduto;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public int getIDPedidoProduto() {
        return IDPedidoProduto;
    }

    public void setIDPedidoProduto(int IDPedidoProduto) {
        this.IDPedidoProduto = IDPedidoProduto;
    }

    public MPedido getmPedido() {
        return mPedido;
    }

    public void setmPedido(MPedido mPedido) {
        this.mPedido = mPedido;
    }

    public MProduto getmProduto() {
        return mProduto;
    }

    public void setmProduto(MProduto mProduto) {
        this.mProduto = mProduto;
    }

}
