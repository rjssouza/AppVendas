package com.app.appvenda.DAO;

import android.content.Context;

import com.app.bdframework.baseEntidade.Entidade;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.conversor.ConversorHelper;

import java.util.ArrayList;

/**
 * Created by Robson on 27/11/2016.
 */

abstract class BaseDAO<TModelo, TEntidade extends Entidade> {

    protected Context context;

    protected Repositorio<TEntidade> repositorio;

    BaseDAO(Context context) {
        this.context = context;
        this.repositorio = obterRepositorio(context);
    }

    protected abstract Repositorio<TEntidade> obterRepositorio(Context context);

    public abstract void salvar(TModelo tModelo, String[] regrasIgnorar);

    public abstract void deletar(TModelo tModelo, String[] regrasIgnorar);

}
