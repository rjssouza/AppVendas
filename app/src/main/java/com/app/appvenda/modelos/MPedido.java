package com.app.appvenda.modelos;

import com.app.appvenda.enums.EnumTipoPedido;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robson on 29/01/2017.
 */

public class MPedido {

    private Long idPedido;
    private Double valorTotal;
    private MEndereco mEndereco;
    private EnumTipoPedido enumTipoPedido;
    private MFormaPagamento mFormaPagamento;
    private List<MPedidoProduto> mPedidoProdutos;

    public MPedido() {
        this.mPedidoProdutos = new ArrayList<>();
    }

    public MEndereco getmEndereco() {
        return mEndereco;
    }

    public void setmEndereco(MEndereco mEndereco) {
        this.mEndereco = mEndereco;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
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

    public List<MPedidoProduto> getMPedidoProdutos() {
        return mPedidoProdutos;
    }

    public void setmPedidoProdutos(List<MPedidoProduto> mPedidoProdutos) {
        this.mPedidoProdutos = mPedidoProdutos;
    }

    public EnumTipoPedido getEnumTipoPedido() {
        return enumTipoPedido;
    }

    public void setEnumTipoPedido(EnumTipoPedido enumTipoPedido) {
        this.enumTipoPedido = enumTipoPedido;
    }

}
