package com.app.appvenda;

import android.app.Activity;

import com.app.appvenda.interfaces.IBaseViews;
import com.app.bdframework.enums.EnumTipoMensagem;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.RegraNegocioMensagem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Classe base para activities
 */
@EActivity
public abstract class BaseActivity extends Activity implements IBaseViews, EventoVoid<RegraNegocioMensagem> {

    @AfterViews
    public void init() {
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

}
