package com.app.appvenda.exportadorVenda;

import android.content.Context;

import com.app.appvenda.modelos.MCliente;
import com.app.appvenda.modelos.MConfiguracao;
import com.app.appvenda.modelos.MEstoque;
import com.app.appvenda.modelos.MProduto;
import com.app.appvenda.modelos.MVendedor;
import com.app.bdframework.excecoes.RegraNegocioException;

import java.util.ArrayList;

/**
 * Created by Robson on 13/12/2016.
 */

public class ExportadorVendasServico implements IExportadorVendas {

    private Context context;
    private MConfiguracao mConfiguracao;

    public ExportadorVendasServico(Context context, MConfiguracao mConfiguracao) {
        this.mConfiguracao = mConfiguracao;
        this.context = context;
    }

    @Override
    public ArrayList<MCliente> obterClientes() throws RegraNegocioException {
        return null;
    }

    @Override
    public ArrayList<MEstoque> obterEstoques() throws RegraNegocioException {
        return null;
    }

    @Override
    public ArrayList<MProduto> obterProdutos() throws RegraNegocioException {
        return null;
    }

    @Override
    public ArrayList<MVendedor> obterVendedores() throws RegraNegocioException {
        return null;
    }

    @Override
    public void efetuarTesteConexao() throws RegraNegocioException {

    }

}
