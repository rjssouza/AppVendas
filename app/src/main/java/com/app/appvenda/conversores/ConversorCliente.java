package com.app.appvenda.conversores;

import com.app.appvenda.entidade.Cliente;
import com.app.appvenda.modelos.MCliente;
import com.app.bdframework.conversor.Conversor;

public class ConversorCliente extends Conversor<Cliente, MCliente> {

    @Override
    public MCliente converterDePara(Cliente cliente) {
        return null;
    }

    @Override
    public Cliente converterParaDe(MCliente mCliente) {
        return null;
    }
}
