package com.app.bdframework.conversor;

import android.animation.TypeEvaluator;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ConversorHelper {

    private static List<Conversor> listaConversor = new ArrayList<>();

    public static <TDe, TPara> void registrarConversor(Conversor<TDe, TPara> conversor) {
        listaConversor.add(conversor);
    }

    public static <TDe, TPara> TPara converter(TDe de) {
        for (Conversor<TDe, TPara> conversor : listaConversor)
            try {
                return conversor.converterDePara(de);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }

    public static <TDe, TPara> TDe converter(TPara para, boolean cde) {
        for (Conversor<TDe, TPara> conversor : listaConversor) {
            try {
                return conversor.converterParaDe(para);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
