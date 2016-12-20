package com.app.appvenda.exportadorVenda;

import com.app.appvenda.modelos.MCliente;
import com.app.appvenda.modelos.MEstoque;
import com.app.appvenda.modelos.MFormaPagamento;
import com.app.appvenda.modelos.MProduto;
import com.app.appvenda.modelos.MVendedor;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioException;

import java.util.ArrayList;

/**
 * Created by Robson on 13/12/2016.
 */

public interface IExportadorVendas {

    void obterClientes(EventoVoid<ArrayList<MCliente>> posPosExecucao) throws RegraNegocioException;

    void obterProdutos(EventoVoid<ArrayList<MProduto>> posPosExecucao) throws RegraNegocioException;

    void obterVendedores(EventoVoid<ArrayList<MVendedor>> posPosExecucao) throws RegraNegocioException;

    void obterFormaPagto(EventoVoid<ArrayList<MFormaPagamento>> posPosExecucao) throws RegraNegocioException;

    void efetuarTesteConexao() throws RegraNegocioException;

}
