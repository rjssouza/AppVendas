package com.app.appvenda.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.app.appvenda.ActivityConfigurar_;
import com.app.appvenda.R;
import com.app.appvenda.interfaces.IBaseViews;
import com.app.bdframework.enums.EnumTipoMensagem;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.eventos.EventosCaixaDialogo;
import com.app.bdframework.excecoes.IRegraNegocio;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.RegraNegocioMensagem;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.app.bdframework.utils.AppLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe base para activities
 */
@EActivity
public abstract class BaseActivity extends AppCompatActivity implements IBaseViews, IRegraNegocio {

    ProgressDialog progressDialog;
    boolean activityActiva;
    private List<Fragment> fragmentList;

    public BaseActivity() {
        this.fragmentList = new ArrayList<>();
        TratamentoExcecao.registrarEventoRegraNegocio(this);
    }

    @AfterViews
    public void init() {
        afterViews();
    }

    protected abstract void afterViews();

    @Override
    @UiThread
    public void exibirMensagemToast(int stringId) {
        this.exibirMensagemToast(getResources().getString(stringId));
    }

    @Override
    @UiThread
    public void exibirMensagemToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
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

    @Override
    @UiThread
    public void esconderProgress() {
        synchronized (this) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    @Override
    @UiThread
    public void actualizarTexto(EditText edtText, String texto) {
        edtText.setText(texto);
    }

    @Override
    @UiThread
    public void exibirDialogoAlerta(int resIdTitulo, int resIdMesaje, final EventosCaixaDialogo eventos) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(this.getResources().getString(resIdTitulo))
                .setMessage(getResources().getString(resIdMesaje))
                .setPositiveButton(getResources().getString(R.string.sim), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eventos.onClickPositivo();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.nao), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eventos.onClickNegativo();
                    }
                })
                .show();
    }

    @Override
    @UiThread
    public void exibirDialogoConfirmacion(int resIdTitulo, int resIdMesaje, final EventosCaixaDialogo eventos) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(this.getResources().getString(resIdTitulo))
                .setMessage(getResources().getString(resIdMesaje))
                .setPositiveButton(getResources().getString(R.string.aceitar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eventos.onClickPositivo();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eventos.onClickNegativo();
                    }
                })
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

    @Override
    @org.androidannotations.annotations.UiThread
    public void executarEvento(RegraNegocioMensagem item) {
        esconderProgress();
        RegraNegocioException exception = item.getRegraNegocioException();
        if (exception.getTipoMensagem().equals(EnumTipoMensagem.PERGUNTA)) {
            this.executarPergunta(item);
        } else if (exception.getTipoMensagem().equals(EnumTipoMensagem.ALERTA)) {
            this.executarAlerta(item);
        } else {
            AppLog.criarLog(item);
            exibirMensagemToast(item.getMensagem());
        }
    }

    protected abstract void executarAlerta(RegraNegocioMensagem item);

    protected abstract void executarPergunta(RegraNegocioMensagem item);

    protected <T extends Fragment> boolean chamarFragment(Class fragmentClass, @IdRes int containerViewId, EventoVoid<T> sucessoChamadaFragment) {
        try {
            T fragment = obterFragment(fragmentClass);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flConteudo, fragment).commit();
            if (sucessoChamadaFragment != null)
                sucessoChamadaFragment.executarEvento(fragment);
            return true;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void onPrimeiroAcesso() {
        Intent intent = new Intent(this, ActivityConfigurar_.class);
        startActivity(intent);
    }

    private <T extends Fragment> T obterFragment(Class fragmentClass) throws IllegalAccessException, InstantiationException {
        for (Fragment fragment : fragmentList) {
            if (fragment.getClass().getSimpleName().toUpperCase().toUpperCase().trim().equals(fragmentClass.getSimpleName().toUpperCase().trim())) {
                return (T) fragment;
            }
        }
        return (T) fragmentClass.newInstance();
    }

}
