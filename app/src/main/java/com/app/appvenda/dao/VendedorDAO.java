package com.app.appvenda.dao;

import android.content.Context;

import com.app.appvenda.entidade.Vendedor;
import com.app.appvenda.modelos.MItemSeletor;
import com.app.appvenda.modelos.MVendedor;
import com.app.appvenda.repositorio.RPVendedor;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.excecoes.RegraNegocioException;

/**
 * Created by Robson on 08/01/2017.
 */

public class VendedorDAO extends BaseDAO<MVendedor, Vendedor> {

    public VendedorDAO(Context context) {
        super(context, Vendedor.class);
    }

    @Override
    protected void posSalvar(Vendedor vendedor, String[] regrasIgnorar) throws RegraNegocioException, Exception {

    }

    @Override
    protected void preDeletar(Vendedor vendedor, String[] regrasIgnorar) throws RegraNegocioException, Exception {

    }

    public void atualizarVendedor(MItemSeletor mItemSeletor){
        Vendedor vendedor = getUnico(Vendedor.ID_VENDEDOR+"=?", new String[]{mItemSeletor.getId().toString()});
    }

    @Override
    protected Repositorio<Vendedor> obterRepositorio(Context context) {
        return new RPVendedor(context);
    }
}
