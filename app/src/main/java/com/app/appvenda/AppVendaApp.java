package com.app.appvenda;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.app.appvenda.conversores.ConversorCliente;
import com.app.appvenda.conversores.ConversorConfiguracao;
import com.app.appvenda.conversores.ConversorEstoque;
import com.app.appvenda.conversores.ConversorProduto;
import com.app.appvenda.repositorio.RPConfiguracao;
import com.app.bdframework.baseEntidade.Repositorio;
import com.app.bdframework.conversor.ConversorHelper;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioMensagem;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.app.bdframework.utils.AppLog;
import com.app.bdframework.utils.TradutorMensagemException;


public class AppVendaApp extends Application implements EventoVoid<RegraNegocioMensagem> {

    @Override
    public void onCreate() {
        super.onCreate();

        TratamentoExcecao.registrarEvento(this);
        registrarConversores();

        Repositorio repositorio = new RPConfiguracao(getApplicationContext());
        repositorio.salvarBDLocal();
    }

    private void addUnhandledEventGlobal() {
        final Thread.UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                Log.e("Erro", TradutorMensagemException.obterMensagem(paramThrowable, true));
                handler.uncaughtException(paramThread, paramThrowable);
            }
        });
    }

    private void registrarConversores() {
        ConversorHelper.registrarConversor(new ConversorConfiguracao());
        ConversorHelper.registrarConversor(new ConversorCliente());
        ConversorHelper.registrarConversor(new ConversorEstoque());
        ConversorHelper.registrarConversor(new ConversorProduto());
    }

    @Override
    public void executarEvento(final RegraNegocioMensagem item) throws Exception {
        Toast.makeText(getApplicationContext(), item.getMensagem(), Toast.LENGTH_SHORT).show();
        AsyncTask task = new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] params) {
                AppLog.CriarLog(item);
                return null;
            }
        };
        task.execute();
    }
}
