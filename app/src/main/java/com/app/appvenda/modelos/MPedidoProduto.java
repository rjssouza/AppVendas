package com.app.appvenda.modelos;

/**
 * Created by Robson on 01/02/2017.
 */

public class MPedidoProduto  {

    private Long IDPedidoProduto;
    private Integer quantidade;
    private MPedido mPedido;
    private MProduto mProduto;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getIDPedidoProduto() {
        return IDPedidoProduto;
    }

    public void setIDPedidoProduto(Long IDPedidoProduto) {
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
