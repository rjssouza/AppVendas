package com.app.appvenda.regrasNegocio.SalvarEntidade.RNCliente;

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

public class ClienteExiste extends RegraNegocioResource implements RegraNegocio<Cliente> {

    public ClienteExiste(Context context) {
        super(context);
    }

    @Override
    public int getOrdem() {
        return 0;
    }

    @Override
    public void validarRegra(Cliente entidade, IExecutorQuery<Cliente> queryHelper) throws RegraNegocioException {
        int quantidadeCliente = queryHelper.executarScalar(Cliente.CNPJ + " = ? OR " + Cliente.CPF + " = ? ",
                new String[]{entidade.getCnpj().toString(), entidade.getCpf().toString()});

        if (quantidadeCliente > 0) {
            throw new RegraNegocioException(getMsgRegraNegocio(), EnumTipoMensagem.ERRO);
        }
    }

}
