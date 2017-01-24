package com.app.appvenda.conversores;

import com.app.appvenda.entidade.IDescricaoEntidade;
import com.app.appvenda.modelos.MItemSeletor;
import com.app.bdframework.conversor.Conversor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robson on 23/01/2017.
 */

public class ConversorItemSeletor extends Conversor<List<IDescricaoEntidade>, List<MItemSeletor>> {

    @Override
    public List<MItemSeletor> converterDePara(List<IDescricaoEntidade> iDescricaoEntidades) {
        List<MItemSeletor> mItemSeletors = new ArrayList<>();

        for (IDescricaoEntidade iDescricaoEntidade : iDescricaoEntidades) {
            MItemSeletor mItemSeletor = new MItemSeletor();
            mItemSeletor.setCodigo(iDescricaoEntidade.getCodigo());
            mItemSeletor.setDescricao(iDescricaoEntidade.getDescricao());
            mItemSeletor.setId(iDescricaoEntidade.getIdentificador());
            mItemSeletors.add(mItemSeletor);
        }

        return mItemSeletors;
    }

    @Override
    public List<IDescricaoEntidade> converterParaDe(List<MItemSeletor> mItemSeletor) {
        return null;
    }

}
