package com.app.appvenda.entidade;

import android.content.Context;
import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.auxiliar.NomeTabela;
import com.app.bdframework.baseEntidade.Entidade;

@NomeTabela(nomeTabela = "tb_telefone")
public class Telefone extends Entidade<Long> {

    public final static String ID_CLIENTE = "id_cliente";
    public final static String ID_TIPO_TELEFONE = "id_tipo_telefone";
    public final static String TELEFONE = "telefone";

    public Telefone(Cursor cursor) {
        super(cursor);
    }

    @Override
    public void complementarEntidade(Context context) {

    }

    @ChavePrimaria
    @ColunaTabela
    private long telefone;
    @ColunaTabela
    private Long id_cliente;
    @ColunaTabela
    private int id_tipo_telefone;

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public int getId_tipo_telefone() {
        return id_tipo_telefone;
    }

    public void setId_tipo_telefone(int id_tipo_telefone) {
        this.id_tipo_telefone = id_tipo_telefone;
    }

}
