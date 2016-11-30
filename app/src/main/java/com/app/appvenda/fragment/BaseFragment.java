package com.app.appvenda.fragment;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.app.appvenda.MainActivity;
import com.app.appvenda.interfaces.IBaseViews;
import com.app.appvenda.interfaces.IFragment;
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
public abstract class BaseFragment extends Fragment implements IBaseViews, IFragment, EventoVoid<RegraNegocioMensagem> {

    private IFragment fragmentPai;
    EventoVoid<IFragment> eventoVoid;

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

    @Override
    public void setFragmentPai(IFragment fragmentPai) {
        this.fragmentPai = fragmentPai;
    }

    @Override
    public String getFragmentID() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void registrarEventoVoltar(EventoVoid<IFragment> eventoVoid) {
        this.eventoVoid = eventoVoid;
    }

    @Override
    public void voltar() {
        eventoVoid.executarEvento(fragmentPai);
    }

}
