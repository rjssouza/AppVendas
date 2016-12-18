package com.app.appvenda.base;

import com.app.bdframework.enums.EnumTipoMensagem;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.RegraNegocioMensagem;
import com.app.bdframework.excecoes.TratamentoExcecao;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

/**
 * Created by Robson on 03/12/2016.
 */
@EActivity
public abstract class BaseActivityRN extends BaseActivity implements EventoVoid<RegraNegocioMensagem> {

    public BaseActivityRN() {
        TratamentoExcecao.registrarEventoRegraNegocio(this);
    }

    @Override
    @UiThread
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
