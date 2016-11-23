package com.app.appvenda.entidade;

import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.baseEntidade.Entidade;

public class TipoPedido extends Entidade {
    public TipoPedido(Cursor cursor) {
        super(cursor);
    }

    public int getId_tipo_pedido() {
        return id_tipo_pedido;
    }

    public void setId_tipo_pedido(int id_tipo_pedido) {
        this.id_tipo_pedido = id_tipo_pedido;
    }

    public int getCod_tipo_pedido() {
        return cod_tipo_pedido;
    }

    public void setCod_tipo_pedido(int cod_tipo_pedido) {
        this.cod_tipo_pedido = cod_tipo_pedido;
    }

    public String getDescr_tipo_pedido() {
        return descr_tipo_pedido;
    }

    public void setDescr_tipo_pedido(String descr_tipo_pedido) {
        this.descr_tipo_pedido = descr_tipo_pedido;
    }

    @ChavePrimaria
    private int id_tipo_pedido;

    private int cod_tipo_pedido;

    private String descr_tipo_pedido;

}
