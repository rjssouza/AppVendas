package com.app.appvenda.regrasNegocio.DeletarEntidade.RNCliente;

import android.content.Context;

import com.app.appvenda.entidade.Cliente;
import com.app.appvenda.entidade.Venda;
import com.app.appvenda.repositorio.RPPedido;
import com.app.appvenda.repositorio.RPVenda;
import com.app.bdframework.IExecutorQuery;
import com.app.bdframework.enums.EnumStatusVenda;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.negocio.RegraNegocio;
import com.app.bdframework.negocio.RegraNegocioResource;

/**
 * Created by Robson on 23/11/2016.
 */

public class ClientePossuiPedidosPendentes extends RegraNegocioResource implements RegraNegocio<Cliente> {

    RPVenda rpVenda;

    public ClientePossuiPedidosPendentes(Context context) {
        super(context);
        this.rpVenda = new RPVenda(context);
    }

    @Override
    public int getOrdem() {
        return 0;
    }

    @Override
    public void validarRegra(Cliente entidade, IExecutorQuery<Cliente> queryHelper) throws RegraNegocioException {
        if (entidade != null) {
            int qtdVenda = this.rpVenda.executarScalar(Venda.ID_STATUS_VENDA + " = ? AND " + Venda.ID_CLIENTE + " = ?",
                    new String[]{EnumStatusVenda.NAO_PAGO.getNumVal().toString(), entidade.getId_cliente().toString()});
            if (qtdVenda == 0) {
                throw new RegraNegocioException(getMsgRegraNegocio());
            }
        }
    }
}
