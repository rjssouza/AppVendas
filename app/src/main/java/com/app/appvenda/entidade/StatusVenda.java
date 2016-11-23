package com.app.appvenda.entidade;

import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.baseEntidade.Entidade;

public class StatusVenda extends Entidade {

    public StatusVenda(Cursor cursor){
        super(cursor);
    }

    public int getId_status_venda() {
        return id_status_venda;
    }

    public void setId_status_venda(int id_status_venda) {
        this.id_status_venda = id_status_venda;
    }

    public int getCod_status() {
        return cod_status;
    }

    public void setCod_status(int cod_status) {
        this.cod_status = cod_status;
    }

    public String getDescr_status() {
        return descr_status;
    }

    public void setDescr_status(String descr_status) {
        this.descr_status = descr_status;
    }

    @ChavePrimaria
    private int id_status_venda;

    private int cod_status;

    private String descr_status;
}
