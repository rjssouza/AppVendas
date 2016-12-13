package com.app.appvenda.exportadorVenda;

import android.content.Context;

import com.app.appvenda.DAO.ClienteDAO;
import com.app.appvenda.DAO.ConfiguracaoDAO;
import com.app.appvenda.enums.EnumTipoConfiguracao;
import com.app.appvenda.modelos.MCliente;
import com.app.appvenda.modelos.MConfiguracao;
import com.app.appvenda.modelos.MVenda;
import com.app.bdframework.enums.EnumTipoMensagem;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.TratamentoExcecao;

import java.util.ArrayList;

/**
 * Created by Robson on 30/11/2016.
 */

public class ExportadorVendas {

    private ClienteDAO clienteDAO;
    private ConfiguracaoDAO configuracaoDAO;
    private Context context;
    private IExportadorVendas iExportadorVendas;

    public ExportadorVendas(Context context) {
        this.context = context;
        clienteDAO = new ClienteDAO(context);
        configuracaoDAO = new ConfiguracaoDAO(context);
    }

    private MConfiguracao getmConfiguracao() throws RegraNegocioException {
        MConfiguracao mConfiguracao = null;
        if (mConfiguracao == null) {
            mConfiguracao = this.configuracaoDAO.obterConfiguracao(EnumTipoConfiguracao.DROPBOX);
            if (mConfiguracao == null)
                throw new RegraNegocioException("", EnumTipoMensagem.ERRO);
        }
        return mConfiguracao;
    }

    public void evetuarSincronizacao() {
        try {
            MConfiguracao mConfiguracao = getmConfiguracao();
            this.iExportadorVendas = mConfiguracao.getTipoConfig() == EnumTipoConfiguracao.DROPBOX ? new ExportadorVendasDropBox(context, mConfiguracao) : null;
            importarBaseDados();
            exportarVendas();
        } catch (RegraNegocioException e) {
            TratamentoExcecao.registrarRegraNegocioExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    private void importarBaseDados() throws RegraNegocioException {
        importarClientes();
    }

    private void exportarVendas() {
        ArrayList<MVenda> mVenda = obterVendasEfetuadas();
    }


    private ArrayList<MVenda> obterVendasEfetuadas() {
        return null;
    }

    private void importarClientes() throws RegraNegocioException {
        ArrayList<MCliente> mClientes = iExportadorVendas.obterClientes();
        for (MCliente mCliente : mClientes) {
            clienteDAO.salvar(mCliente, null);
        }
    }

}
