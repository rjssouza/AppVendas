package com.app.appvenda.fragment;

import com.app.appvenda.R;
import com.app.bdframework.excecoes.RegraNegocioMensagem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Robson on 30/11/2016.
 */
@EFragment(R.layout.fragment_venda)
public class FragmentVendas extends BaseFragment {

    @AfterViews
    void Init(){

    }

    @Override
    protected void executarAlerta(RegraNegocioMensagem item) {

    }

    @Override
    protected void executarPergunta(RegraNegocioMensagem item) {

    }

    @Override
    protected void executarErro(RegraNegocioMensagem item) {

    }
}
