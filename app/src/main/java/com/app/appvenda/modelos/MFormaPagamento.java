package com.app.appvenda.modelos;

/**
 * Created by Marina on 18/12/2016.
 */

public class MFormaPagamento {

    private Double valPerc;
    private int idFormaPagto;
    private int codFormaPagto;
    private String descrFormaPagto;

    public Double getValPerc() {
        return valPerc;
    }

    public void setValPerc(Double valPerc) {
        this.valPerc = valPerc;
    }

    public int getIdFormaPagto() {
        return idFormaPagto;
    }

    public void setIdFormaPagto(int idFormaPagto) {
        this.idFormaPagto = idFormaPagto;
    }

    public int getCodFormaPagto() {
        return codFormaPagto;
    }

    public void setCodFormaPagto(int codFormaPagto) {
        this.codFormaPagto = codFormaPagto;
    }

    public String getDescrFormaPagto() {
        return descrFormaPagto;
    }

    public void setDescrFormaPagto(String descrFormaPagto) {
        this.descrFormaPagto = descrFormaPagto;
    }

}
