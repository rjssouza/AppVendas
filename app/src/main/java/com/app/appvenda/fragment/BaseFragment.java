package com.app.appvenda.fragment;

import android.support.v4.app.Fragment;

import com.app.appvenda.interfaces.IBaseViews;
import com.app.bdframework.enums.EnumTipoMensagem;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.RegraNegocioMensagem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Robson on 29/11/2016.
 */
@EFragment
public abstract class BaseFragment extends Fragment implements IBaseViews, EventoVoid<RegraNegocioMensagem> {

    private BaseFragment fragmentPai;
    EventoVoid<BaseFragment> eventoVoid;

    @AfterViews
    public void init() {
        fragmentPai = null;
    }

    @Override
    public void executarEvento(RegraNegocioMensagem item) {
        RegraNegocioException exception = item.getRegraNegocioException();
        if (exception.getTipoMensagem().equals(EnumTipoMensagem.PERGUNTA)) {
            this.executarPergunta(item);
        } else if (exception.getTipoMensagem().equals(EnumTipoMensagem.ALERTA)) {
            this.executarAlerta(item);
        } else {
            this.executarErro(item);
        }
    }

    protected abstract void executarAlerta(RegraNegocioMensagem item);

    protected abstract void executarPergunta(RegraNegocioMensagem item);

    protected abstract void executarErro(RegraNegocioMensagem item);

    public void setFragmentPai(BaseFragment fragmentPai) {
        this.fragmentPai = fragmentPai;
    }

    public String getFragmentID() {
        return this.getClass().getSimpleName();
    }

    public void registrarEventoVoltar(EventoVoid<BaseFragment> eventoVoid) {
        this.eventoVoid = eventoVoid;
    }

    public void voltar() {
        eventoVoid.executarEvento(fragmentPai);
    }

}
