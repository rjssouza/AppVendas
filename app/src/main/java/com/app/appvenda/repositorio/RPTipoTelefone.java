package com.app.appvenda.repositorio;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.entidade.TipoTelefone;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.List;

public class RPTipoTelefone extends Repositorio<TipoTelefone> {

    public RPTipoTelefone(Context context) {
        super(context);
    }

    @Override
    protected TipoTelefone obterEntidade(Cursor cursor) {
        return new TipoTelefone(cursor);
    }

    @Override
    protected String getNomeTabela() {
        return "tb_tipo_telefone";
    }
}
