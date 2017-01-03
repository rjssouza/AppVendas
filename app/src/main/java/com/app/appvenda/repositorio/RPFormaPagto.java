package com.app.appvenda.repositorio;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.entidade.FormaPagto;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.List;

public class RPFormaPagto extends Repositorio<FormaPagto> {

    public RPFormaPagto(Context context) {
        super(context, FormaPagto.class);
    }

}
