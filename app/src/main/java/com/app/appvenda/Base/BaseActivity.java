package com.app.appvenda.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.app.appvenda.R;
import com.app.appvenda.interfaces.IBaseViews;
import com.app.bdframework.eventos.EventosCaixaDialogo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Classe base para activities
 */
@EActivity
public abstract class BaseActivity extends AppCompatActivity implements IBaseViews {

    ProgressDialog progressDialog;
    boolean activityActiva;

    @AfterViews
    public void init() {
    }

    @UiThread
    public void exibirMensagemToast(int stringId) {
        this.exibirMensagemToast(getResources().getString(stringId));
    }

    @UiThread
    public void exibirMensagemToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @UiThread
    public void exibirProgress(int messageId, boolean cancelavel) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage(getResources().getString(messageId));
                progressDialog.setCancelable(cancelavel);
                progressDialog.show();
            }
            if (!progressDialog.isShowing())
                progressDialog.show();

    }

    @UiThread
    public void esconderProgress() {
        synchronized (this) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    @UiThread
    public void actualizarTexto(EditText edtText, String texto) {
        edtText.setText(texto);
    }

    public void exibirDialogoAlerta(int resIdTitulo, int resIdMesaje, EventosCaixaDialogo eventos) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(this.getResources().getString(resIdTitulo))
                .setMessage(getResources().getString(resIdMesaje))
                .setPositiveButton(getResources().getString(R.string.sim), eventos.onClickPositivo())
                .setNegativeButton(getResources().getString(R.string.nao), eventos.onClickNegativo())
                .show();
    }

    public void exibirDialogoConfirmacion(int resIdTitulo, int resIdMesaje, EventosCaixaDialogo eventos) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(this.getResources().getString(resIdTitulo))
                .setMessage(getResources().getString(resIdMesaje))
                .setPositiveButton(getResources().getString(R.string.aceitar), eventos.onClickPositivo())
                .setNegativeButton(getResources().getString(R.string.cancelar), eventos.onClickNegativo())
                .show();
    }

    @Override
    public void onStart() {
        super.onStart();
        activityActiva = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityActiva = false;
    }

}
