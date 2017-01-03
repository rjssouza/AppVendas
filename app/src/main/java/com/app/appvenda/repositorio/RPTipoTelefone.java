package com.app.appvenda.repositorio;

import android.content.Context;

import com.app.appvenda.entidade.TipoTelefone;
import com.app.bdframework.baseEntidade.Repositorio;

public class RPTipoTelefone extends Repositorio<TipoTelefone> {

    public RPTipoTelefone(Context context) {
        super(context, TipoTelefone.class);
    }

}
