package com.app.appvenda.exportadorVenda;

import android.content.Context;

import com.app.appvenda.DAO.ClienteDAO;
import com.app.appvenda.DAO.ConfiguracaoDAO;
import com.app.appvenda.DAO.ProdutoDAO;
import com.app.appvenda.R;
import com.app.appvenda.enums.EnumTipoConfiguracao;
import com.app.appvenda.modelos._MCliente;
import com.app.appvenda.modelos.MConfiguracao;
import com.app.appvenda.modelos.MProduto;
import com.app.appvenda.modelos.MVenda;
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

    private Context context;
    private IExportadorVendas iExportadorVendas;
    private EventoVoid<Boolean> eventoProcessamento;
    private int qtdRequest = 0;

    public ExportadorVendas(Context context) {
        this.context = context;
        clienteDAO = new ClienteDAO(context);
        produtoDAO = new ProdutoDAO(context);
        configuracaoDAO = new ConfiguracaoDAO(context);

        clienteDAO.setEventoPosExecucao(eventoPosProcessamento());
        produtoDAO.setEventoPosExecucao(eventoPosProcessamento());
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

    public void setEventoProcessamento(EventoVoid<Boolean> eventoProcessamento) {
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
        importarClientes();
        importarProdutos();
    }

    private void exportarVendas() {
        ArrayList<MVenda> mVenda = obterVendasEfetuadas();
    }

    private ArrayList<MVenda> obterVendasEfetuadas() {
        return null;
    }

    private synchronized void importarClientes() throws RegraNegocioException {

        iExportadorVendas.obterClientes(new EventoVoid<ArrayList<_MCliente>>() {
            @Override
            public void executarEvento(ArrayList<_MCliente> item) throws Exception {
                qtdRequest++;
                for (_MCliente mCliente : item) {
                    clienteDAO.salvar(mCliente, null);
                }
            }
        });
    }

    private synchronized void importarProdutos() throws RegraNegocioException {
        iExportadorVendas.obterProdutos(new EventoVoid<ArrayList<MProduto>>() {
            @Override
            public void executarEvento(ArrayList<MProduto> item) throws Exception {
                qtdRequest++;
                for (MProduto mProduto : item) {
                    produtoDAO.salvar(mProduto, null);
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
                        eventoProcessamento.executarEvento(item);
                        produtoDAO.salvarBD();
                    }
                }
            }
        };
    }

}
