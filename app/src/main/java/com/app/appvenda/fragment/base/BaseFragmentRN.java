package com.app.appvenda.fragment.base;

import com.app.appvenda.base.BaseActivityRN;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioMensagem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

/**
 * Created by Robson on 03/12/2016.
 */
@EFragment
public abstract class BaseFragmentRN extends BaseFragment implements EventoVoid<RegraNegocioMensagem> {

    private BaseActivityRN baseActivityRN;

    @AfterViews
    public void init() {
        this.baseActivityRN = (BaseActivityRN) this.getActivity();
    }

    @Override
    @UiThread
    public void executarEvento(RegraNegocioMensagem item) {
       this.baseActivityRN.executarEvento(item);
    }

}
