package com.app.appvenda.dao;

import android.content.Context;

import com.app.appvenda.entidade.FormaPagto;
import com.app.appvenda.modelos.MFormaPagamento;
import com.app.appvenda.repositorio.RPFormaPagto;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.excecoes.RegraNegocioException;

import java.text.Normalizer;

/**
 * Created by Robson on 08/01/2017.
 */

public class FormaPagamentoDAO extends BaseDAO<MFormaPagamento, FormaPagto> {

    public FormaPagamentoDAO(Context context) {
        super(context, FormaPagto.class);
    }

    @Override
    protected void posSalvar(FormaPagto formaPagto, String[] regrasIgnorar) throws RegraNegocioException, Exception {

    }

    @Override
    protected void preDeletar(FormaPagto formaPagto, String[] regrasIgnorar) throws RegraNegocioException, Exception {

    }

    @Override
    protected Repositorio<FormaPagto> obterRepositorio(Context context) {
        return new RPFormaPagto(context);
    }

}
