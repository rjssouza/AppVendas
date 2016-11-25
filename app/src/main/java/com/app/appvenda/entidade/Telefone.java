package com.app.appvenda.entidade;

import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.baseEntidade.Entidade;

public class Telefone extends Entidade {

    public final static String ID_CLIENTE = "id_cliente";
    public final static String ID_TELEFONE = "id_telefone";
    public final static String ID_TIPO_TELEFONE = "id_tipo_telefone";
    public final static String TELEFONE = "telefone";

    public Telefone(Cursor cursor) {
        super(cursor);
    }

    @ChavePrimaria
    private int id_telefone;
    @ColunaTabela
    private int id_cliente;
    @ColunaTabela
    private int telefone;
    @ColunaTabela
    private int id_tipo_telefone;

    public int getId_telefone() {
        return id_telefone;
    }

    public void setId_telefone(int id_telefone) {
        this.id_telefone = id_telefone;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getId_tipo_telefone() {
        return id_tipo_telefone;
    }

    public void setId_tipo_telefone(int id_tipo_telefone) {
        this.id_tipo_telefone = id_tipo_telefone;
    }

}
