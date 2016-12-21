package com.app.appvenda.eventosExcecao;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.app.appvenda.AppVendaApp;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioMensagem;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.app.bdframework.utils.AppLog;
import com.app.bdframework.utils.GeradorArquivo;


public class TratamentoEventoGeral implements EventoVoid<RegraNegocioMensagem> {

    private Context context;

    public TratamentoEventoGeral(Context context) {
        this.context = context;
    }

    @Override
    public void executarEvento(final RegraNegocioMensagem item) {
        Toast.makeText(context, item.getMensagem(), Toast.LENGTH_SHORT).show();
        AsyncTask task = new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] params) {
                AppLog.CriarLog(item);
                return null;
            }
        };
        task.execute();
    }

}
