package com.app.appvenda.fragment.base;

import com.app.appvenda.base._BaseActivityRN;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioMensagem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

/**
 * Created by Robson on 03/12/2016.
 */
@EFragment
public abstract class _BaseFragmentRN extends BaseFragment implements EventoVoid<RegraNegocioMensagem> {

    private _BaseActivityRN baseActivityRN;

    @AfterViews
    public void init() {
        this.baseActivityRN = (_BaseActivityRN) this.getActivity();
    }

    @Override
    @UiThread
    public void executarEvento(RegraNegocioMensagem item) {
       this.baseActivityRN.executarEvento(item);
    }

}
