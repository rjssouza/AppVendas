package com.app.bdframework.conversor;

import com.app.bdframework.excecoes.RegraNegocioException;

import java.util.ArrayList;
import java.util.List;

public class ConversorHelper {

    private static final List<Conversor> listaConversor = new ArrayList<>();

    public static <TDe, TPara> void registrarConversor(Conversor<TDe, TPara> conversor) {
        listaConversor.add(conversor);
    }

    public static <TDe, TPara> TPara converterParaDe(TDe de) {
        for (@SuppressWarnings("unchecked") Conversor<TDe, TPara> conversor : listaConversor)
            try {
                if (de != null)
                    return conversor.converterDePara(de);
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        return null;
    }

    public static <TDe, TPara> TDe converterParaDe(TPara para, boolean cde) {
        for (@SuppressWarnings("unchecked") Conversor<TDe, TPara> conversor : listaConversor) {
            try {
                if (para != null)
                    return conversor.converterParaDe(para);
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
