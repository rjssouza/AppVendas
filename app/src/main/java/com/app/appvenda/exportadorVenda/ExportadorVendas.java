package com.app.appvenda.exportadorVenda;

import android.content.Context;

import com.app.appvenda.DAO.ClienteDAO;
import com.app.appvenda.DAO.ConfiguracaoDAO;
import com.app.appvenda.modelos.MCliente;
import com.app.appvenda.modelos.MEstoque;
import com.app.appvenda.modelos.MProduto;
import com.app.appvenda.modelos.MVenda;
import com.app.appvenda.modelos.MVendedor;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.TratamentoExcecao;

import java.util.ArrayList;

/**
 * Created by Robson on 30/11/2016.
 */

public abstract class ExportadorVendas {

    private ClienteDAO clienteDAO;
    public ConfiguracaoDAO configuracaoDAO;
    protected Context context;

    ExportadorVendas(Context context) {
        this.context = context;
        clienteDAO = new ClienteDAO(context);
        configuracaoDAO = new ConfiguracaoDAO(context);
    }

    public void importarBaseDados() {
        try {
            importarClientes();
        } catch (RegraNegocioException e) {
            TratamentoExcecao.registrarRegraNegocioExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    public void exportarVendas() {
        ArrayList<MVenda> mVenda = obterVendasEfetuadas();

    }

    protected abstract ArrayList<MCliente> obterClientes() throws RegraNegocioException;

    protected abstract ArrayList<MEstoque> obterEstoques() throws RegraNegocioException;

    protected abstract ArrayList<MProduto> obterProdutos() throws RegraNegocioException;

    protected abstract ArrayList<MVendedor> obterVendedores() throws RegraNegocioException;

    private ArrayList<MVenda> obterVendasEfetuadas() {
        return null;
    }

    private void importarClientes() throws RegraNegocioException {
        ArrayList<MCliente> mClientes = obterClientes();
        for (MCliente mCliente : mClientes) {
            clienteDAO.salvar(mCliente, null);
        }
    }

}
