package com.app.appvenda.entidade;

import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.baseEntidade.Entidade;

public class TipoTelefone extends Entidade{

    public TipoTelefone(Cursor cursor) {
        super(cursor);
    }

    public int getId_tipo_telefone() {
        return id_tipo_telefone;
    }

    public void setId_tipo_telefone(int id_tipo_telefone) {
        this.id_tipo_telefone = id_tipo_telefone;
    }

    public String getDescr_tipo_telefone() {
        return descr_tipo_telefone;
    }

    public void setDescr_tipo_telefone(String descr_tipo_telefone) {
        this.descr_tipo_telefone = descr_tipo_telefone;
    }

    @ChavePrimaria
    private int id_tipo_telefone;

    private String descr_tipo_telefone;

}
