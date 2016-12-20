package com.app.appvenda.entidade;

import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.baseEntidade.Entidade;

public class Venda extends Entidade<Integer> {

    public final static String ID_CLIENTE = "id_cliente";
    public final static String ID_PEDIDO = "id_pedido";
    public final static String ID_STATUS_VENDA = "id_status_venda";
    public final static String ID_VENDA = "id_venda";
    public final static String ID_VENDEDOR = "id_vendedor";
    public final static String SINCRONIZADO = "sincronizado";

    public Venda(Cursor cursor) {
        super(cursor);
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

    public Integer getId_venda() {
        return id_venda;
    }

    public void setId_venda(Integer id_venda) {
        this.id_venda = id_venda;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(Integer id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Integer getId_status_venda() {
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

}
