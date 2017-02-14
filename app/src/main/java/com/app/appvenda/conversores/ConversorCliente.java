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
        telefone.setId_cliente(mCliente.getId());

        Telefone celular = new Telefone(null);
        celular.setId_tipo_telefone(EnumTipoTelefone.CELULAR.getNumVal());
        celular.setTelefone(mCliente.getCelular());
        celular.setId_cliente(mCliente.getId());

        cliente.setCod_cliente(mCliente.getId());
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
        cliente.setEmail(mCliente.getEmail());
        return cliente;
    }

    @Override
    public MCliente converterParaDe(Cliente cliente) {
        MCliente mCliente = new MCliente();
        mCliente.setAtivo(cliente.getAtivo());
        mCliente.setCelular(cliente.getCelular().getTelefone());
        mCliente.setTelefone(cliente.getFixo().getTelefone());
        mCliente.setCnpj(cliente.getCnpj());
        mCliente.setCoord_x(cliente.getCoord_x());
        mCliente.setCoord_y(cliente.getCoord_y());
        mCliente.setCpf(cliente.getCpf());
        mCliente.setNome(cliente.getNome());
        mCliente.setNomeFantasia(cliente.getRazao_social());
        mCliente.setEmail(cliente.getEmail());
        return mCliente;
    }
}
