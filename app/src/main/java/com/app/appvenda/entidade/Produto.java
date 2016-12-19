package com.app.appvenda.entidade;

import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.baseEntidade.Entidade;

public class Produto extends Entidade {

    public final static String ATIVO = "ativo";
    public final static String FOTO = "foto";
    public final static String ID_PRODUTO = "id_produto";
    public final static String NOME = "nome";
    public final static String QTD_LIMITE = "qtd_limite";
    public final static String VALOR_FINAL = "valor_final";
    public final static String VALOR_UNITARIO = "valor_unitario";

    public Produto(Cursor cursor){
        super(cursor);
    }

    @ChavePrimaria
    private int id_produto;
    @ColunaTabela
    private String nome;
    @ColunaTabela
    private Double valor_unitario;
    @ColunaTabela
    private String foto;
    @ColunaTabela
    private Double valor_final;
    @ColunaTabela
    private Boolean ativo;
    @ColunaTabela
    private int qtd_limite;

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(Double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getValor_final() {
        return valor_final;
    }

    public void setValor_final(Double valor_final) {
        this.valor_final = valor_final;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public int getQtd_limite() {
        return qtd_limite;
    }
    public void setQtd_limite(int qtd_limite) {
        this.qtd_limite = qtd_limite;
    }


}
