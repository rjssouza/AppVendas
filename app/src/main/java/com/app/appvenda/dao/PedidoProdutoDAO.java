package com.app.appvenda.dao;

import android.content.Context;

import com.app.appvenda.entidade.PedidoProduto;
import com.app.appvenda.modelos.MPedidoProduto;
import com.app.appvenda.repositorio.RPPedidoProduto;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.excecoes.RegraNegocioException;

import java.util.List;

/**
 * Created by Robson on 01/02/2017.
 */

public class PedidoProdutoDAO extends BaseDAO<MPedidoProduto, PedidoProduto> {

    public PedidoProdutoDAO(Context context) {
        super(context, PedidoProduto.class);
    }

    @Override
    protected void posSalvar(PedidoProduto pedidoProduto, String[] regrasIgnorar) throws RegraNegocioException, Exception {

    }

    @Override
    protected void preDeletar(PedidoProduto pedidoProduto, String[] regrasIgnorar) throws RegraNegocioException, Exception {

    }

    @Override
    protected Repositorio<PedidoProduto> obterRepositorio(Context context) {
        return new RPPedidoProduto(context);
    }

    public List<PedidoProduto> obterPedidoProduto(String idPedido) {
        List<PedidoProduto> pedidoProdutos = this.getLista(PedidoProduto.ID_PEDIDO + "=?", true, idPedido);
        return pedidoProdutos;
    }

}
