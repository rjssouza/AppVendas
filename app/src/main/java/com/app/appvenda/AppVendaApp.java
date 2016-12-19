package com.app.appvenda;

import android.app.Application;
import android.support.v4.util.LogWriter;
import android.util.Log;

import com.app.appvenda.conversores.ConversorCliente;
import com.app.appvenda.conversores.ConversorConfiguracao;
import com.app.appvenda.entidade.Configuracao;
import com.app.appvenda.eventosExcecao.TratamentoEventoGeral;
import com.app.appvenda.repositorio.RPConfiguracao;
import com.app.bdframework.BDHelper;
import com.app.bdframework.conversor.ConversorHelper;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.app.bdframework.utils.TradutorMensagemException;


public class AppVendaApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TratamentoExcecao.registrarEvento(new TratamentoEventoGeral(this.getApplicationContext()));
        ConversorHelper.registrarConversor(new ConversorConfiguracao());
        ConversorHelper.registrarConversor(new ConversorCliente());
        BDHelper<Configuracao> configuracaoBDHelper = new RPConfiguracao(this.getApplicationContext());
        configuracaoBDHelper.salvarBDLocal();
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
