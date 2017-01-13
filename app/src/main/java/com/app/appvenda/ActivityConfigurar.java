package com.app.appvenda;

import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Button;

import com.app.appvenda.base.BaseActivity;
import com.app.appvenda.exportadorVenda.ExportadorVendas;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.eventos.EventosCaixaDialogo;
import com.app.bdframework.excecoes.RegraNegocioMensagem;
import com.app.bdframework.utils.ArquivosUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Robson on 12/01/2017.
 */
@EActivity(R.layout.activity_configurar_usuario)
public class ActivityConfigurar extends BaseActivity {

    ExportadorVendas exportadorVendas;

    @ViewById
    Button btnConfirma;

    @Override
    protected void afterViews() {
        // exibirProgress(R.string.aguarde, false);
        this.exportadorVendas = new ExportadorVendas(this);
        this.exportadorVendas.setEventoPosProcessamento(new EventoVoid<Boolean>() {
            @Override
            public void executarEvento(Boolean item) throws Exception {
                if (item) {
                    esconderProgress();
                }
            }
        });
        // this.exportadorVendas.evetuarSincronizacao();
    }

    @Override
    protected void executarAlerta(RegraNegocioMensagem item) {

    }

    @Override
    protected void executarPergunta(RegraNegocioMensagem item) {

    }

    @Override
    public void executarEvento(RegraNegocioMensagem item) {
        exibirMensagemToast(item.getMensagem());
        ArquivosUtils.deleteCache(this);
    }

    @Override
    public void onBackPressed() {
        exibirDialogoAlerta(R.string.confirma_acao, R.string.msg_sair, new EventosCaixaDialogo() {
            @Override
            public void onClickPositivo() {
                ArquivosUtils.deleteCache(getApplicationContext());
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }

            @Override
            public void onClickNegativo() {

            }
        });
    }

    @Click(R.id.btnConfirma)
    void confirmaDados() {
        Intent intent = new Intent(this, MainActivity_.class);
        startActivity(intent);
    }

}
