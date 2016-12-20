package com.app.appvenda.conversores;

import com.app.appvenda.entidade.Produto;
import com.app.appvenda.modelos.MProduto;
import com.app.bdframework.conversor.Conversor;

/**
 * Created by Robson on 20/12/2016.
 */

public class ConversorProduto extends Conversor<MProduto, Produto> {

    @Override
    public Produto converterDePara(MProduto mProduto) {
        Produto produto = new Produto(null);

        return null;
    }

    @Override
    public MProduto converterParaDe(Produto produto) {
        return null;
    }

}
