package com.app.appvenda.entidade;

import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.baseEntidade.Entidade;

public class Produto extends Entidade {

    public Produto(Cursor cursor){
        super(cursor);
    }

    @ChavePrimaria
    private int id_produto;

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

    private String nome;

    private Double valor_unitario;

    private String foto;

    private Double valor_final;

    private Boolean ativo;

    private int qtd_limite;


}
