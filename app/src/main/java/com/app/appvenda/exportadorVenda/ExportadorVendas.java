package com.app.appvenda.exportadorVenda;

import android.content.Context;

import com.app.appvenda.dao.ClienteDAO;
import com.app.appvenda.dao.ConfiguracaoDAO;
import com.app.appvenda.dao.FormaPagamentoDAO;
import com.app.appvenda.dao.ProdutoDAO;
import com.app.appvenda.R;
import com.app.appvenda.dao.VendedorDAO;
import com.app.appvenda.enums.EnumTipoConfiguracao;
import com.app.appvenda.modelos.MCliente;
import com.app.appvenda.modelos.MConfiguracao;
import com.app.appvenda.modelos.MFormaPagamento;
import com.app.appvenda.modelos.MProduto;
import com.app.appvenda.modelos.MVenda;
import com.app.appvenda.modelos.MVendedor;
import com.app.bdframework.enums.EnumTipoMensagem;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.TratamentoExcecao;

import java.util.ArrayList;

/**
 * Created by Robson on 30/11/2016.
 */

public class ExportadorVendas {

    private ClienteDAO clienteDAO;
    private ConfiguracaoDAO configuracaoDAO;
    private ProdutoDAO produtoDAO;
    private FormaPagamentoDAO formaPagamentoDAO;
    private VendedorDAO vendedorDAO;

    private Context context;
    private IExportadorVendas iExportadorVendas;
    private EventoVoid<Boolean> eventoProcessamento;
    private int qtdRequest = 0;
    private boolean sucesso = true;

    public ExportadorVendas(Context context) {
        this.context = context;
        clienteDAO = new ClienteDAO(context);
        produtoDAO = new ProdutoDAO(context);
        vendedorDAO = new VendedorDAO(context);
        configuracaoDAO = new ConfiguracaoDAO(context);
        formaPagamentoDAO = new FormaPagamentoDAO(context);

        clienteDAO.setEventoPosExecucao(eventoPosProcessamento());
        produtoDAO.setEventoPosExecucao(eventoPosProcessamento());
        vendedorDAO.setEventoPosExecucao(eventoPosProcessamento());
        formaPagamentoDAO.setEventoPosExecucao(eventoPosProcessamento());
    }

    public void evetuarSincronizacao() {
        try {
            MConfiguracao mConfiguracao = getmConfiguracao();
            iExportadorVendas = mConfiguracao.getTipoConfig() == EnumTipoConfiguracao.DROPBOX ? new ExportadorVendasDropBox(context, mConfiguracao) : new ExportadorVendasServico(context, mConfiguracao);
            iExportadorVendas.efetuarTesteConexao();
            importarBaseDados();
        } catch (RegraNegocioException e) {
            TratamentoExcecao.registrarRegraNegocioExcecao(e);
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    public void setEventoPosProcessamento(EventoVoid<Boolean> eventoProcessamento) {
        this.eventoProcessamento = eventoProcessamento;
    }

    private MConfiguracao getmConfiguracao() throws RegraNegocioException {
        MConfiguracao mConfiguracao = null;
        if (mConfiguracao == null) {
            mConfiguracao = this.configuracaoDAO.obterConfiguracaoAtiva();
            if (mConfiguracao == null) {
                throw new RegraNegocioException(context.getResources().getString(R.string.msg_sem_config), EnumTipoMensagem.ERRO);
            }
        }
        return mConfiguracao;
    }

    private void importarBaseDados() throws RegraNegocioException {
        qtdRequest = 4;

        importarClientes();
        importarProdutos();
        importarFormaPagamento();
        importarVendedores();
    }

    private void exportarVendas() {
        ArrayList<MVenda> mVenda = obterVendasEfetuadas();
    }

    private ArrayList<MVenda> obterVendasEfetuadas() {
        return null;
    }

    private synchronized void importarClientes() throws RegraNegocioException {
        iExportadorVendas.obterClientes(new EventoVoid<ArrayList<MCliente>>() {
            @Override
            public void executarEvento(ArrayList<MCliente> item) throws Exception {
                for (MCliente mCliente : item) {
                    clienteDAO.salvar(mCliente, null);
                }
            }
        });
    }

    private synchronized void importarProdutos() throws RegraNegocioException {
        iExportadorVendas.obterProdutos(new EventoVoid<ArrayList<MProduto>>() {
            @Override
            public void executarEvento(ArrayList<MProduto> item) throws Exception {
                for (MProduto mProduto : item) {
                    produtoDAO.salvar(mProduto, null);
                }
            }
        });
    }

    private synchronized void importarFormaPagamento() throws RegraNegocioException {
        iExportadorVendas.obterFormaPagto(new EventoVoid<ArrayList<MFormaPagamento>>() {
            @Override
            public void executarEvento(ArrayList<MFormaPagamento> item) throws Exception {
                for (MFormaPagamento mFormaPagamento : item) {
                    formaPagamentoDAO.salvar(mFormaPagamento, null);
                }
            }
        });
    }

    private synchronized void importarVendedores() throws RegraNegocioException {
        iExportadorVendas.obterVendedores(new EventoVoid<ArrayList<MVendedor>>() {
            @Override
            public void executarEvento(ArrayList<MVendedor> item) throws Exception {
                for (MVendedor mVendedor : item) {
                    vendedorDAO.salvar(mVendedor, null);
                }
            }
        });
    }

    private EventoVoid<Boolean> eventoPosProcessamento() {
        return new EventoVoid<Boolean>() {
            @Override
            public synchronized void executarEvento(Boolean item) throws Exception {
                qtdRequest--;
                if (qtdRequest <= 0) {
                    if (eventoProcessamento != null) {
                        eventoProcessamento.executarEvento(sucesso);
                        produtoDAO.salvarBD();
                    }
                }
            }
        };
    }

}
