package com.app.appvenda.entidade;

import android.content.Context;
import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.auxiliar.NomeTabela;
import com.app.bdframework.baseEntidade.Entidade;

@NomeTabela(nomeTabela = "tb_tipo_telefone")
public class TipoTelefone extends Entidade<Integer> {

    public final static String DESCR_TIPO_TELEFONE = "descr_tipo_telefone";
    public final static String ID_TIPO_TELEFONE = "id_tipo_telefone";

    public TipoTelefone(Cursor cursor) {
        super(cursor);
    }

    @Override
    public void complementarEntidade(Context context) {

    }

    @ChavePrimaria
    private Long id_tipo_telefone;
    @ColunaTabela
    private String descr_tipo_telefone;

    public Long getId_tipo_telefone() {
        return id_tipo_telefone;
    }

    public void setId_tipo_telefone(Long id_tipo_telefone) {
        this.id_tipo_telefone = id_tipo_telefone;
    }

    public String getDescr_tipo_telefone() {
        return descr_tipo_telefone;
    }

    public void setDescr_tipo_telefone(String descr_tipo_telefone) {
        this.descr_tipo_telefone = descr_tipo_telefone;
    }

}
