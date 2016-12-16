package com.app.appvenda.exportadorVenda;

import android.content.Context;

import com.app.appvenda.DAO.ConfiguracaoDAO;
import com.app.appvenda.R;
import com.app.appvenda.entidade.Configuracao;
import com.app.appvenda.enums.EnumTipoConfiguracao;
import com.app.appvenda.modelos.MCliente;
import com.app.appvenda.modelos.MConfiguracao;
import com.app.appvenda.modelos.MEstoque;
import com.app.appvenda.modelos.MProduto;
import com.app.appvenda.modelos.MVendedor;
import com.app.bdframework.enums.EnumTipoMensagem;
import com.app.bdframework.eventos.EventoRetorno;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.servico.AppVendasResponseHandler;
import com.app.bdframework.servico.ClienteHttpAssincrono;
import com.app.bdframework.utils.ResourceUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
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
    private boolean sucessoConexao = false;
    private ClienteHttpAssincrono clienteHttpAssincrono;

    private String cliente;
    private String estoques;
    private String produtos;
    private String vendedores;


    public ExportadorVendasDropBox(Context context, MConfiguracao mConfiguracao) {
        this.mConfiguracao = mConfiguracao;
        this.context = context;
        this.clienteHttpAssincrono = new ClienteHttpAssincrono();
    }

    @Override
    public ArrayList<MCliente> obterClientes() throws RegraNegocioException {
        return tratarRetorno(cliente, new EventoRetorno<String[], MCliente>() {
            @Override
            public MCliente executarEvento(String[] strings) {
                return null;
            }
        });
    }

    @Override
    public ArrayList<MEstoque> obterEstoques() throws RegraNegocioException {
        return tratarRetorno(estoques, new EventoRetorno<String[], MEstoque>() {
            @Override
            public MEstoque executarEvento(String[] strings) {
                return null;
            }
        });
    }

    @Override
    public ArrayList<MProduto> obterProdutos() throws RegraNegocioException {
        return tratarRetorno(produtos, new EventoRetorno<String[], MProduto>() {
            @Override
            public MProduto executarEvento(String[] strings) {
                return null;
            }
        });
    }

    @Override
    public ArrayList<MVendedor> obterVendedores() throws RegraNegocioException {
        return tratarRetorno(vendedores, new EventoRetorno<String[], MVendedor>() {
            @Override
            public MVendedor executarEvento(String[] strings) {
                return null;
            }
        });
    }

    @Override
    public void efetuarTesteConexao() throws RegraNegocioException {
        SyncHttpClient syncHttpClient = new SyncHttpClient();

        syncHttpClient.get(context, this.mConfiguracao.getEnderecoServico().toString(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                sucessoConexao = true;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                sucessoConexao = false;
            }
        });

        if (!sucessoConexao) {
            throw new RegraNegocioException(ResourceUtils.getString(context, R.string.msg_url_indisponivel), EnumTipoMensagem.ERRO);
        }
    }

    private void obterTexto(String valores, EventoVoid<Boolean> eventoVoid) throws RegraNegocioException {
        if (valores == null) {
            criarRequisicao(mConfiguracao.getPastaCliente(), "cliente.txt", new EventoVoid<String>() {
                @Override
                public void executarEvento(String item) {
                    cliente = item;
                }
            });
            criarRequisicao(mConfiguracao.getPastaEstoque(), "estoque.txt", new EventoVoid<String>() {
                @Override
                public void executarEvento(String item) {
                    estoques = item;
                }
            });
            criarRequisicao(mConfiguracao.getPastaProduto(), "produto.txt", new EventoVoid<String>() {
                @Override
                public void executarEvento(String item) {
                    produtos = item;
                }
            });
            criarRequisicao(mConfiguracao.getPastaVendedor(), "vendedor.txt", new EventoVoid<String>() {
                @Override
                public void executarEvento(String item) {
                    vendedores = item;
                }
            });

            clienteHttpAssincrono.setOnPostRequest(eventoVoid);
        }
    }

    private void criarRequisicao(String pasta, String arquivo, final EventoVoid<String> preencher) {
        clienteHttpAssincrono.get(context, mConfiguracao.getEnderecoCompleto(pasta, arquivo).toString(), new AppVendasResponseHandler<String>() {
            @Override
            protected void posSucesso(int statusCode, String s) throws Exception {
                preencher.executarEvento(s);
            }

            @Override
            protected void posErro(int statusCode, Header[] headers, byte[] responseBody, Throwable error) throws RegraNegocioException {
                throw new RegraNegocioException(error.getMessage(), EnumTipoMensagem.ERRO);
            }
        });
    }

    private <TRetorno> ArrayList<TRetorno> tratarRetorno(final String texto, final EventoRetorno<String[], TRetorno> eventoRetorno) throws RegraNegocioException {
        final ArrayList<TRetorno>[] retornoList = new ArrayList[]{new ArrayList<>()};

        obterTexto(texto, new EventoVoid<Boolean>() {
            @Override
            public void executarEvento(Boolean item) throws RegraNegocioException {
                if (item)
                    retornoList[0] = tratarRetorno(texto, eventoRetorno);
            }
        });

        retornoList[0] = interpretarLinhas(texto, eventoRetorno);

        return retornoList[0];
    }

    private <TRetorno> ArrayList<TRetorno> interpretarLinhas(String texto, EventoRetorno<String[], TRetorno> eventoRetorno) {
        ArrayList<TRetorno> retornoList = new ArrayList<>();
        if (eventoRetorno != null) {
            String line;
            BufferedReader br = new BufferedReader(new StringReader(texto));

            try {
                while ((line = br.readLine()) != null) {
                    retornoList.add(eventoRetorno.executarEvento(line.split("\\|")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return retornoList;
    }

}
