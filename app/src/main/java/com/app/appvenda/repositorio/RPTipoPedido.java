package com.app.appvenda.repositorio;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.entidade.TipoPedido;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.List;

public class RPTipoPedido extends Repositorio<TipoPedido> {

    public RPTipoPedido(Context context) {
        super(context, TipoPedido.class);
    }

}
