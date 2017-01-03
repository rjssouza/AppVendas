package com.app.appvenda.entidade;

import android.content.Context;
import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.auxiliar.NomeTabela;
import com.app.bdframework.baseEntidade.Entidade;

@NomeTabela(nomeTabela = "tb_cliente")
public class Cliente extends Entidade<Integer> {

    public final static String ATIVO = "ativo";
    public final static String CNPJ = "cnpj";
    public final static String COORD_X = "coord_x";
    public final static String COORD_Y = "coord_y";
    public final static String CPF = "cpf";
    public final static String ENDERECO = "endereco";
    public final static String ID_CLIENTE = "id_cliente";
    public final static String COD_CLIENTE = "cod_cliente";
    public final static String NOME = "nome";
    public final static String RAZAO_SOCIAL = "razao_social";

    public Cliente(Cursor cursor) {
        super(cursor);
    }

    @Override
    public void complementarEntidade(Context context) {

    }

    @ChavePrimaria
    private int id_cliente;
    @ColunaTabela()
    private Integer cod_cliente;
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
    private Telefone fixo;
    private Telefone celular;

    public Integer getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(Integer cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public Telefone getFixo() {
        return fixo;
    }

    public void setFixo(Telefone fixo) {
        this.fixo = fixo;
    }

    public Telefone getCelular() {
        return celular;
    }

    public void setCelular(Telefone celular) {
        this.celular = celular;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getId_cliente() {
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
