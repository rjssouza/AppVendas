package com.app.appvenda.modelos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robson on 29/01/2017.
 */

public class MPedido {

    private int idPedido;
    private Double valorTotal;
    private MEndereco mEndereco;
    private MFormaPagamento mFormaPagamento;
    private List<MProduto> mProdutoList;

    public MPedido() {
        this.mProdutoList = new ArrayList<>();
    }

    public MEndereco getmEndereco() {
        return mEndereco;
    }

    public void setmEndereco(MEndereco mEndereco) {
        this.mEndereco = mEndereco;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public MFormaPagamento getmFormaPagamento() {
        return mFormaPagamento;
    }

    public void setmFormaPagamento(MFormaPagamento mFormaPagamento) {
        this.mFormaPagamento = mFormaPagamento;
    }

    public List<MProduto> getmProdutoList() {
        return mProdutoList;
    }

    public void setmProdutoList(List<MProduto> mProdutoList) {
        this.mProdutoList = mProdutoList;
    }

}
