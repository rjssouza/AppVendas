package com.app.appvenda.exportadorVenda;

import android.content.Context;

import com.app.appvenda.DAO.ConfiguracaoDAO;
import com.app.appvenda.entidade.Configuracao;
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

import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Robson on 30/11/2016.
 */

public class ExportadorVendasDropBox implements IExportadorVendas {

    private Context context;
    private MConfiguracao mConfiguracao;

    public ExportadorVendasDropBox(Context context, MConfiguracao mConfiguracao) {
        this.mConfiguracao = mConfiguracao;
        this.context = context;
    }

    @Override
    public ArrayList<MCliente> obterClientes() throws RegraNegocioException {
        String texto = obterTexto(this.mConfiguracao.getPastaCliente(), "cliente.txt");
        StringReader inputStreamReader = new StringReader(texto);

        return null;
    }

    @Override
    public ArrayList<MEstoque> obterEstoques() throws RegraNegocioException {
        String texto = obterTexto(this.mConfiguracao.getPastaEstoque(), "estoque.txt");

        return null;
    }

    @Override
    public ArrayList<MProduto> obterProdutos() throws RegraNegocioException {
        String texto = obterTexto(this.mConfiguracao.getPastaProduto(), "produto.txt");

        return null;
    }

    @Override
    public ArrayList<MVendedor> obterVendedores() throws RegraNegocioException {
        String texto = obterTexto(this.mConfiguracao.getPastaVendedor(), "vendedor.txt");

        return null;
    }

    @Override
    public void efetuarTesteConexao()  throws RegraNegocioException {
        String texto = obterTexto(this.mConfiguracao.getPastaCliente(), "cliente.txt");
        if(texto == ""){
            throw new RegraNegocioException("Url indisponivel favor verificar configuracao", EnumTipoMensagem.ERRO);
        }
    }

    private String obterTexto(String pasta, String arquivo) throws RegraNegocioException {
        List<String> tempArray = new ArrayList<String>();
        URI url = this.mConfiguracao.getEnderecoCompleto(pasta, arquivo);
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
