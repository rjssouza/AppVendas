package com.app.appvenda;

import android.app.Application;
import android.util.Log;

import com.app.appvenda.eventosExcecao.TratamentoEventoGeral;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.app.bdframework.utils.TradutorMensagemException;


public class AppVendaApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TratamentoExcecao.registrarEvento(new TratamentoEventoGeral());
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

}
