package com.app.appvenda.entidade;

import android.content.Context;
import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.auxiliar.NomeTabela;
import com.app.bdframework.baseEntidade.Entidade;

@NomeTabela(nomeTabela = "tb_estoque")
public class Estoque extends Entidade<Integer> {

    public final static String ID_ESTOQUE = "id_estoque";
    public final static String ID_PRODUTO = "id_produto";
    public final static String QUANTIDADE = "quantidade";
    public final static String VALOR_PRODUTO = "valor_produto";
    public final static String VALOR_FINAL = "valor_final";
    public final static String ALERTA_FALTA = "alerta_falta";

    public Estoque(Cursor cursor) {
        super(cursor);
    }

    @Override
    public void complementarEntidade(Context context) {

    }

    @ChavePrimaria
    private Long id_estoque;
    @ColunaTabela
    private Long id_produto;
    @ColunaTabela
    private int quantidade;
    @ColunaTabela
    private Double valor_produto;
    @ColunaTabela
    private Double valor_final;
    @ColunaTabela
    private int alerta_falta;

    public Double getValor_produto() {
        return valor_produto;
    }

    public void setValor_produto(Double valor_produto) {
        this.valor_produto = valor_produto;
    }

    public Double getValor_final() {
        return valor_final;
    }

    public void setValor_final(Double valor_final) {
        this.valor_final = valor_final;
    }

    public int getAlerta_falta() {
        return alerta_falta;
    }

    public void setAlerta_falta(int alerta_falta) {
        this.alerta_falta = alerta_falta;
    }

    public Long getId_estoque() {
        return id_estoque;
    }

    public void setId_estoque(Long id_estoque) {
        this.id_estoque = id_estoque;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Long getId_produto() {
        return id_produto;
    }

    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }

}
