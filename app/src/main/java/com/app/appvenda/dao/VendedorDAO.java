package com.app.appvenda.dao;

import android.content.Context;

import com.app.appvenda.entidade.Vendedor;
import com.app.appvenda.modelos.MItemSeletor;
import com.app.appvenda.modelos.MVendedor;
import com.app.appvenda.repositorio.RPVendedor;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.conversor.ConversorHelper;
import com.app.bdframework.excecoes.RegraNegocioException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robson on 08/01/2017.
 */

public class VendedorDAO extends BaseDAO<MVendedor, Vendedor> {

    public VendedorDAO(Context context) {
        super(context, Vendedor.class);
    }

    @Override
    protected void posSalvar(Vendedor vendedor, String[] regrasIgnorar) throws RegraNegocioException, Exception {
        if (vendedor.getAtivo()) {
            Vendedor vendedor1 = new Vendedor(null);
            vendedor1.setAtivo(false);
            this.repositorio.atualizarEntidade(vendedor1, Vendedor.ID_VENDEDOR + "!=", vendedor1.getId_vendedor().toString());
        }
    }

    @Override
    protected void preDeletar(Vendedor vendedor, String[] regrasIgnorar) throws RegraNegocioException, Exception {

    }

    public void atualizarVendedor(MItemSeletor mItemSeletor) {
        Vendedor vendedor = getUnico(Vendedor.ID_VENDEDOR + "=?", false, new String[]{mItemSeletor.getId().toString()});
        vendedor.setAtivo(true);
        salvar(vendedor);
    }

    public MVendedor getUnico(String idVendedor) {
        Vendedor vendedor = getUnico(Vendedor.ID_VENDEDOR + "=?", false, idVendedor);
        return ConversorHelper.converterParaDe(vendedor, MVendedor.class);
    }

    public List<MItemSeletor> getTodosVendedoresSelecionar() {
        List<Vendedor> vendedorList = this.getLista(null, false, null);
        List<MItemSeletor> mItemSeletors = ConversorHelper.converterDePara(vendedorList, new ArrayList<MItemSeletor>().getClass());
        return mItemSeletors;
    }

    @Override
    protected Repositorio<Vendedor> obterRepositorio(Context context) {
        return new RPVendedor(context);
    }
}
