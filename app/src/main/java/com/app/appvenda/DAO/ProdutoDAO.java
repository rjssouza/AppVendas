package com.app.appvenda.DAO;

import android.content.Context;

import com.app.appvenda.entidade.Produto;
import com.app.appvenda.modelos.MProduto;
import com.app.appvenda.repositorio.RPEstoque;
import com.app.appvenda.repositorio.RPProduto;
import com.app.bdframework.baseEntidade.Repositorio;

/**
 * Created by Robson on 20/12/2016.
 */

public class ProdutoDAO extends BaseDAO<MProduto, Produto> {

    private RPEstoque rpEstoque;

    public ProdutoDAO(Context context) {
        super(context, Produto.class);
        this.rpEstoque = new RPEstoque(context);
    }

    @Override
    protected void posSalvar(Produto produto, String[] regrasIgnorar) {
        this.rpEstoque.salvar(produto.getEstoque(), regrasIgnorar);
    }

    @Override
    protected void preDeletar(Produto produto, String[] regrasIgnorar) {
        this.rpEstoque.deletar(produto.getEstoque(), regrasIgnorar);
    }

    @Override
    protected Repositorio<Produto> obterRepositorio(Context context) {
        return new RPProduto(context);
    }


}
