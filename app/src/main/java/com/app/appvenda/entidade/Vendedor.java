package com.app.appvenda.entidade;

import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.baseEntidade.Entidade;

public class Vendedor extends Entidade {

    public final static String ID_VENDEDOR = "id_vendedor";
    public final static String NOME = "nome";

    public Vendedor(Cursor cursor) {
        super(cursor);
    }

    @ChavePrimaria
    private int id_vendedor;
    @ColunaTabela
    private String nome;

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
