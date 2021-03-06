package com.app.appvenda.enums;

/**
 * Created by Robson on 24/11/2016.
 */

public enum EnumStatusVenda {

    PAGO(1, "PAGO"), NAO_PAGO(2, "NAO_PAGO");

    private int numVal;
    private String descr;

    EnumStatusVenda(int numVal, String desr) {
        this.numVal = numVal;
        this.descr = desr;
    }

    public Integer getNumVal() {
        return this.numVal;
    }

    @Override
    public String toString() {
        return this.descr;
    }
}
