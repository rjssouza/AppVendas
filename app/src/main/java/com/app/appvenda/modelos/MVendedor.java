package com.app.appvenda.modelos;

/**
 * Created by Robson on 30/11/2016.
 */

public class MVendedor {

    private int idVendedor;
    private String nome;
    private Double percVenda;

    public Double getPercVenda() {
        return percVenda;
    }

    public void setPercVenda(Double percVenda) {
        this.percVenda = percVenda;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
