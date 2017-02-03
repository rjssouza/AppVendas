package com.app.appvenda.conversores;

import android.content.Context;

import com.app.appvenda.entidade.FormaPagto;
import com.app.appvenda.entidade.Pedido;
import com.app.appvenda.entidade.PedidoProduto;
import com.app.appvenda.enums.EnumTipoPedido;
import com.app.appvenda.modelos.MEndereco;
import com.app.appvenda.modelos.MFormaPagamento;
import com.app.appvenda.modelos.MPedido;
import com.app.appvenda.modelos.MProduto;
import com.app.appvenda.repositorio.RPPedidoProduto;
import com.app.bdframework.IExecutorQuery;
import com.app.bdframework.conversor.Conversor;
import com.app.bdframework.conversor.ConversorHelper;

import java.util.ArrayList;
import java.util.List;

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

        List<PedidoProduto> pedidoProdutoList = new ArrayList<>();
        if (mPedido.getmProdutoList() != null && mPedido.getmProdutoList().size() > 0) {
            for (MProduto mProduto : mPedido.getmProdutoList()) {
                PedidoProduto pedidoProduto = pedidoProdutoIExecutorQuery.executarUnico(PedidoProduto.getTodasColunas(PedidoProduto.class), PedidoProduto.ID_PEDIDO + "=? AND " + PedidoProduto.ID_PRODUTO + "=?", mPedido.getIdPedido().toString(), mProduto.getIdProduto().toString());
                if (pedidoProduto == null) {
                    pedidoProduto = new PedidoProduto(null);
                    pedidoProduto.setId_pedido(mPedido.getIdPedido());
                    pedidoProduto.setId_produto(mProduto.getIdProduto());
                    pedidoProduto.setId_tipo_pedido(mProduto.getEnumTipoPedido().getNumVal());
                } else {
                    pedidoProduto.setQuantidade(pedidoProduto.getQuantidade() + 1);
                }
                pedidoProdutoList.add(pedidoProduto);
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
        for (PedidoProduto pedidoProduto : pedido.getPedidoProdutos()) {
            for (int x = 0; x < pedidoProduto.getQuantidade(); x++) {
                MProduto mProduto = ConversorHelper.converterParaDe(pedidoProduto.getProduto(), MProduto.class);
                mProduto.setEnumTipoPedido(ConversorHelper.converterParaDe(pedidoProduto, EnumTipoPedido.class));
                mPedido.getmProdutoList().add(mProduto);
            }
        }
        return mPedido;
    }

}
