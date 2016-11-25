package com.app.appvenda.repositorio;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.entidade.Cliente;
import com.app.appvenda.regrasNegocio.DeletarEntidade.RNCliente.ClientePossuiPedidosPendentes;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.negocio.RegraNegocio;

import java.util.ArrayList;
import java.util.List;


public class RPCliente extends Repositorio<Cliente> {

    public RPCliente(Context context) {
        super(context);
    }

    @Override
    protected List<RegraNegocio<Cliente>> obterRegrasSalvar() {
        return null;
    }

    @Override
    protected List<RegraNegocio<Cliente>> obterRegrasDeletar() {
        List<RegraNegocio<Cliente>> regraNegocios = new ArrayList<>();
        regraNegocios.add(new ClientePossuiPedidosPendentes(_context));
        return regraNegocios;
    }

    @Override
    protected Cliente obterEntidade(Cursor cursor) {
        return new Cliente(cursor);
    }

    @Override
    protected String getNomeTabela() {
        return "tb_cliente";
    }

}
