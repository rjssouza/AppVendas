package com.app.appvenda.processos;

import android.app.Activity;
import android.content.Context;

import com.app.appvenda.dao.ClienteDAO;
import com.app.appvenda.dao.FormaPagamentoDAO;
import com.app.appvenda.dao.TipoPedidoDAO;
import com.app.appvenda.modelos.MItemSeletor;
import com.app.appvenda.processos.resultado.IRetornoCargaVendas;
import com.app.appvenda.utils.ProcessoTratamento;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.IRegraNegocio;
import com.app.bdframework.excecoes.RegraNegocioMensagem;
import com.app.bdframework.excecoes.TratamentoExcecao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Robson on 12/02/2017.
 */

public class ProcessoCargaVendas implements IRetornoCargaVendas, IRegraNegocio {

    private Activity activity;
    private int qtdProcessos = 3;
    private ExecutorService executor;

    private ClienteDAO clienteDAO;
    private FormaPagamentoDAO formaPagamentoDAO;
    private TipoPedidoDAO tipoPedidoDAO;

    private EventoVoid preCarga;
    private EventoVoid<RegraNegocioMensagem> erroProcessamento;
    private EventoVoid<IRetornoCargaVendas> posCarga;

    private List<MItemSeletor> listaClientes;
    private List<MItemSeletor> listaTipoPedido;
    private List<MItemSeletor> listaFormaPagamento;

    public ProcessoCargaVendas(Activity activity) {
        this.activity = activity;
        this.executor = Executors.newFixedThreadPool(5);

        TratamentoExcecao.registrarEventoRegraNegocio(this);
        configurarDAOs(activity.getBaseContext());
    }

    public void setPreCarga(EventoVoid preCarga) {
        this.preCarga = preCarga;
    }

    public void setErroProcessamento(EventoVoid<RegraNegocioMensagem> erroProcessamento) {
        this.erroProcessamento = erroProcessamento;
    }

    public void setPosCarga(EventoVoid<IRetornoCargaVendas> posCarga) {
        this.posCarga = posCarga;
    }


    public void efetuarCargaVendas() {
        try {
            executarMetodo(null, preCarga);
            this.executor.execute(new ProcessoTratamento() {
                @Override
                protected void executar() throws Exception {
                    carregarCliente();
                    carregarTipoPedido();
                    carregarFormaPagto();
                }
            });
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    @Override
    public List<MItemSeletor> getListaClientes() {
        return listaClientes;
    }

    @Override
    public List<MItemSeletor> getListaTipoPedido() {
        return listaTipoPedido;
    }

    @Override
    public List<MItemSeletor> getListaFormaPagamento() {
        return listaFormaPagamento;
    }

    @Override
    public void onPrimeiroAcesso() {

    }

    @Override
    public void executarEvento(RegraNegocioMensagem item) throws Exception {
        if (erroProcessamento != null)
            erroProcessamento.executarEvento(item);
    }

    private void carregarCliente() throws Exception {
        listaClientes = clienteDAO.obterTodosClientes();
        chamarProcesso();
    }

    private void carregarFormaPagto() throws Exception {
        listaFormaPagamento = formaPagamentoDAO.obterFormasPagamento();
        chamarProcesso();
    }

    private void carregarTipoPedido() throws Exception {
        listaTipoPedido = tipoPedidoDAO.obterTodosTiposPedidos();
        chamarProcesso();
    }

    private void chamarProcesso() throws Exception {
        qtdProcessos--;
        if (qtdProcessos <= 0) {
            executarMetodo(this, posCarga);
        }
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

    private void configurarDAOs(Context context) {
        clienteDAO = new ClienteDAO(context);
    }

}
