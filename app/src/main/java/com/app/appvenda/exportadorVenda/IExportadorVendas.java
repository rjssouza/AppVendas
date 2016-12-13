package com.app.appvenda.exportadorVenda;

import com.app.appvenda.modelos.MCliente;
import com.app.appvenda.modelos.MEstoque;
import com.app.appvenda.modelos.MProduto;
import com.app.appvenda.modelos.MVendedor;
import com.app.bdframework.excecoes.RegraNegocioException;

import java.util.ArrayList;

/**
 * Created by Robson on 13/12/2016.
 */

public interface IExportadorVendas {

    ArrayList<MCliente> obterClientes() throws RegraNegocioException;

    ArrayList<MEstoque> obterEstoques() throws RegraNegocioException;

    ArrayList<MProduto> obterProdutos() throws RegraNegocioException;

    ArrayList<MVendedor> obterVendedores() throws RegraNegocioException;

}
