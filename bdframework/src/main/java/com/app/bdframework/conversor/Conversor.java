package com.app.bdframework.conversor;

/**
 * Created by Robson on 12/11/2016.
 */

public abstract class Conversor<TDe, TPara> {

    public abstract TPara converterDePara(TDe de);

    public abstract TDe converterParaDe(TPara para);

}
