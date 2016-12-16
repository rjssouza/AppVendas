package com.app.appvenda.repositorio;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.entidade.StatusVenda;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.List;

public class RPStatusVenda extends Repositorio<StatusVenda> {

    public RPStatusVenda(Context context) {
        super(context);
    }

    @Override
    protected StatusVenda obterEntidade(Cursor cursor) {
        return new StatusVenda(cursor);
    }

    @Override
    protected String getNomeTabela() {
        return "tb_status_venda";
    }
}
