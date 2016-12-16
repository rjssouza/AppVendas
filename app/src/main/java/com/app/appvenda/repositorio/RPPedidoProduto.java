package com.app.appvenda.repositorio;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.entidade.PedidoProduto;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.List;

public class RPPedidoProduto extends Repositorio<PedidoProduto> {

    public RPPedidoProduto(Context context) {
        super(context);
    }

    @Override
    protected PedidoProduto obterEntidade(Cursor cursor) {
        return new PedidoProduto(cursor);
    }

    @Override
    protected String getNomeTabela() {
        return "tb_pedido_produto";
    }
}
