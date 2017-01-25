package com.app.appvenda.entidade;

import android.content.Context;
import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.auxiliar.NomeTabela;
import com.app.bdframework.baseEntidade.Entidade;

@NomeTabela(nomeTabela = "tb_vendedor")
public class Vendedor extends Entidade<Integer> implements IDescricaoEntidade {

    public final static String ID_VENDEDOR = "id_vendedor";
    public final static String NOME = "nome";
    public final static String PERC_VENDA = "perc_venda";
    public final static String ATIVO = "ativo";

    public Vendedor(Cursor cursor) {
        super(cursor);
    }

    @Override
    public void complementarEntidade(Context context) {

    }

    @ChavePrimaria
    private Integer id_vendedor;
    @ColunaTabela
    private String nome;
    @ColunaTabela
    private Double perc_venda;
    @ColunaTabela
    private Boolean ativo;

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Double getPerc_venda() {
        return perc_venda;
    }

    public void setPerc_venda(Double perc_venda) {
        this.perc_venda = perc_venda;
    }

    public Integer getId_vendedor() {
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

    @Override
    public int getIdentificador() {
        return id_vendedor;
    }

    @Override
    public String getDescricao() {
        return nome;
    }

    @Override
    public String getCodigo() {
        return id_vendedor.toString();
    }
}
