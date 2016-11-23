package com.app.appvenda.entidade;

import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.baseEntidade.Entidade;

public class Venda extends Entidade {

    public Venda(Cursor cursor) {
        super(cursor);
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_status_venda() {
        return id_status_venda;
    }

    public void setId_status_venda(int id_status_venda) {
        this.id_status_venda = id_status_venda;
    }

    public boolean isSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(boolean sincronizado) {
        this.sincronizado = sincronizado;
    }

    @ChavePrimaria
    private int id_venda;
    @ColunaTabela
    private int id_cliente;
    @ColunaTabela
    private int id_vendedor;
    @ColunaTabela
    private int id_pedido;
    @ColunaTabela
    private int id_status_venda;
    @ColunaTabela
    private boolean sincronizado;
}
