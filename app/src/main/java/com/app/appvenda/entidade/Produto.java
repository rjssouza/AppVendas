package com.app.appvenda.entidade;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.repositorio.RPEstoque;
import com.app.bdframework.IExecutorQuery;
import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.auxiliar.NomeTabela;
import com.app.bdframework.baseEntidade.Entidade;

@NomeTabela(nomeTabela = "tb_produto")
public class Produto extends Entidade<Integer> {

    public final static String ATIVO = "ativo";
    public final static String FOTO = "foto";
    public final static String ID_PRODUTO = "id_produto";
    public final static String NOME = "nome";
    public final static String QTD_LIMITE = "qtd_limite";

    public Produto(Cursor cursor) {
        super(cursor);
    }

    @ChavePrimaria
    private Long id_produto;
    @ColunaTabela
    private String nome;
    @ColunaTabela
    private String foto;
    @ColunaTabela
    private Boolean ativo;
    @ColunaTabela
    private int qtd_limite;
    @ColunaTabela
    private Integer cod_produto;

    private Estoque estoque;

    @Override
    public void complementarEntidade(Context context) {
        IExecutorQuery<Estoque> iExecutorQueryEstoque = new RPEstoque(context);
        estoque = iExecutorQueryEstoque.executarUnico(Estoque.getTodasColunas(Estoque.class), Estoque.ID_PRODUTO + "=?", false, id_produto.toString());
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Long getId_produto() {
        return id_produto;
    }

    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public int getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(int cod_produto) {
        this.cod_produto = cod_produto;
    }

}
