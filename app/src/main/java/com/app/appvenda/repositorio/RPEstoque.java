package com.app.appvenda.repositorio;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.entidade.Estoque;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.List;

public class RPEstoque  extends Repositorio<Estoque> {

    public RPEstoque(Context context) {
        super(context);
    }

    @Override
    protected Estoque obterEntidade(Cursor cursor) {
        return new Estoque(cursor);
    }

    @Override
    protected String getNomeTabela() {
        return "tb_estoque";
    }
}
