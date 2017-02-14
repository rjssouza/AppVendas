package com.app.appvenda.modelos;

public class MCliente {

    public MCliente(){
    }

    private  int id;
    private String nome;
    private String nomeFantasia;
    private long cnpj;
    private long cpf;
    private long coord_x;
    private long coord_y;
    private long telefone;
    private long celular;
    private boolean ativo;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public long getCoord_x() {
        return coord_x;
    }

    public void setCoord_x(long coord_x) {
        this.coord_x = coord_x;
    }

    public long getCoord_y() {
        return coord_y;
    }

    public void setCoord_y(long coord_y) {
        this.coord_y = coord_y;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

}
