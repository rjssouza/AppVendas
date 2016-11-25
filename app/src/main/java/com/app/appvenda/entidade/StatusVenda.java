package com.app.appvenda.entidade;

import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.baseEntidade.Entidade;

public class StatusVenda extends Entidade {

    public final static String COD_STATUS = "cod_status";
    public final static String DESCR_STATUS = "descr_status";
    public final static String ID_STATUS_VENDA = "id_status_venda";

    public StatusVenda(Cursor cursor){
        super(cursor);
    }

    @ChavePrimaria
    private Integer id_status_venda;
    @ColunaTabela
    private Integer cod_status;
    @ColunaTabela
    private String descr_status;

    public Integer getId_status_venda() {
        return id_status_venda;
    }

    public void setId_status_venda(Integer id_status_venda) {
        this.id_status_venda = id_status_venda;
    }

    public Integer getCod_status() {
        return cod_status;
    }

    public void setCod_status(Integer cod_status) {
        this.cod_status = cod_status;
    }

    public String getDescr_status() {
        return descr_status;
    }

    public void setDescr_status(String descr_status) {
        this.descr_status = descr_status;
    }

}
