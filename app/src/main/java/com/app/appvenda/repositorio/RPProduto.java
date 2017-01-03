package com.app.appvenda.repositorio;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.entidade.Produto;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.List;

public class RPProduto extends Repositorio<Produto> {

    public RPProduto(Context context) {
        super(context, Produto.class);
    }

 }
