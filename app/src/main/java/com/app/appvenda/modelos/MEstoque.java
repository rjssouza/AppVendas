package com.app.appvenda.modelos;

/**
 * Created by Robson on 30/11/2016.
 */

public class MEstoque {

    private int idEstoque;
    private int idProduto;
    private int quantidade;

    public Double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }

    //TODO Adicionar a tabela
    private Double valorProduto;
    private Double valorFinal;
    private int alertaFalta;

    public int getAlertaFalta() {
        return alertaFalta;
    }

    public void setAlertaFalta(int alertaFalta) {
        this.alertaFalta = alertaFalta;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
