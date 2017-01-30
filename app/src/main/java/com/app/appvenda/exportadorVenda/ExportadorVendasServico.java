package com.app.appvenda.exportadorVenda;

import android.content.Context;

import com.app.appvenda.modelos.MCliente;
import com.app.appvenda.modelos.MConfiguracao;
import com.app.appvenda.modelos.MFormaPagamento;
import com.app.appvenda.modelos.MProduto;
import com.app.appvenda.modelos.MVendedor;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.TratamentoExcecao;

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
    public void obterClientes(EventoVoid<ArrayList<MCliente>> posPosExecucao) throws RegraNegocioException {
        executarPosExecucao(posPosExecucao);
    }

    @Override
    public void obterProdutos(EventoVoid<ArrayList<MProduto>> posPosExecucao) throws RegraNegocioException {
        executarPosExecucao(posPosExecucao);
    }

    @Override
    public void obterVendedores(EventoVoid<ArrayList<MVendedor>> posPosExecucao) throws RegraNegocioException {
        executarPosExecucao(posPosExecucao);
    }

    @Override
    public void obterFormaPagto(EventoVoid<ArrayList<MFormaPagamento>> posPosExecucao) throws RegraNegocioException {
        executarPosExecucao(posPosExecucao);
    }

    @Override
    public void efetuarTesteConexao() throws RegraNegocioException {
    }

    private <T> void executarPosExecucao(EventoVoid<ArrayList<T>> posPosExecucao) {
        try {
            posPosExecucao.executarEvento(null);
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }
}
