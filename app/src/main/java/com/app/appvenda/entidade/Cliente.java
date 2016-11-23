package com.app.appvenda.entidade;

import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.baseEntidade.Entidade;

public class Cliente extends Entidade {

    public Cliente(Cursor cursor) {
        super(cursor);
    }

    @ChavePrimaria
    private int id_cliente;
    @ColunaTabela
    private String nome;
    @ColunaTabela
    private String razao_social;
    @ColunaTabela
    private Long cnpj;
    @ColunaTabela
    private Long cpf;
    @ColunaTabela
    private String endereco;
    @ColunaTabela
    private Long coord_x;
    @ColunaTabela
    private Long coord_y;
    @ColunaTabela
    private Boolean ativo;

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getCoord_x() {
        return coord_x;
    }

    public void setCoord_x(Long coord_x) {
        this.coord_x = coord_x;
    }

    public Long getCoord_y() {
        return coord_y;
    }

    public void setCoord_y(Long coord_y) {
        this.coord_y = coord_y;
    }

}
