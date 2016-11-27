package com.app.appvenda.conversores;

import com.app.appvenda.entidade.Cliente;
import com.app.appvenda.entidade.Telefone;
import com.app.appvenda.enums.EnumTipoTelefone;
import com.app.appvenda.modelos.MCliente;
import com.app.bdframework.conversor.Conversor;

public class ConversorCliente extends Conversor<MCliente, Cliente> {

    @Override
    public Cliente converterDePara(MCliente mCliente) {
        Cliente cliente = new Cliente(null);
        Telefone telefone = new Telefone(null);
        telefone.setId_tipo_telefone(EnumTipoTelefone.FIXO.getNumVal());
        telefone.setTelefone(mCliente.getTelefone());
        Telefone celular = new Telefone(null);
        celular.setId_tipo_telefone(EnumTipoTelefone.CELULAR.getNumVal());
        celular.setTelefone(mCliente.getCelular());

        cliente.setId_cliente(mCliente.getId());
        cliente.setCnpj(mCliente.getCnpj());
        cliente.setCpf(mCliente.getCpf());
        cliente.setAtivo(mCliente.isAtivo());
        cliente.setFixo(telefone);
        cliente.setCelular(celular);
        cliente.setCoord_x(mCliente.getCoord_x());
        cliente.setCoord_y(mCliente.getCoord_y());
        cliente.setNome(mCliente.getNome());
        cliente.setRazao_social(mCliente.getNomeFantasia());

        return cliente;
    }

    @Override
    public MCliente converterParaDe(Cliente cliente) {
        return null;
    }
}
