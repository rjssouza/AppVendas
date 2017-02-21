package com.app.appvenda.dao;

import android.content.Context;

import com.app.appvenda.entidade.Pedido;
import com.app.appvenda.entidade.PedidoProduto;
import com.app.appvenda.modelos.MPedido;
import com.app.appvenda.repositorio.RPPedido;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.excecoes.RegraNegocioException;

/**
 * Created by Robson on 01/02/2017.
 */

public class PedidoDAO extends BaseDAO<MPedido, Pedido> {

    private PedidoProdutoDAO pedidoProdutoDAO;

    public PedidoDAO(Context context) {
        super(context, Pedido.class, MPedido.class);
        this.pedidoProdutoDAO = new PedidoProdutoDAO(context);
    }

    @Override
    protected void posSalvar(Pedido pedido, String[] regrasIgnorar) throws RegraNegocioException, Exception {
        for (PedidoProduto pedidoProduto : pedido.getPedidoProdutos()) {
            pedidoProduto.setId_pedido(pedido.getId_pedido());
            this.pedidoProdutoDAO.salvar(pedidoProduto, null);
        }
    }

    @Override
    protected void preDeletar(Pedido pedido, String[] regrasIgnorar) throws RegraNegocioException, Exception {

    }

    @Override
    protected Repositorio<Pedido> obterRepositorio(Context context) {
        return new RPPedido(context);
    }
}
