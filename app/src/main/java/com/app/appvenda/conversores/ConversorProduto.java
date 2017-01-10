package com.app.appvenda.conversores;

import com.app.appvenda.entidade.Estoque;
import com.app.appvenda.entidade.Produto;
import com.app.appvenda.modelos.MEstoque;
import com.app.appvenda.modelos.MProduto;
import com.app.bdframework.conversor.Conversor;
import com.app.bdframework.conversor.ConversorHelper;

/**
 * Created by Robson on 20/12/2016.
 */

public class ConversorProduto extends Conversor<MProduto, Produto> {

    @Override
    public Produto converterDePara(MProduto mProduto) {
        Produto produto = new Produto(null);
        produto.setNome(mProduto.getNome());
        produto.setFoto(mProduto.getFoto());
        produto.setAtivo(mProduto.getAtivo());
        produto.setCod_produto(mProduto.getCodProduto());
        Estoque estoque = ConversorHelper.converterDePara(mProduto.getmEstoque(), Estoque.class);
        produto.setEstoque(estoque);
        produto.setId_produto(mProduto.getIdProduto());
        produto.setQtd_limite(mProduto.getQtdLimite());
        return produto;
    }

    @Override
    public MProduto converterParaDe(Produto produto) {
        MProduto mProduto = new MProduto();
        mProduto.setNome(produto.getNome());
        mProduto.setFoto(produto.getFoto());
        mProduto.setAtivo(produto.getAtivo());
        mProduto.setCodProduto(produto.getCod_produto());
        MEstoque mEstoque = ConversorHelper.converterDePara(produto.getEstoque(), MEstoque.class);
        mProduto.setmEstoque(mEstoque);
        mProduto.setIdProduto(produto.getId_produto());
        mProduto.setQtdLimite(produto.getQtd_limite());
        return mProduto;
    }

}
