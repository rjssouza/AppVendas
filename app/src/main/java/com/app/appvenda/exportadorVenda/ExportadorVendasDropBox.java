package com.app.appvenda.exportadorVenda;

import android.content.Context;

import com.app.appvenda.modelos.MCliente;
import com.app.appvenda.modelos.MConfiguracao;
import com.app.appvenda.modelos.MEstoque;
import com.app.appvenda.modelos.MFormaPagamento;
import com.app.appvenda.modelos.MProduto;
import com.app.appvenda.modelos.MVendedor;
import com.app.bdframework.enums.EnumTipoMensagem;
import com.app.bdframework.eventos.EventoRetorno;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.app.bdframework.servico.ClienteHttpAssincrono;
import com.app.bdframework.servico.CustomResponseHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.MessageConstraintException;

/**
 * Created by Robson on 30/11/2016.
 */

public class ExportadorVendasDropBox implements IExportadorVendas {

    private Context context;
    private MConfiguracao mConfiguracao;
    private boolean sucessoConexao = false;
    private ClienteHttpAssincrono clienteHttpAssincrono;

    private final String F_CLIENTE = "cliente.txt";
    private final String F_ESTOQUE = "estoque.txt";
    private final String F_PRODUTO = "produto.txt";
    private final String F_VENDEDORES = "vendedores.txt";
    private final String F_FORMA_PAGTO = "formaPagamento.txt";

    public ExportadorVendasDropBox(Context context, MConfiguracao mConfiguracao) {
        this.mConfiguracao = mConfiguracao;
        this.context = context;
        this.clienteHttpAssincrono = new ClienteHttpAssincrono();
    }

    @Override
    public void obterClientes(EventoVoid<ArrayList<MCliente>> posPosExecucao) throws RegraNegocioException {
        URI uri = mConfiguracao.getEnderecoCompleto(mConfiguracao.getPastaCliente(), F_CLIENTE);
        obterTexto(uri, posPosExecucao, new EventoRetorno<String[], MCliente>() {
            @Override
            public MCliente executarEvento(String[] strings) {
                MCliente mCliente = new MCliente();
                mCliente.setId(Integer.parseInt(strings[8]));
                mCliente.setNome(strings[13]);
                mCliente.setNomeFantasia(strings[14]);
                mCliente.setCnpj(Long.parseLong(strings[7]));
                mCliente.setAtivo(Boolean.parseBoolean(strings[0]));
                if (!strings[17].toString().replaceAll("\\D+", "").equals(""))
                    mCliente.setCelular(Long.parseLong(strings[17].replaceAll("\\D+", "")));
                if (!strings[18].toString().replaceAll("\\D+", "").equals(""))
                    mCliente.setCelular(Long.parseLong(strings[18].replaceAll("\\D+", "")));
                return mCliente;
            }
        });
    }

    private void obterEstoques(EventoVoid<ArrayList<MEstoque>> posPosExecucao) throws RegraNegocioException {
        URI uri = mConfiguracao.getEnderecoCompleto(mConfiguracao.getPastaEstoque(), F_ESTOQUE);
        obterTexto(uri, posPosExecucao, new EventoRetorno<String[], MEstoque>() {
            @Override
            public MEstoque executarEvento(String[] strings) {
                MEstoque mEstoque = new MEstoque();
                mEstoque.setIdEstoque(Integer.parseInt(strings[0]));
                mEstoque.setIdProduto(Integer.parseInt(strings[4]));
                mEstoque.setQuantidade(Integer.parseInt(strings[2]));
                mEstoque.setValorFinal(Double.parseDouble(strings[6]));
                mEstoque.setValorProduto(Double.parseDouble(strings[3]));
                return mEstoque;
            }
        });
    }

    @Override
    public void obterProdutos(final EventoVoid<ArrayList<MProduto>> posPosExecucao) throws RegraNegocioException {
        final URI uri = mConfiguracao.getEnderecoCompleto(mConfiguracao.getPastaEstoque(), F_PRODUTO);
        final List<MEstoque> estoques = new ArrayList<>();
        obterEstoques(new EventoVoid<ArrayList<MEstoque>>() {
            @Override
            public void executarEvento(ArrayList<MEstoque> item) throws Exception {
                estoques.addAll(item);

                obterTexto(uri, posPosExecucao, new EventoRetorno<String[], MProduto>() {
                    @Override
                    public MProduto executarEvento(String[] strings) {
                        MProduto mProduto = new MProduto();
                        mProduto.setIdProduto(Integer.parseInt(strings[1]));
                        mProduto.setNome(strings[4]);
                        mProduto.setCodProduto(Integer.parseInt(strings[3]));
                        mProduto.setAtivo(Boolean.parseBoolean(strings[0]));
                        mProduto.setFoto(strings[3]);
                        mProduto.setQtdLimite(Integer.parseInt(strings[8]));
                        mProduto.setValorFinal(Double.parseDouble(strings[9]));
                        mProduto.setPercentualVendas(Double.parseDouble(strings[7]));

                        for (MEstoque mEstoque : estoques) {
                            if (mEstoque.getIdProduto() == mProduto.getIdProduto())
                                mProduto.setmEstoque(mEstoque);
                        }
                        return mProduto;
                    }
                });
            }
        });


    }

    @Override
    public void obterVendedores(EventoVoid<ArrayList<MVendedor>> posPosExecucao) throws RegraNegocioException {
        URI uri = mConfiguracao.getEnderecoCompleto(mConfiguracao.getPastaEstoque(), F_VENDEDORES);
        obterTexto(uri, posPosExecucao, new EventoRetorno<String[], MVendedor>() {
            @Override
            public MVendedor executarEvento(String[] strings) {
                return null;
            }
        });
    }

    @Override
    public void obterFormaPagto(EventoVoid<ArrayList<MFormaPagamento>> posPosExecucao) throws RegraNegocioException {
        URI uri = mConfiguracao.getEnderecoCompleto(mConfiguracao.getPastaEstoque(), F_FORMA_PAGTO);
        obterTexto(uri, posPosExecucao, new EventoRetorno<String[], MFormaPagamento>() {
            @Override
            public MFormaPagamento executarEvento(String[] strings) {
                return null;
            }
        });

    }

    @Override
    public void efetuarTesteConexao() throws RegraNegocioException {

    }

    public <TRetorno> void obterTexto(URI uri, final EventoVoid<ArrayList<TRetorno>> posPosExecucao, final EventoRetorno<String[], TRetorno> converterPara) {
        clienteHttpAssincrono.get(context, uri.toString(), new CustomResponseHandler<String>(String.class) {
            @Override
            protected void posSucesso(int statusCode, String s) throws Exception {
                posPosExecucao.executarEvento(interpretarLinhas(s, converterPara));
            }

            @Override
            protected void posErro(int statusCode, Header[] headers, byte[] responseBody, Throwable error) throws RegraNegocioException {
                throw new RegraNegocioException(error.getMessage(), EnumTipoMensagem.ERRO);
            }
        });
    }

    private <TRetorno> ArrayList<TRetorno> interpretarLinhas(String texto, EventoRetorno<String[], TRetorno> converterPara) throws Exception {
        ArrayList<TRetorno> retornoList = new ArrayList<>();
        if (converterPara != null) {
            String line;
            BufferedReader br = new BufferedReader(new StringReader(texto));

            while ((line = br.readLine()) != null) {
                retornoList.add(converterPara.executarEvento(line.split("\\|")));
            }

        }
        return retornoList;
    }

}
