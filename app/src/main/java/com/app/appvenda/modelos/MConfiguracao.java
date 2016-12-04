package com.app.appvenda.modelos;

import com.app.appvenda.enums.EnumTipoConfiguracao;

import java.net.URI;

/**
 * Created by Robson on 30/11/2016.
 */

public class MConfiguracao {

    private Integer idConfiguracao;
    private EnumTipoConfiguracao tipoConfig;
    private URI enderecoServico;
    private String pastaFotos;
    private String pastaCliente;
    private String pastaEstoque;
    private String pastaProduto;
    private String pastaVenda;
    private String pastaVendedor;

    public MConfiguracao(){
        idConfiguracao = null;
    }

    public Integer getIdConfiguracao() {
        return idConfiguracao;
    }

    public void setIdConfiguracao(Integer idConfiguracao) {
        this.idConfiguracao = idConfiguracao;
    }

    public EnumTipoConfiguracao getTipoConfig() {
        return tipoConfig;
    }

    public void setTipoConfig(EnumTipoConfiguracao tipoConfig) {
        this.tipoConfig = tipoConfig;
    }

    public URI getEnderecoServico() {
        return enderecoServico;
    }

    public void setEnderecoServico(URI enderecoServico) {
        this.enderecoServico = enderecoServico;
    }

    public String getPastaFotos() {
        return pastaFotos;
    }

    public void setPastaFotos(String pastaFotos) {
        this.pastaFotos = pastaFotos;
    }

    public String getPastaCliente() {
        return pastaCliente;
    }

    public void setPastaCliente(String pastaCliente) {
        this.pastaCliente = pastaCliente;
    }

    public String getPastaEstoque() {
        return pastaEstoque;
    }

    public void setPastaEstoque(String pastaEstoque) {
        this.pastaEstoque = pastaEstoque;
    }

    public String getPastaProduto() {
        return pastaProduto;
    }

    public void setPastaProduto(String pastaProduto) {
        this.pastaProduto = pastaProduto;
    }

    public String getPastaVenda() {
        return pastaVenda;
    }

    public void setPastaVenda(String pastaVenda) {
        this.pastaVenda = pastaVenda;
    }

    public String getPastaVendedor() {
        return pastaVendedor;
    }

    public void setPastaVendedor(String pastaVendedor) {
        this.pastaVendedor = pastaVendedor;
    }

}
