package com.app.appvenda.conversores;

import android.content.Context;

import com.app.appvenda.entidade.FormaPagto;
import com.app.appvenda.entidade.Pedido;
import com.app.appvenda.entidade.PedidoProduto;
import com.app.appvenda.modelos.MEndereco;
import com.app.appvenda.modelos.MPedido;
import com.app.appvenda.modelos.MProduto;
import com.app.appvenda.repositorio.RPPedidoProduto;
import com.app.bdframework.IExecutorQuery;
import com.app.bdframework.conversor.Conversor;
import com.app.bdframework.conversor.ConversorHelper;

/**
 * Created by Robson on 01/02/2017.
 */

public class ConversorPedido extends Conversor<MPedido, Pedido> {

    private IExecutorQuery<PedidoProduto> pedidoProdutoIExecutorQuery;

    public ConversorPedido(Context context) {
        pedidoProdutoIExecutorQuery = new RPPedidoProduto(context);
    }

    @Override
    public Pedido converterDePara(MPedido mPedido) {
        Pedido pedido = new Pedido(null);

        MEndereco mEndereco = mPedido.getmEndereco();
        pedido.setId_pedido(mPedido.getIdPedido());
        pedido.setCoord_y(mEndereco.getCoordY());
        pedido.setCoord_x(mEndereco.getCoordX());
        pedido.setEndereco(mEndereco.getEndereco());
        pedido.setValor_total(mPedido.getValorTotal());

        if (mPedido.getmFormaPagamento() != null) {
            pedido.setId_forma_pagto(mPedido.getmFormaPagamento().getIdFormaPagto());
            pedido.setFormaPagto(ConversorHelper.converterDePara(mPedido.getmFormaPagamento(), FormaPagto.class));
        }

        if (mPedido.getmProdutoList() != null && mPedido.getmProdutoList().size() > 0) {
            for (MProduto mProduto : mPedido.getmProdutoList()) {

            }
        }

        return pedido;
    }

    @Override
    public MPedido converterParaDe(Pedido pedido) {
        return null;
    }

}
