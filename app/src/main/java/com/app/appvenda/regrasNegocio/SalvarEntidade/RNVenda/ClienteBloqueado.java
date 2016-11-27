package com.app.appvenda.regrasNegocio.SalvarEntidade.RNVenda;

import android.content.Context;

import com.app.appvenda.entidade.Cliente;
import com.app.bdframework.IExecutorQuery;
import com.app.bdframework.enums.EnumTipoMensagem;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.negocio.RegraNegocio;
import com.app.bdframework.negocio.RegraNegocioResource;

/**
 * Created by Robson on 23/11/2016.
 */

public class ClienteBloqueado extends RegraNegocioResource implements RegraNegocio<Cliente> {

    public ClienteBloqueado(Context context) {
        super(context);
    }

    @Override
    public int getOrdem() {
        return 0;
    }

    @Override
    public void validarRegra(Cliente entidade, IExecutorQuery<Cliente> queryHelper) throws RegraNegocioException {
        if (!entidade.getAtivo()) {
            throw new RegraNegocioException(getMsgRegraNegocio(), EnumTipoMensagem.PERGUNTA);
        }
    }

}
