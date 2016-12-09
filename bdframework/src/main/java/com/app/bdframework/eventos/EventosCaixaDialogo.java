package com.app.bdframework.eventos;

import android.content.DialogInterface;

/**
 * Created by Robson on 08/12/2016.
 */

public interface EventosCaixaDialogo {

    DialogInterface.OnClickListener onClickPositivo();

    DialogInterface.OnClickListener onClickNegativo();

}