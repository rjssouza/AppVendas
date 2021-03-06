package com.app.appvenda.repositorio;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.entidade.Telefone;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.List;

public class RPTelefone extends Repositorio<Telefone> {

    public RPTelefone(Context context) {
        super(context, Telefone.class);
    }
}
