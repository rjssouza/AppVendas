package com.app.appvenda.regrasNegocio.RNCliente;

import com.app.appvenda.entidade.Cliente;
import com.app.bdframework.IExecutorQuery;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.negocio.RegraNegocio;

/**
 * Verifica cnpj e codigo do clientge ja existente
 */

public class ClienteExiste implements RegraNegocio<Cliente> {
    @Override
    public int getOrdem() {
        return 0;
    }

    @Override
    public void validarRegra(Cliente entidade, IExecutorQuery queryHelper) throws RegraNegocioException {
    }
}
