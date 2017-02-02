package com.app.appvenda.modelos;

/**
 * Created by Robson on 29/01/2017.
 */

public class MEndereco {

    private String endereco;
    private Long coordY;
    private Long coordX;

    public Long getCoordY() {
        if (coordY != null)
            calcularCoord();
        return coordY;
    }

    public Long getCoordX() {
        if (coordX != null)
            calcularCoord();
        return coordX;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    private void calcularCoord() {
        if (endereco != null) {

        }
    }

}
