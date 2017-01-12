package com.app.appvenda.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Robson on 29/12/2016.
 */

public class ConversorUtils {
    public static Double stringDouble(String valor) {
        if (valor != null) {
            Pattern defimalPatt = Pattern.compile("(\\+|-)?([0-9]+(\\,[0-9]+))");
            Matcher matcher = defimalPatt.matcher(valor);
            if (matcher.find()) {
                String teste = matcher.group(0);
                return Double.parseDouble(teste.replace(",", "."));
            }
        }
        return null;
    }

}
