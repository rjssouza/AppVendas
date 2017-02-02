package com.app.appvenda.dao;

import android.content.Context;

import com.app.appvenda.entidade.Pedido;
import com.app.appvenda.modelos.MPedido;
import com.app.appvenda.repositorio.RPPedido;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.excecoes.RegraNegocioException;

/**
 * Created by Robson on 01/02/2017.
 */

public class PedidoDAO extends BaseDAO<MPedido, Pedido> {

    public PedidoDAO(Context context) {
        super(context, Pedido.class);
    }

    @Override
    protected void posSalvar(Pedido pedido, String[] regrasIgnorar) throws RegraNegocioException, Exception {


    }

    @Override
    protected void preDeletar(Pedido pedido, String[] regrasIgnorar) throws RegraNegocioException, Exception {

    }

    @Override
    protected Repositorio<Pedido> obterRepositorio(Context context) {
        return new RPPedido(context);
    }
}
