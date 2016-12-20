package com.app.appvenda.entidade;

import android.content.Context;
import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.baseEntidade.Entidade;

public class FormaPagto extends Entidade<Integer> {

    public final static String COD_FORMA_PAGTO = "cod_forma_pagto";
    public final static String DESCR_FORMA_PAGTO = "descr_forma_pagto";
    public final static String ID_FORMA_PAGTO = "id_forma_pagto";
    public final static String VAL_PERC = "val_perc";

    public FormaPagto(Cursor cursor){
        super(cursor);
    }

    @Override
    public void complementarEntidade(Context context) {

    }

    @ColunaTabela
    private Double val_perc;
    @ChavePrimaria
    private int id_forma_pagto;
    @ColunaTabela
    private int cod_forma_pagto;
    @ColunaTabela
    private String descr_forma_pagto;

    public int getId_forma_pagto() {
        return id_forma_pagto;
    }

    public void setId_forma_pagto(int id_forma_pagto) {
        this.id_forma_pagto = id_forma_pagto;
    }

    public int getCod_forma_pagto() {
        return cod_forma_pagto;
    }

    public void setCod_forma_pagto(int cod_forma_pagto) {
        this.cod_forma_pagto = cod_forma_pagto;
    }

    public String getDescr_forma_pagto() {
        return descr_forma_pagto;
    }

    public void setDescr_forma_pagto(String descr_forma_pagto) {
        this.descr_forma_pagto = descr_forma_pagto;
    }

    public Double getVal_perc() {
        return val_perc;
    }

    public void setVal_perc(Double val_perc) {
        this.val_perc = val_perc;
    }

}
