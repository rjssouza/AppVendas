package com.app.appvenda.dao;

import android.content.Context;

import com.app.appvenda.entidade.TipoPedido;
import com.app.appvenda.enums.EnumTipoPedido;
import com.app.appvenda.modelos.MItemSeletor;
import com.app.appvenda.repositorio.RPTipoPedido;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.conversor.ConversorHelper;
import com.app.bdframework.excecoes.RegraNegocioException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robson on 16/02/2017.
 */

public class TipoPedidoDAO extends BaseDAO<EnumTipoPedido, TipoPedido> {

    public TipoPedidoDAO(Context context) {
        super(context, TipoPedido.class, EnumTipoPedido.class);
    }

    @Override
    protected void posSalvar(TipoPedido tipoPedido, String[] regrasIgnorar) throws RegraNegocioException, Exception {

    }

    @Override
    protected void preDeletar(TipoPedido tipoPedido, String[] regrasIgnorar) throws RegraNegocioException, Exception {

    }

    public List<MItemSeletor> obterTodosTiposPedidos(){
        List<TipoPedido> tipoPedidos = this.getLista(null, null);
        List<MItemSeletor> mItemSeletors = ConversorHelper.converterDePara(tipoPedidos, new ArrayList<MItemSeletor>().getClass());
        return mItemSeletors;
    }

    @Override
    protected Repositorio<TipoPedido> obterRepositorio(Context context) {
        return new RPTipoPedido(context);
    }
}
