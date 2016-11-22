package com.app.bdframework.conversor;

import android.animation.TypeEvaluator;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robson on 12/11/2016.
 */
public class ConversorHelper {

    private static List<Conversor> listaConversor = new ArrayList<>();

    public static <TDe, TPara> void registrarConversor(Conversor<TDe, TPara> conversor) {
        Type[] teste = conversor.getClass().getGenericInterfaces();
        listaConversor.add(conversor);
    }

    public static <TDe, TPara> TPara converter(TDe de) {
        for (Conversor conversor : listaConversor) {
            try {
                Conversor<TDe, TPara> conversor1 = (Conversor<TDe, TPara>) conversor;
                return conversor1.converterDePara(de);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static <TDe, TPara> TDe converter(TPara para, boolean cde) {
        for (Conversor conversor : listaConversor) {
            try {
                Conversor<TDe, TPara> conversor1 = (Conversor<TDe, TPara>) conversor;
                return conversor1.converterParaDe(para);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
