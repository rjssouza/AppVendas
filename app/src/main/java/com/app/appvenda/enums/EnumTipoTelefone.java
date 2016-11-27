package com.app.appvenda.enums;

public enum EnumTipoTelefone {

    FIXO(1), CELULAR(2);

    private int numVal;

    EnumTipoTelefone(int numVal) {
        this.numVal = numVal;
    }

    public Integer getNumVal() {
        return this.numVal;
    }

}
