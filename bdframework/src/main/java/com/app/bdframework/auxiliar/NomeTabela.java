package com.app.bdframework.auxiliar;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Robson on 03/01/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface NomeTabela {
    public String nomeTabela();
}
