package com.app.appvenda.interfaces;

import android.widget.EditText;

import com.app.bdframework.eventos.EventosCaixaDialogo;
import com.app.bdframework.excecoes.IRegraNegocio;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Interface para operações basicas entre active view e fragment view
 */
public interface IBaseViews {

    void init();

    void exibirMensagemToast(int stringId);
    void exibirMensagemToast(String msg);
    void exibirProgress(int messageId, boolean cancelavel);
    void esconderProgress();
    void actualizarTexto(EditText edtText, String texto);
    void exibirDialogoAlerta(int resIdTitulo, int resIdMesaje, EventosCaixaDialogo eventos);
    void exibirDialogoConfirmacion(int resIdTitulo, int resIdMesaje, EventosCaixaDialogo eventos);

}
