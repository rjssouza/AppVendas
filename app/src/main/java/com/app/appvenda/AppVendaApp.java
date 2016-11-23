package com.app.appvenda;

import android.app.Application;
import android.util.Log;

import com.app.appvenda.entidade.Cliente;
import com.app.appvenda.entidade.TipoPedido;
import com.app.appvenda.eventosExcecao.EventoGeral;
import com.app.appvenda.eventosExcecao.EventoRegraNegocio;
import com.app.appvenda.repositorio.RPCliente;
import com.app.appvenda.repositorio.RPTipoPedido;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.app.bdframework.utils.Constantes;
import com.app.bdframework.utils.TradutorMensagemException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class AppVendaApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TratamentoExcecao.registrarEvento(new EventoGeral());
        TratamentoExcecao.registrarEvento(new EventoRegraNegocio());
        RPTipoPedido rpTipoPedido = new RPTipoPedido(this.getApplicationContext());

        List<TipoPedido> tipoPedidoList = rpTipoPedido.executarQuery(new String[]{"cod_tipo_pedido", "descr_tipo_pedido"},
                null, null);
    }

    private void addUnhandledEventGlobal(){
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
