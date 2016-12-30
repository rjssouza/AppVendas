package com.app.appvenda.conversores;

import com.app.appvenda.entidade.Estoque;
import com.app.appvenda.modelos.MEstoque;
import com.app.bdframework.conversor.Conversor;

/**
 * Created by Robson on 20/12/2016.
 */

public class ConversorEstoque extends Conversor<MEstoque, Estoque> {

    @Override
    public Estoque converterDePara(MEstoque mEstoque) {
        Estoque estoque = new Estoque(null);
        estoque.setValor_final(mEstoque.getValorFinal());
        estoque.setId_produto(mEstoque.getIdProduto());
        estoque.setAlerta_falta(mEstoque.getAlertaFalta());
        estoque.setId_estoque(mEstoque.getIdEstoque());
        estoque.setQuantidade(mEstoque.getQuantidade());
        estoque.setValor_produto(mEstoque.getValorProduto());
        return estoque;
    }

    @Override
    public MEstoque converterParaDe(Estoque estoque) {
        MEstoque mEstoque = new MEstoque();
        mEstoque.setValorFinal(estoque.getValor_final());
        mEstoque.setIdProduto(estoque.getId_produto());
        mEstoque.setAlertaFalta(estoque.getAlerta_falta());
        mEstoque.setIdEstoque(estoque.getId_estoque());
        mEstoque.setQuantidade(estoque.getQuantidade());
        mEstoque.setValorProduto(estoque.getValor_produto());
        return mEstoque;
    }

}
