package com.app.appvenda.entidade;

import android.content.Context;
import android.database.Cursor;

import com.app.appvenda.enums.EnumTipoTelefone;
import com.app.appvenda.repositorio.RPTelefone;
import com.app.bdframework.IExecutorQuery;
import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.auxiliar.NomeTabela;
import com.app.bdframework.baseEntidade.Entidade;

@NomeTabela(nomeTabela = "tb_cliente")
public class Cliente extends Entidade<Integer> implements IDescricaoEntidade {

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
    public final static String EMAIL = "email";

    IExecutorQuery<Telefone> telefoneIExecutorQuery;

    public Cliente(Cursor cursor) {
        super(cursor);
    }

    @Override
    public void complementarEntidade(Context context) {
        telefoneIExecutorQuery = new RPTelefone(context);

        fixo = obterTelefone(EnumTipoTelefone.FIXO);
        celular = obterTelefone(EnumTipoTelefone.CELULAR);
    }

    private Telefone obterTelefone(EnumTipoTelefone tipoTelefone) {
        Telefone telefone = telefoneIExecutorQuery.executarUnico(Telefone.getTodasColunas(Telefone.class), Telefone.ID_CLIENTE + "=? AND " + Telefone.ID_TIPO_TELEFONE + "=?", id_cliente.toString(), tipoTelefone.getNumVal().toString());
        if (telefone == null) {
            telefone = new Telefone(null);
        }
        return telefone;
    }

    @ChavePrimaria
    private Long id_cliente;
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
    private String email;
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

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Long getIdentificador() {
        return id_cliente;
    }

    @Override
    public String getDescricao() {
        return razao_social;
    }

    @Override
    public String getCodigo() {
        return cod_cliente.toString();
    }
}
