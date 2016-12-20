package com.app.appvenda.conversores;

import com.app.appvenda.entidade.Estoque;
import com.app.appvenda.modelos.MEstoque;
import com.app.bdframework.conversor.Conversor;

/**
 * Created by Robson on 20/12/2016.
 */

public class ConversorEstoque extends Conversor<MEstoque,Estoque> {

    @Override
    public Estoque converterDePara(MEstoque mEstoque) {
        return null;
    }

    @Override
    public MEstoque converterParaDe(Estoque estoque) {
        return null;
    }

}
