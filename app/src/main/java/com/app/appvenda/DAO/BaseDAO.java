package com.app.appvenda.DAO;

import android.content.Context;

import com.app.bdframework.baseEntidade.Entidade;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.conversor.ConversorHelper;

/**
 * Created by Robson on 27/11/2016.
 */

public abstract class BaseDAO<TModelo, TEntidade extends Entidade> {

    protected Context context;

    protected Repositorio<TEntidade> repositorio;

    public BaseDAO(Context context) {
        this.context = context;
    }

    public abstract void salvar(TModelo tModelo, String[] regrasIgnorar);

    public abstract void deletar(TModelo tModelo, String[] regrasIgnorar);
}
