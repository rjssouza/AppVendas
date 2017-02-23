package com.app.appvenda.conversores;

import com.app.appvenda.entidade.FormaPagto;
import com.app.appvenda.entidade.Pedido;
import com.app.appvenda.entidade.PedidoProduto;
import com.app.appvenda.enums.EnumTipoPedido;
import com.app.appvenda.modelos.MEndereco;
import com.app.appvenda.modelos.MFormaPagamento;
import com.app.appvenda.modelos.MPedido;
import com.app.appvenda.modelos.MPedidoProduto;
import com.app.bdframework.conversor.Conversor;
import com.app.bdframework.conversor.ConversorHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robson on 01/02/2017.
 */

public class ConversorPedido extends Conversor<MPedido, Pedido> {

    @Override
    public Pedido converterDePara(MPedido mPedido) {
        Pedido pedido = new Pedido(null);

        MEndereco mEndereco = mPedido.getmEndereco();
        pedido.setId_pedido(mPedido.getIdPedido());
        pedido.setCoord_y(mEndereco.getCoordY());
        pedido.setCoord_x(mEndereco.getCoordX());
        pedido.setEndereco(mEndereco.getEndereco());
        pedido.setValor_total(mPedido.getValorTotal());
        pedido.setId_tipo_pedido(Long.parseLong(mPedido.getEnumTipoPedido().getNumVal().toString()));

        if (mPedido.getmFormaPagamento() != null) {
            pedido.setId_forma_pagto(mPedido.getmFormaPagamento().getIdFormaPagto());
            pedido.setFormaPagto(ConversorHelper.converterDePara(mPedido.getmFormaPagamento(), FormaPagto.class));
        }

        if (mPedido.getMPedidoProdutos() != null && mPedido.getMPedidoProdutos().size() > 0) {
            for (MPedidoProduto mPedidoProduto : mPedido.getMPedidoProdutos()) {
                PedidoProduto pedidoProduto = ConversorHelper.converterParaDe(mPedidoProduto, PedidoProduto.class);
                pedido.getPedidoProdutos().add(pedidoProduto);
            }
        }

        return pedido;
    }

    @Override
    public MPedido converterParaDe(Pedido pedido) {
        MPedido mPedido = new MPedido();
        MEndereco mEndereco = new MEndereco();
        mEndereco.setEndereco(pedido.getEndereco());
        mPedido.setIdPedido(pedido.getId_pedido());
        mPedido.setmEndereco(mEndereco);
        mPedido.setmFormaPagamento(ConversorHelper.converterParaDe(pedido.getFormaPagto(), MFormaPagamento.class));
        mPedido.setValorTotal(pedido.getValor_total());
        mPedido.setEnumTipoPedido(ConversorHelper.converterParaDe(pedido.getId_tipo_pedido(), EnumTipoPedido.class));

        for (PedidoProduto pedidoProduto : pedido.getPedidoProdutos()) {
            MPedidoProduto mProduto = ConversorHelper.converterParaDe(pedidoProduto.getProduto(), MPedidoProduto.class);
            mPedido.getMPedidoProdutos().add(mProduto);
        }

        return mPedido;
    }

}
