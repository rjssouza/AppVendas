package com.app.appvenda.regrasNegocio.DeletarEntidade.RNVenda;

import android.content.Context;

import com.app.appvenda.entidade.StatusVenda;
import com.app.appvenda.entidade.Venda;
import com.app.appvenda.enums.EnumStatusVenda;
import com.app.appvenda.repositorio.RPStatusVenda;
import com.app.appvenda.repositorio.RPVenda;
import com.app.bdframework.IExecutorQuery;
import com.app.bdframework.baseEntidade.Entidade;
import com.app.bdframework.enums.EnumTipoMensagem;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.negocio.RegraNegocio;
import com.app.bdframework.negocio.RegraNegocioResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robson on 23/11/2016.
 */

public class StatusVendaNaoPago extends RegraNegocioResource implements RegraNegocio<Venda> {

    private RPStatusVenda rpStatusVenda;

    public StatusVendaNaoPago(Context context) {
        super(context);
        rpStatusVenda = new RPStatusVenda(context);
    }

    @Override
    public int getOrdem() {
        return 0;
    }

    @Override
    public boolean validarRegra(Venda entidade, IExecutorQuery<Venda> queryHelper) throws RegraNegocioException {
        StatusVenda statusVenda = rpStatusVenda.executarUnico(StatusVenda.getTodasColunas(StatusVenda.class), StatusVenda.ID_STATUS_VENDA + " = ?",
                false, new String[]{entidade.getId_status_venda().toString()});

        if (statusVenda.getCod_status().equals(EnumStatusVenda.NAO_PAGO.getNumVal())) {
            throw new RegraNegocioException(getMsgRegraNegocio(), EnumTipoMensagem.ERRO);
        }
        return true;
    }

}
