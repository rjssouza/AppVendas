package com.app.appvenda.enums;

/**
 * Created by Robson on 02/02/2017.
 */

public enum EnumTipoPedido {

    VENDA(1, "VENDA"), TROCA(2, "TROCA"), DEVOLUCAO(3, "DEVOLUCAO");

    private int numVal;
    private String descr;

    EnumTipoPedido(int numVal, String strEnum) {
        this.numVal = numVal;
        this.descr = strEnum;
    }

    public Integer getNumVal() {
        return this.numVal;
    }

    @Override
    public String toString() {
        return this.descr;
    }
}
