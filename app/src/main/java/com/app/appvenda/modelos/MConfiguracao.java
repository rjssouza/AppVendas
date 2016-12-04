package com.app.appvenda.modelos;

import android.net.Uri;

import com.app.appvenda.enums.EnumTipoConfiguracao;

/**
 * Created by Robson on 30/11/2016.
 */

public class MConfiguracao {

    private Integer IdConfiguracao;
    private EnumTipoConfiguracao TipoConfig;
    private Uri EnderecoServico;
    private String PastaFotos;
    private String PastaCliente;
    private String PastaEstoque;
    private String PastaProduto;
    private String PastaVenda;
    private String PastaVendedor;

    public Integer getIdConfiguracao() {
        return IdConfiguracao;
    }

    public void setIdConfiguracao(Integer idConfiguracao) {
        IdConfiguracao = idConfiguracao;
    }

    public Uri getEnderecoServico() {
        return EnderecoServico;
    }

    public void setEnderecoServico(Uri enderecoServico) {
        EnderecoServico = enderecoServico;
    }

    public String getPastaFotos() {
        return PastaFotos;
    }

    public void setPastaFotos(String pastaFotos) {
        PastaFotos = pastaFotos;
    }

    public EnumTipoConfiguracao getTipoConfig() {
        return TipoConfig;
    }

    public void setTipoConfig(EnumTipoConfiguracao tipoConfig) {
        TipoConfig = tipoConfig;
    }

    public String getPastaCliente() {
        return PastaCliente;
    }

    public void setPastaCliente(String pastaCliente) {
        PastaCliente = pastaCliente;
    }

    public String getPastaEstoque() {
        return PastaEstoque;
    }

    public void setPastaEstoque(String pastaEstoque) {
        PastaEstoque = pastaEstoque;
    }

    public String getPastaProduto() {
        return PastaProduto;
    }

    public void setPastaProduto(String pastaProduto) {
        PastaProduto = pastaProduto;
    }

    public String getPastaVenda() {
        return PastaVenda;
    }

    public void setPastaVenda(String pastaVenda) {
        PastaVenda = pastaVenda;
    }

    public String getPastaVendedor() {
        return PastaVendedor;
    }

    public void setPastaVendedor(String pastaVendedor) {
        PastaVendedor = pastaVendedor;
    }

}
