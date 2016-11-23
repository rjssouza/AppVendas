package com.app.appvenda.repositorio;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.entidade.Vendedor;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.List;

public class RPVendedor extends Repositorio<Vendedor> {

    public RPVendedor(Context context) {
        super(context);
    }

    @Override
    protected List<RegraNegocio<Vendedor>> obterRegras() {
        return null;
    }

    @Override
    protected Vendedor obterEntidade(Cursor cursor) {
        return new Vendedor(cursor);
    }

    @Override
    protected String getNomeTabela() {
        return "tb_vendedor";
    }
}
