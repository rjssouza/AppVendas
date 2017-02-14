package com.app.appvenda;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.app.appvenda.conversores.ConversorCliente;
import com.app.appvenda.conversores.ConversorConfiguracao;
import com.app.appvenda.conversores.ConversorEstoque;
import com.app.appvenda.conversores.ConversorFormaPagto;
import com.app.appvenda.conversores.ConversorItemSeletor;
import com.app.appvenda.conversores.ConversorPedido;
import com.app.appvenda.conversores.ConversorPedidoProduto;
import com.app.appvenda.conversores.ConversorProduto;
import com.app.appvenda.conversores.ConversorStatusVenda;
import com.app.appvenda.conversores.ConversorTipoPedido;
import com.app.appvenda.conversores.ConversorVenda;
import com.app.appvenda.conversores.ConversorVendedor;
import com.app.appvenda.utils.InformacoesVendedor;
import com.app.bdframework.conversor.ConversorHelper;
import com.app.bdframework.excecoes.IRegraNegocio;
import com.app.bdframework.excecoes.RegraNegocioMensagem;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.app.bdframework.utils.AppLog;


public class AppVendaApp extends Application implements IRegraNegocio {

    InformacoesVendedor informacoesVendedor;

    @Override
    public void onCreate() {
        super.onCreate();

        TratamentoExcecao.registrarEventoRegraNegocio(this);
        registrarConversores();
    }

    private void addUnhandledEventGlobal() {
        final Thread.UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                AppLog.criarLog(paramThrowable);
                handler.uncaughtException(paramThread, paramThrowable);
            }
        });
    }

    private void registrarConversores() {
        ConversorHelper.registrarConversor(new ConversorConfiguracao());
        ConversorHelper.registrarConversor(new ConversorCliente());
        ConversorHelper.registrarConversor(new ConversorEstoque());
        ConversorHelper.registrarConversor(new ConversorProduto());
        ConversorHelper.registrarConversor(new ConversorFormaPagto());
        ConversorHelper.registrarConversor(new ConversorVendedor());
        ConversorHelper.registrarConversor(new ConversorVenda());
        ConversorHelper.registrarConversor(new ConversorItemSeletor());
        ConversorHelper.registrarConversor(new ConversorPedido());
        ConversorHelper.registrarConversor(new ConversorTipoPedido());
        ConversorHelper.registrarConversor(new ConversorStatusVenda());
        ConversorHelper.registrarConversor(new ConversorPedidoProduto());
    }

    @Override
    public void executarEvento(final RegraNegocioMensagem item) throws Exception {
        Toast.makeText(getApplicationContext(), item.getMensagem(), Toast.LENGTH_SHORT).show();
        AsyncTask task = new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] params) {
                AppLog.criarLog(item);
                return null;
            }
        };
        task.execute();
    }

    @Override
    public void onPrimeiroAcesso() {
        Intent intent = new Intent(this, ActivityConfigurar_.class);
        startActivity(intent);
    }
}
