package com.app.appvenda.entidade;

import android.content.Context;
import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.auxiliar.NomeTabela;
import com.app.bdframework.baseEntidade.Entidade;

@NomeTabela(nomeTabela = "tb_tipo_pedido")
public class TipoPedido extends Entidade<Integer> {

    public final static String COD_TIPO_PEDIDO = "cod_tipo_pedido";
    public final static String DESCR_TIPO_PEDIDO = "descr_tipo_pedido";
    public final static String ID_TIPO_PEDIDO = "id_tipo_pedido";

    public TipoPedido(Cursor cursor) {
        super(cursor);
    }

    @Override
    public void complementarEntidade(Context context) {

    }

    @ChavePrimaria
    private int id_tipo_pedido;
    @ColunaTabela
    private int cod_tipo_pedido;
    @ColunaTabela
    private String descr_tipo_pedido;

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

}
