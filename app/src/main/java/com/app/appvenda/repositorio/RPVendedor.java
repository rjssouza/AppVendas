package com.app.appvenda.repositorio;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.entidade.Vendedor;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.List;

public class RPVendedor extends Repositorio<Vendedor> {

    public RPVendedor(Context context) {
        super(context, Vendedor.class);
    }
}
