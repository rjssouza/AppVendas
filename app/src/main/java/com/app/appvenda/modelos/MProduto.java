package com.app.appvenda.modelos;

/**
 * Created by Robson on 30/11/2016.
 */

public class MProduto {

    private int idProduto;
    private String nome;
    private Boolean ativo;
    private int qtdLimite;
    private String foto;

    //TODO adicionar a tabela
    private int codProduto;

    //TODO remover da tabela
    private Double valorUnitario;
    private Double valorFinal;

    public Double getPercentualVendas() {
        return percentualVendas;
    }

    public void setPercentualVendas(Double percentualVendas) {
        this.percentualVendas = percentualVendas;
    }

    private Double percentualVendas;

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    private MEstoque mEstoque;

    public MEstoque getmEstoque() {
        return mEstoque;
    }

    public void setmEstoque(MEstoque mEstoque) {
        this.mEstoque = mEstoque;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public int getQtdLimite() {
        return qtdLimite;
    }

    public void setQtdLimite(int qtdLimite) {
        this.qtdLimite = qtdLimite;
    }

}
