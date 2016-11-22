package com.app.appvenda;

import android.app.Application;

import com.app.appvenda.entidade.Cliente;
import com.app.appvenda.eventosExcecao.EventoGeral;
import com.app.appvenda.eventosExcecao.EventoRegraNegocio;
import com.app.appvenda.repositorio.RPCliente;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.app.bdframework.utils.Constantes;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Robson on 13/11/2016.
 */
public class AppVendaApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TratamentoExcecao.registrarEvento(new EventoGeral());
        TratamentoExcecao.registrarEvento(new EventoRegraNegocio());
        RPCliente rpCliente = new RPCliente(getApplicationContext());

        rpCliente.salvarBDLocal();
    }

}
