package com.app.bdframework.conversor;

/**
 * Classe abstrata para conversão de objetos
 */
public abstract class Conversor<TDe, TPara> {

    public abstract TPara converterDePara(TDe de);

    public abstract TDe converterParaDe(TPara para);

}
