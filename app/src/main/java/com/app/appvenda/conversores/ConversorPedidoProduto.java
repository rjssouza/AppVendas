package com.app.appvenda.conversores;

import com.app.appvenda.entidade.Pedido;
import com.app.appvenda.entidade.PedidoProduto;
import com.app.appvenda.entidade.Produto;
import com.app.appvenda.modelos.MPedido;
import com.app.appvenda.modelos.MPedidoProduto;
import com.app.appvenda.modelos.MProduto;
import com.app.bdframework.conversor.Conversor;
import com.app.bdframework.conversor.ConversorHelper;

/**
 * Created by Robson on 07/02/2017.
 */

public class ConversorPedidoProduto extends Conversor<MPedidoProduto, PedidoProduto> {
    @Override
    public PedidoProduto converterDePara(MPedidoProduto mPedidoProduto) {
        PedidoProduto pedidoProduto = new PedidoProduto(null);
        pedidoProduto.setId_pedido_produto(mPedidoProduto.getIDPedidoProduto());
        pedidoProduto.setId_pedido(mPedidoProduto.getmPedido().getIdPedido());
        pedidoProduto.setId_produto(mPedidoProduto.getmProduto().getIdProduto());
        pedidoProduto.setQuantidade(mPedidoProduto.getQuantidade());
        pedidoProduto.setPedido(ConversorHelper.converterDePara(mPedidoProduto.getmPedido(), Pedido.class));
        pedidoProduto.setProduto(ConversorHelper.converterDePara(mPedidoProduto.getmProduto(), Produto.class));
        return pedidoProduto;
    }

    @Override
    public MPedidoProduto converterParaDe(PedidoProduto pedidoProduto) {
        MPedidoProduto mPedidoProduto = new MPedidoProduto();
        mPedidoProduto.setIDPedidoProduto(pedidoProduto.getId_pedido_produto());
        mPedidoProduto.setQuantidade(pedidoProduto.getQuantidade());
        mPedidoProduto.setmPedido(ConversorHelper.converterParaDe(pedidoProduto.getPedido(), MPedido.class));
        mPedidoProduto.setmProduto(ConversorHelper.converterParaDe(pedidoProduto.getProduto(), MProduto.class));
        return mPedidoProduto;
    }
}
