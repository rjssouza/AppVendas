package com.app.appvenda.entidade;

import android.database.Cursor;

import com.app.bdframework.baseEntidade.Entidade;

/**
 * Created by Robson on 30/11/2016.
 */

public class Configuracao extends Entidade {

    public final static String ID_CONFIGURACAO = "id_configuracao";
    public final static String ENDERECO_ATIVO = "endereco_servico";
    public final static String PASTA_FOTOS = "pasta_fotos";
    public final static String TIPO_CONFIG = "tipo_config";
    public final static String PASTA_CLIENTE = "pasta_cliente";
    public final static String PASTA_ESTOQUE = "pasta_estoque";
    public final static String PASTA_PRODUTO = "pasta_produto" ;
    public final static String PASTA_VENDA = "pasta_venda";
    public final static String PASTA_VENDEDOR = "pasta_vendedor";

    public Configuracao(Cursor cursor) {
        super(cursor);
    }

    private Integer id_configuracao;
    private String endereco_servico;
    private String pasta_fotos;
    private Short tipo_config;
    private String pasta_cliente;
    private String pasta_estoque;
    private String pasta_produto ;
    private String pasta_venda;
    private String pasta_vendedor;

    public Integer getId_configuracao() {
        return id_configuracao;
    }

    public void setId_configuracao(Integer id_configuracao) {
        this.id_configuracao = id_configuracao;
    }

    public String getEndereco_servico() {
        return endereco_servico;
    }

    public void setEndereco_servico(String endereco_servico) {
        this.endereco_servico = endereco_servico;
    }

    public String getPasta_fotos() {
        return pasta_fotos;
    }

    public void setPasta_fotos(String pasta_fotos) {
        this.pasta_fotos = pasta_fotos;
    }

    public Short getTipo_config() {
        return tipo_config;
    }

    public void setTipo_config(Short tipo_config) {
        this.tipo_config = tipo_config;
    }

    public String getPasta_cliente() {
        return pasta_cliente;
    }

    public void setPasta_cliente(String pasta_cliente) {
        this.pasta_cliente = pasta_cliente;
    }

    public String getPasta_estoque() {
        return pasta_estoque;
    }

    public void setPasta_estoque(String pasta_estoque) {
        this.pasta_estoque = pasta_estoque;
    }

    public String getPasta_produto() {
        return pasta_produto;
    }

    public void setPasta_produto(String pasta_produto) {
        this.pasta_produto = pasta_produto;
    }

    public String getPasta_venda() {
        return pasta_venda;
    }

    public void setPasta_venda(String pasta_venda) {
        this.pasta_venda = pasta_venda;
    }

    public String getPasta_vendedor() {
        return pasta_vendedor;
    }

    public void setPasta_vendedor(String pasta_vendedor) {
        this.pasta_vendedor = pasta_vendedor;
    }

}