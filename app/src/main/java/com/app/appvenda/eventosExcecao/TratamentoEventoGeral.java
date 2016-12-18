package com.app.appvenda.eventosExcecao;

import android.content.Context;
import android.widget.Toast;

import com.app.appvenda.AppVendaApp;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioMensagem;
import com.app.bdframework.excecoes.TratamentoExcecao;


public class TratamentoEventoGeral implements EventoVoid<RegraNegocioMensagem> {

    private Context context;

    public TratamentoEventoGeral(Context context) {
        this.context = context;
    }

    @Override
    public void executarEvento(RegraNegocioMensagem item) {
        item.getException().printStackTrace();
        Toast.makeText(context, item.getMensagem(), Toast.LENGTH_SHORT).show();
    }

}
