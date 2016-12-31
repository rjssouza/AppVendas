package com.app.appvenda.dao;

import android.content.Context;

import com.app.appvenda.entidade.Estoque;
import com.app.appvenda.modelos.MEstoque;
import com.app.appvenda.repositorio.RPEstoque;
import com.app.bdframework.baseEntidade.Repositorio;

/**
 * Created by Robson on 20/12/2016.
 */

public class EstoqueDAO extends BaseDAO<MEstoque, Estoque> {

    EstoqueDAO(Context context) {
        super(context, Estoque.class);
    }

    @Override
    protected void posSalvar(Estoque estoque, String[] regrasIgnorar) {

    }

    @Override
    protected void preDeletar(Estoque estoque, String[] regrasIgnorar) {

    }

    @Override
    protected Repositorio<Estoque> obterRepositorio(Context context) {
        return new RPEstoque(context);
    }


}
