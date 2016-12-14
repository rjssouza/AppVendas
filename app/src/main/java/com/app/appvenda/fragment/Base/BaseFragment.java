package com.app.appvenda.fragment.base;

import android.support.v4.app.Fragment;
import android.widget.EditText;

import com.app.appvenda.base.BaseActivity;
import com.app.appvenda.interfaces.IBaseViews;
import com.app.bdframework.eventos.EventosCaixaDialogo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

/**
 * Created by Robson on 29/11/2016.
 */
@EFragment
public class BaseFragment extends Fragment implements IBaseViews {

    private BaseActivity baseActivity;

    @AfterViews
    public void init() {
        this.baseActivity = (BaseActivity) this.getActivity();
    }

    @Override
    public void exibirMensagemToast(int stringId) {
        this.baseActivity.exibirMensagemToast(stringId);
    }

    @Override
    public void exibirMensagemToast(String msg) {
        this.baseActivity.exibirMensagemToast(msg);
    }

    @Override
    @UiThread
    public void exibirProgress(int messageId, boolean cancelavel) {
        this.baseActivity.exibirProgress(messageId, cancelavel);
    }

    @Override
    @UiThread
    public void esconderProgress() {
        this.baseActivity.esconderProgress();
    }

    @Override
    public void actualizarTexto(EditText edtText, String texto) {
        this.baseActivity.actualizarTexto(edtText, texto);
    }

    @Override
    public void exibirDialogoAlerta(int resIdTitulo, int resIdMesaje, EventosCaixaDialogo eventos) {
        this.baseActivity.exibirDialogoAlerta(resIdTitulo, resIdMesaje, eventos);
    }

    @Override
    public void exibirDialogoConfirmacion(int resIdTitulo, int resIdMesaje, EventosCaixaDialogo eventos) {
        this.baseActivity.exibirDialogoConfirmacion(resIdTitulo, resIdMesaje, eventos);
    }

}
