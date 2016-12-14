package com.app.appvenda.modelos;

import com.app.appvenda.enums.EnumTipoConfiguracao;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Robson on 30/11/2016.
 */

public class MConfiguracao {

    private Integer idConfiguracao;
    private EnumTipoConfiguracao tipoConfig;
    private String pastaFotos;
    private String enderecoServico;
    private String pastaCliente;
    private String pastaEstoque;
    private String pastaProduto;
    private String pastaVenda;
    private String pastaVendedor;
    private boolean principal;

    public MConfiguracao() {
        idConfiguracao = null;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
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

    public String getEnderecoServico() {
        return enderecoServico;
    }

    public void setEnderecoServico(String enderecoServico) {
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

    public URI getEnderecoCompleto(String pasta, String arquivo) {
        try {
            return new URI((this.enderecoServico + "/" + pasta + "/" + arquivo).toLowerCase());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
