package com.app.appvenda;

import android.app.Application;
import android.util.Log;

import com.app.appvenda.entidade.Cliente;
import com.app.appvenda.entidade.Estoque;
import com.app.appvenda.entidade.FormaPagto;
import com.app.appvenda.entidade.Pedido;
import com.app.appvenda.entidade.PedidoProduto;
import com.app.appvenda.entidade.Produto;
import com.app.appvenda.entidade.StatusVenda;
import com.app.appvenda.entidade.Telefone;
import com.app.appvenda.entidade.TipoPedido;
import com.app.appvenda.entidade.TipoTelefone;
import com.app.appvenda.entidade.Venda;
import com.app.appvenda.entidade.Vendedor;
import com.app.appvenda.eventosExcecao.EventoGeral;
import com.app.appvenda.eventosExcecao.EventoRegraNegocio;
import com.app.appvenda.regrasNegocio.DeletarEntidade.RNCliente.ClientePossuiPedidosPendentes;
import com.app.appvenda.regrasNegocio.DeletarEntidade.RNVenda.StatusVendaIncompleto;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.app.bdframework.utils.TradutorMensagemException;

import java.util.List;


public class AppVendaApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TratamentoExcecao.registrarEvento(new EventoGeral());
        TratamentoExcecao.registrarEvento(new EventoRegraNegocio());
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
