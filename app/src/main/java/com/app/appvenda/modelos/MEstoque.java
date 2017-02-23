package com.app.appvenda.modelos;

/**
 * Created by Robson on 30/11/2016.
 */

public class MEstoque {

    private Long idEstoque;
    private Long idProduto;
    private int quantidade;
    private Double valorProduto;
    private Double valorFinal;
    private int alertaFalta;

    public Double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }

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

    public Long getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(Long idEstoque) {
        this.idEstoque = idEstoque;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
