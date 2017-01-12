package com.app.appvenda.entidade;

import android.content.Context;
import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.auxiliar.NomeTabela;
import com.app.bdframework.baseEntidade.Entidade;

@NomeTabela(nomeTabela = "tb_vendedor")
public class Vendedor extends Entidade<Integer> {

    public final static String ID_VENDEDOR = "id_vendedor";
    public final static String NOME = "nome";
    public final static String PERC_VENDA = "perc_venda";

    public Vendedor(Cursor cursor) {
        super(cursor);
    }

    @Override
    public void complementarEntidade(Context context) {

    }

    @ChavePrimaria
    private int id_vendedor;
    @ColunaTabela
    private String nome;
    @ColunaTabela
    private Double perc_venda;

    public Double getPerc_venda() {
        return perc_venda;
    }

    public void setPerc_venda(Double perc_venda) {
        this.perc_venda = perc_venda;
    }

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
