package com.app.appvenda.processos;

import android.app.Activity;
import android.content.Context;

import com.app.appvenda.dao.ClienteDAO;
import com.app.appvenda.modelos.MItemSeletor;
import com.app.appvenda.processos.resultado.IRetornoCargaVendas;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.IRegraNegocio;
import com.app.bdframework.excecoes.RegraNegocioMensagem;
import com.app.bdframework.excecoes.TratamentoExcecao;

import java.util.List;

/**
 * Created by Robson on 12/02/2017.
 */

public class ProcessoCargaVendas implements IRetornoCargaVendas, IRegraNegocio {

    private int qtdProcessos = 1;

    private ClienteDAO clienteDAO;

    private EventoVoid preCarga;
    private EventoVoid<RegraNegocioMensagem> erroProcessamento;
    private EventoVoid<IRetornoCargaVendas> posCarga;

    private Activity activity;

    public void setPreCarga(EventoVoid preCarga) {
        this.preCarga = preCarga;
    }

    public void setErroProcessamento(EventoVoid<RegraNegocioMensagem> erroProcessamento) {
        this.erroProcessamento = erroProcessamento;
    }

    public void setPosCarga(EventoVoid<IRetornoCargaVendas> posCarga) {
        this.posCarga = posCarga;
    }

    private List<MItemSeletor> listaClientes;

    public ProcessoCargaVendas(Activity activity) {
        this.activity = activity;
        TratamentoExcecao.registrarEventoRegraNegocio(this);

        configurarDAOs(activity.getBaseContext());
    }

    private void configurarDAOs(Context context) {
        clienteDAO = new ClienteDAO(context);
    }

    public void efetuarCargaVendas() {
        try {
            executarMetodo(null, preCarga);

            carregarCliente();
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    private void carregarCliente() throws Exception {
        listaClientes = clienteDAO.obterTodosClientes();
        chamarProcesso();
    }

    private void chamarProcesso() throws Exception {
        qtdProcessos--;
        if (qtdProcessos <= 0) {
            if (posCarga != null)
                posCarga.executarEvento(this);
        }
    }

    @Override
    public List<MItemSeletor> getListaClientes() {
        return listaClientes;
    }

    @Override
    public void onPrimeiroAcesso() {

    }

    @Override
    public void executarEvento(RegraNegocioMensagem item) throws Exception {
        if (erroProcessamento != null)
            erroProcessamento.executarEvento(item);
    }

    private <T> void executarMetodo(final T item, final EventoVoid<T> metodo) throws Exception {
        if (metodo != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        metodo.executarEvento(item);
                    } catch (Exception e) {
                        TratamentoExcecao.registrarExcecao(e);
                    } finally {
                        TratamentoExcecao.invocarEvento();
                    }
                }
            });

        }
    }
}
