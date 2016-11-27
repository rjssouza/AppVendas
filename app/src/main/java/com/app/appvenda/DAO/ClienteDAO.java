package com.app.appvenda.DAO;

import android.content.Context;

import com.app.appvenda.entidade.Cliente;
import com.app.appvenda.entidade.Telefone;
import com.app.appvenda.modelos.MCliente;
import com.app.appvenda.repositorio.RPCliente;
import com.app.appvenda.repositorio.RPTelefone;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.conversor.ConversorHelper;

import java.util.List;

public class ClienteDAO extends BaseDAO<MCliente, Cliente> {

    private RPCliente rpCliente;
    private RPTelefone rpTelefone;

    public ClienteDAO(Context context) {
        super(context);
        this.rpCliente = new RPCliente(context);
        this.rpTelefone = new RPTelefone(context);
    }

    @Override
    public void salvar(MCliente mCliente, String[] regrasIgnorar) {
        Cliente cliente = ConversorHelper.converter(mCliente);
        this.rpCliente.salvar(cliente, regrasIgnorar);
        this.rpTelefone.salvar(cliente.getCelular(), regrasIgnorar);
        this.rpTelefone.salvar(cliente.getFixo(), regrasIgnorar);
    }

    @Override
    public void deletar(MCliente mCliente, String[] regrasIgnorar) {
        Cliente cliente = ConversorHelper.converter(mCliente);
        List<Telefone> telefones = this.rpTelefone.executarQuery(Telefone.getTodasColunas(Telefone.class), Telefone.ID_CLIENTE + " = ?",
                new String[]{cliente.getId_cliente().toString()});
        for (Telefone telefone : telefones) {
            this.rpTelefone.deletar(telefone, regrasIgnorar);
        }
    }

}
