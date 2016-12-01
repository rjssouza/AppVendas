package com.app.appvenda.exportadorVenda;

import android.content.Context;

import com.app.appvenda.DAO.ClienteDAO;
import com.app.appvenda.modelos.MCliente;
import com.app.appvenda.modelos.MEstoque;
import com.app.appvenda.modelos.MProduto;
import com.app.appvenda.modelos.MVenda;
import com.app.appvenda.modelos.MVendedor;

import java.util.ArrayList;

/**
 * Created by Robson on 30/11/2016.
 */

public abstract class ExportadorVendas {

    private ClienteDAO clienteDAO;

    protected Context context;

    ExportadorVendas(Context context) {
        this.context = context;
    }

    public void importarBaseDados() {
        importarClientes();
    }

    public void exportarVendas() {
        ArrayList<MVenda> mVenda = obterVendasEfetuadas();

    }

    protected abstract ArrayList<MCliente> obterClientes();

    protected abstract ArrayList<MEstoque> obterEstoques();

    protected abstract ArrayList<MProduto> obterProdutos();

    protected abstract ArrayList<MVendedor> obterVendedores();

    private ArrayList<MVenda> obterVendasEfetuadas() {
        return null;
    }

    private void importarClientes() {
        ArrayList<MCliente> mClientes = obterClientes();
        for (MCliente mCliente : mClientes) {
            clienteDAO.salvar(mCliente, null);
        }
    }

}
