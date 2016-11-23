package com.app.appvenda.repositorio;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.entidade.Produto;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.List;

public class RPProduto extends Repositorio<Produto> {

    public RPProduto(Context context) {
        super(context);
    }

    @Override
    protected List<RegraNegocio<Produto>> obterRegrasSalvar() {
        return null;
    }

    @Override
    protected List<RegraNegocio<Produto>> obterRegrasDeletar() {
        return null;
    }

    @Override
    protected Produto obterEntidade(Cursor cursor) {
        return new Produto(cursor);
    }

    @Override
    protected String getNomeTabela() {
        return "tb_produto";
    }
}
