package com.app.appvenda.exportadorVenda;

import android.content.Context;

import com.app.appvenda.enums.EnumTipoConfiguracao;
import com.app.appvenda.modelos.MCliente;
import com.app.appvenda.modelos.MConfiguracao;
import com.app.appvenda.modelos.MEstoque;
import com.app.appvenda.modelos.MProduto;
import com.app.appvenda.modelos.MVendedor;
import com.app.bdframework.enums.EnumTipoMensagem;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Robson on 30/11/2016.
 */

public class ExportadorVendasDropBox extends ExportadorVendas {

    private MConfiguracao getmConfiguracao() throws RegraNegocioException {
        MConfiguracao mConfiguracao = null;
        if (mConfiguracao == null) {
            mConfiguracao = this.configuracaoDAO.obterConfiguracao(EnumTipoConfiguracao.DROPBOX);
            if (mConfiguracao == null)
                throw new RegraNegocioException("", EnumTipoMensagem.ERRO);
        }
        return mConfiguracao;
    }

    public ExportadorVendasDropBox(Context context) {
        super(context);

    }

    @Override
    protected ArrayList<MCliente> obterClientes() throws RegraNegocioException {
        String texto = obterTexto(this.getmConfiguracao().getPastaCliente(), "file.txt");
        return null;
    }

    @Override
    protected ArrayList<MEstoque> obterEstoques() throws RegraNegocioException {
        return null;
    }

    @Override
    protected ArrayList<MProduto> obterProdutos() throws RegraNegocioException {
        return null;
    }

    @Override
    protected ArrayList<MVendedor> obterVendedores() throws RegraNegocioException {
        return null;
    }

    private String obterTexto(String pasta, String arquivo) throws RegraNegocioException {
        List<String> tempArray = new ArrayList<String>();
        URI url = getmConfiguracao().getEnderecoCompleto(pasta, arquivo);
        SyncHttpClient asyncHttpClient = new SyncHttpClient();
        final String[] texto = {""};

        asyncHttpClient.get(context, url.toString(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                texto[0] = new String(responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return texto[0];
    }
}
