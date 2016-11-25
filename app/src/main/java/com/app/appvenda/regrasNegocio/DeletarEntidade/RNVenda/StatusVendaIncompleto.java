package com.app.appvenda.regrasNegocio.DeletarEntidade.RNVenda;

import android.content.Context;

import com.app.appvenda.entidade.StatusVenda;
import com.app.appvenda.entidade.Venda;
import com.app.appvenda.repositorio.RPStatusVenda;
import com.app.appvenda.repositorio.RPVenda;
import com.app.bdframework.IExecutorQuery;
import com.app.bdframework.baseEntidade.Entidade;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.negocio.RegraNegocio;
import com.app.bdframework.negocio.RegraNegocioResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robson on 23/11/2016.
 */

public class StatusVendaIncompleto extends RegraNegocioResource implements RegraNegocio<Venda> {

    private RPVenda rpVenda;

    private RPStatusVenda rpStatusVenda;

    public StatusVendaIncompleto(Context context) {
        super(context);
        rpVenda = new RPVenda(context);
        rpStatusVenda = new RPStatusVenda(context);
    }

    @Override
    public int getOrdem() {
        return 0;
    }

    @Override
    public void validarRegra(Venda entidade, IExecutorQuery<Venda> queryHelper) throws RegraNegocioException {

    }

}
