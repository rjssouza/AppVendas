package com.app.appvenda.exportadorVenda;

import android.content.Context;

import com.app.appvenda.modelos.MCliente;
import com.app.appvenda.modelos.MEstoque;
import com.app.appvenda.modelos.MProduto;
import com.app.appvenda.modelos.MVendedor;

import java.util.ArrayList;

/**
 * Created by Robson on 30/11/2016.
 */

public class ExportadorVendasDropBox extends ExportadorVendas {

    public ExportadorVendasDropBox(Context context) {
        super(context);
    }

    @Override
    protected ArrayList<MCliente> obterClientes() {
        return null;
    }

    @Override
    protected ArrayList<MEstoque> obterEstoques() {
        return null;
    }

    @Override
    protected ArrayList<MProduto> obterProdutos() {
        return null;
    }

    @Override
    protected ArrayList<MVendedor> obterVendedores() {
        return null;
    }

}
