package com.app.appvenda.modelos;

/**
 * Created by Robson on 29/01/2017.
 */

public class MEndereco {

    private String endereco;
    private Long coordY;
    private Long coordX;

    public Long getCoordY() {
        return coordY;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getCoordX() {
        return coordX;
    }

    private void calcularCoord(){

    }

}
