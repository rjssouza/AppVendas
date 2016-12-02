package com.app.appvenda;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.app.appvenda.fragment.BaseFragment;
import com.app.appvenda.interfaces.IBaseViews;
import com.app.bdframework.enums.EnumTipoMensagem;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.RegraNegocioMensagem;
import com.app.bdframework.excecoes.TratamentoExcecao;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe base para activities
 */
@EActivity
public abstract class BaseActivity extends AppCompatActivity implements IBaseViews {

    @AfterViews
    public void init() {
        TratamentoExcecao.registrarEvento(this);
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
