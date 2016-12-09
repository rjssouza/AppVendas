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
import com.app.bdframework.excecoes.TratamentoExcecao;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robson on 30/11/2016.
 */

public class ExportadorVendasDropBox extends ExportadorVendas {

    MConfiguracao mConfiguracao;

    public ExportadorVendasDropBox(Context context) {
        super(context);
        this.mConfiguracao = this.configuracaoDAO.obterConfiguracao(EnumTipoConfiguracao.DROPBOX);
        try {
            if (this.mConfiguracao == null)
                throw new RegraNegocioException("", EnumTipoMensagem.ERRO);
        } catch (RegraNegocioException e) {
            TratamentoExcecao.registrarRegraNegocioExcecao(new RegraNegocioException("", EnumTipoMensagem.ERRO));
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    @Override
    protected ArrayList<MCliente> obterClientes() {
        try {
            String texto = obterTexto(this.mConfiguracao.getPastaCliente(), "file.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected ArrayList<MEstoque> obterEstoques() {
        return null;
    }

    @Override
    protected ArrayList<MProduto> obterProdutos() {
        return null;
    }

    @Override
    protected ArrayList<MVendedor> obterVendedores() {
        return null;
    }

    private String obterTexto(String pasta, String arquivo) throws IOException {
        List<String> tempArray = new ArrayList<String>();
        URI url = mConfiguracao.getEnderecoServico();
        URL url2 = new URL(url.toString() + "/" + pasta + "/" + arquivo);

        URLConnection con = url2.openConnection();
        InputStream stream = con.getInputStream();
        String cont = "";
        int ch;
        byte[] bytes = new byte[1];
        while ((ch = stream.read()) != -1) {
            bytes[0] = (byte) ch;
            cont = cont + new String(bytes);
        }

        stream.close();
        return cont;
    }
}
