package com.app.appvenda.DAO;

import android.content.Context;

import com.app.appvenda.entidade.Cliente;
import com.app.appvenda.entidade.Telefone;
import com.app.appvenda.modelos.MCliente;
import com.app.appvenda.repositorio.RPCliente;
import com.app.appvenda.repositorio.RPTelefone;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.excecoes.RegraNegocioException;

import java.util.List;

public class ClienteDAO extends BaseDAO<MCliente, Cliente> {

    private RPTelefone rpTelefone;

    public ClienteDAO(Context context) {
        super(context, Cliente.class);
        this.rpTelefone = new RPTelefone(context);
    }

    @Override
    protected Repositorio<Cliente> obterRepositorio(Context context) {
        return new RPCliente(context);
    }

    @Override
    protected void posSalvar(Cliente cliente, String[] regrasIgnorar) throws RegraNegocioException, Exception  {
        this.rpTelefone.salvar(cliente.getCelular(), regrasIgnorar);
        this.rpTelefone.salvar(cliente.getFixo(), regrasIgnorar);
    }

    @Override
    protected void preDeletar(Cliente cliente, String[] regrasIgnorar) throws RegraNegocioException, Exception {
        List<Telefone> telefones = this.rpTelefone.executarQuery(Telefone.getTodasColunas(Telefone.class), Telefone.ID_CLIENTE + " = ?",
                new String[]{cliente.getId_cliente().toString()});
        for (Telefone telefone : telefones) {
            this.rpTelefone.deletar(telefone, regrasIgnorar);
        }
    }

}
