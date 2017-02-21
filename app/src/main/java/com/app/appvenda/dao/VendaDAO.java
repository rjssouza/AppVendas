package com.app.appvenda.dao;

import android.content.Context;

import com.app.appvenda.entidade.Venda;
import com.app.appvenda.modelos.MVenda;
import com.app.appvenda.repositorio.RPPedido;
import com.app.appvenda.repositorio.RPVenda;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.excecoes.RegraNegocioException;

/**
 * Created by Robson on 02/02/2017.
 */
public class VendaDAO extends BaseDAO<MVenda, Venda> {

    private PedidoDAO pedidoDAO;

    public VendaDAO(Context context) {
        super(context, Venda.class, MVenda.class);
        this.pedidoDAO = new PedidoDAO(context);
    }

    @Override
    protected void posSalvar(Venda venda, String[] regrasIgnorar) throws RegraNegocioException, Exception {
        venda.getPedido().setId_venda(venda.getId_venda());
        this.pedidoDAO.salvar(venda.getPedido(), null);
    }

    @Override
    protected void preDeletar(Venda venda, String[] regrasIgnorar) throws RegraNegocioException, Exception {

    }

    @Override
    protected Repositorio<Venda> obterRepositorio(Context context) {
        return new RPVenda(context);
    }

}
