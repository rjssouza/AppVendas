package com.app.appvenda.enums;

/**
 * Created by Robson on 30/11/2016.
 */

public enum  EnumTipoConfiguracao {

    DROPBOX(1), SERVICO(2);

    private int numVal;

    EnumTipoConfiguracao(int numVal) {
        this.numVal = numVal;
    }

    public Integer getNumVal() {
        return this.numVal;
    }

}
