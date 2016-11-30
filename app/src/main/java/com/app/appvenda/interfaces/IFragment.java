package com.app.appvenda.interfaces;

import com.app.bdframework.eventos.EventoVoid;

/**
 * Created by Robson on 29/11/2016.
 */

public interface IFragment {

    void setFragmentPai(IFragment fragmentPai);

    String getFragmentID();

    void registrarEventoVoltar(EventoVoid<IFragment> eventoVoid);

    void voltar();

}
