package com.app.appvenda.modelos;

/**
 * Created by Robson on 30/11/2016.
 */

public class MVenda {

    private int idVenda;
    private int idCliente;
    private int idVendedor;
    private int idPedido;
    private int idStatusVenda;
    private boolean sincronizado;

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdStatusVenda() {
        return idStatusVenda;
    }

    public void setIdStatusVenda(int idStatusVenda) {
        this.idStatusVenda = idStatusVenda;
    }

    public boolean isSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(boolean sincronizado) {
        this.sincronizado = sincronizado;
    }

}
