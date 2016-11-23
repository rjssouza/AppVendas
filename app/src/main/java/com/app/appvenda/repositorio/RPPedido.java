package com.app.appvenda.repositorio;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.entidade.Pedido;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.List;

public class RPPedido extends Repositorio<Pedido> {

    public RPPedido(Context context) {
        super(context);
    }

    @Override
    protected List<RegraNegocio<Pedido>> obterRegrasSalvar() {
        return null;
    }

    @Override
    protected List<RegraNegocio<Pedido>> obterRegrasDeletar() {
        return null;
    }

    @Override
    protected Pedido obterEntidade(Cursor cursor) {
        return new Pedido(cursor);
    }

    @Override
    protected String getNomeTabela() {
        return "tb_pedido";
    }
}
