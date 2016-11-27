package com.app.appvenda.enums;

/**
 * Created by Robson on 24/11/2016.
 */

public enum EnumStatusVenda  {

    PAGO(1), NAO_PAGO(2);

    private int numVal;

    EnumStatusVenda(int numVal) {
        this.numVal = numVal;
    }

    public Integer getNumVal() {
        return this.numVal;
    }

}
