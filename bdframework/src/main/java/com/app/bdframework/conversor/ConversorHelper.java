package com.app.bdframework.conversor;

import java.util.ArrayList;
import java.util.List;

public class ConversorHelper {

    private static final List<Conversor> listaConversor = new ArrayList<>();

    public static <TDe, TPara> void registrarConversor(Conversor<TDe, TPara> conversor) {
        listaConversor.add(conversor);
    }

    public static <TDe, TPara> TPara converterDePara(TDe de, Class<TPara> tParaClass) {
        for (@SuppressWarnings("unchecked") Conversor<TDe, TPara> conversor : listaConversor)
            try {
                if (de != null) {
                    if (tParaClass.isInstance(de.getClass()))
                        return conversor.converterDePara(de);
                }
                return null;
            } catch (ClassCastException e) {
                //e.printStackTrace();
            }
        return null;
    }

    public static <TDe, TPara> TDe converterParaDe(TPara para, Class<TDe> tDeClass) {
        for (@SuppressWarnings("unchecked") Conversor<TDe, TPara> conversor : listaConversor) {
            try {
                if (para != null) {
                    if (tDeClass.isInstance(para.getClass()))
                        return conversor.converterParaDe(para);
                }
                return null;
            } catch (ClassCastException e) {
                //e.printStackTrace();
            }
        }
        return null;
    }

}
