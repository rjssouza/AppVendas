package com.app.appvenda.repositorio;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.entidade.Cliente;
import com.app.bdframework.baseEntidade.Entidade;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.negocio.RegraNegocio;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Robson on 16/11/2016.
 */

public class RPCliente extends Repositorio<Cliente> {

    public RPCliente(Context context) {
        super(context);
    }

    @Override
    protected List<RegraNegocio> obterRegras() {
        return null;
    }

    @Override
    protected Cliente obterEntidade(Cursor cursor) {
        return new Cliente(cursor);
    }

    @Override
    protected String getNomeTabela() {
        return "tb_cliente";
    }

}
