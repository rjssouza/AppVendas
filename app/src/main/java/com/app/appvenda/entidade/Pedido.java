package com.app.appvenda.entidade;

import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;
import com.app.bdframework.baseEntidade.Entidade;

public class Pedido extends Entidade {

    public final static String COORD_X = "coord_x";
    public final static String COORD_Y = "coord_y";
    public final static String ENDERECO = "endereco";
    public final static String ID_FORMA_PAGTO = "id_forma_pagto";
    public final static String ID_PEDIDO = "id_pedido";
    public final static String VALOR_TOTAL = "valor_total";

    public Pedido(Cursor cursor){
        super(cursor);
    }

    @ChavePrimaria
    private int id_pedido;
    @ColunaTabela
    private Double valor_total;
    @ColunaTabela
    private String endereco;
    @ColunaTabela
    private Long coord_x;
    @ColunaTabela
    private Long coord_y;
    @ColunaTabela
    private int id_forma_pagto;

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
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

    public Integer getId_forma_pagto() {
        return id_forma_pagto;
    }

    public void setId_forma_pagto(Integer id_forma_pagto) {
        this.id_forma_pagto = id_forma_pagto;
    }

}
