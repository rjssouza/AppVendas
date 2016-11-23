package com.app.appvenda.repositorio;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.entidade.Venda;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.List;

public class RPVenda extends Repositorio<Venda> {

    public RPVenda(Context context) {
        super(context);
    }

    @Override
    protected List<RegraNegocio<Venda>> obterRegras() {
        return null;
    }

    @Override
    protected Venda obterEntidade(Cursor cursor) {
        return new Venda(cursor);
    }

    @Override
    protected String getNomeTabela() {
        return "tb_venda";
    }

}
