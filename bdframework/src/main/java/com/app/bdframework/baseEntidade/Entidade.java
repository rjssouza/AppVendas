package com.app.bdframework.baseEntidade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe base para todas as entidades, esta possui metodos necessarios a operações CRUD do objeto
 */
public abstract class Entidade<TChavePrimaria> {

    public static String[] getTodasColunas(Class tipo) {
        List<String> todasColunas = new ArrayList<>();
        for (Field field : tipo.getDeclaredFields()) {
            if (field.isAnnotationPresent(ColunaTabela.class) || field.isAnnotationPresent(ChavePrimaria.class)) {
                todasColunas.add(field.getName());
            }
        }
        String[] todasColunas_ = todasColunas.toArray(new String[todasColunas.size()]);
        return todasColunas_;
    }

    protected Entidade(Cursor cursor) {
        if (cursor != null) {
            for (Field field : this.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(ColunaTabela.class) || field.isAnnotationPresent(ChavePrimaria.class)) {
                    field.setAccessible(true);
                    try {
                        if (cursor.getColumnIndex(obtenerNomeColuna(field)) > -1) {
                            switch (field.getType().getSimpleName().toUpperCase()) {
                                case "STRING":
                                    field.set(this, cursor.getString(cursor.getColumnIndex(obtenerNomeColuna(field))));
                                    break;
                                case "BOOLEAN":
                                    field.set(this, (cursor.getShort(cursor.getColumnIndex(obtenerNomeColuna(field))) > 0));
                                    break;
                                case "INT":
                                case "INTEGER":
                                    field.set(this, cursor.getInt(cursor.getColumnIndex(obtenerNomeColuna(field))));
                                    break;
                                case "LONG":
                                    field.set(this, cursor.getLong(cursor.getColumnIndex(obtenerNomeColuna(field))));
                                    break;
                                case "SHORT":
                                    field.set(this, cursor.getShort(cursor.getColumnIndex(obtenerNomeColuna(field))));
                                    break;
                                case "DOUBLE":
                                    field.set(this, cursor.getDouble(cursor.getColumnIndex(obtenerNomeColuna(field))));
                                    break;
                                default:
                                    break;
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static ParCampoValor getChavePrimaria(Class clsEntidade) {
        for (Field field : clsEntidade.getDeclaredFields()) {
            if (field.isAnnotationPresent(ChavePrimaria.class)) {
                field.setAccessible(true);
                ChavePrimaria chavePrimaria = field.getAnnotation(ChavePrimaria.class);

                if (chavePrimaria != null) {
                    return new ParCampoValor<>(null, field.getName());
                }
            }
        }
        return null;
    }

    public ParCampoValor<TChavePrimaria> getChavePrimaria() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ChavePrimaria.class)) {
                field.setAccessible(true);
                ChavePrimaria chavePrimaria = field.getAnnotation(ChavePrimaria.class);
                ColunaTabela colunaTabela = field.getAnnotation(ColunaTabela.class);

                if (chavePrimaria != null) {
                    try {
                        return new ParCampoValor<>((TChavePrimaria) field.get(this), field.getName());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    public ContentValues getContentValue() {
        ContentValues contentValues = new ContentValues();
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ColunaTabela.class) || field.isAnnotationPresent(ChavePrimaria.class)) {
                ColunaTabela colunaTabela = field.getAnnotation(ColunaTabela.class);
                field.setAccessible(true);
                try {
                    if (field.get(this) != null) {
                        switch (field.getType().getSimpleName().toUpperCase()) {
                            case "STRING":
                                contentValues.put(obtenerNomeColuna(field), field.get(this).toString());
                                break;
                            case "BOOLEAN":
                                contentValues.put(obtenerNomeColuna(field), (Boolean) field.get(this));
                                break;
                            case "INT":
                                contentValues.put(obtenerNomeColuna(field), field.getInt(this));
                                break;
                            case "INTEGER":
                                contentValues.put(obtenerNomeColuna(field), (Integer) field.get(this));
                                break;
                            case "LONG":
                                contentValues.put(obtenerNomeColuna(field), (Long) field.get(this));
                                break;
                            case "SHORT":
                                contentValues.put(obtenerNomeColuna(field), (Short) field.get(this));
                                break;
                            case "DOUBLE":
                                contentValues.put(obtenerNomeColuna(field), (Double) field.get(this));
                                break;
                            default:
                                break;
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return contentValues;
    }

    public abstract void complementarEntidade(Context context);

    public void setChavePrimaria(TChavePrimaria tChavePrimaria) {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ChavePrimaria.class)) {
                field.setAccessible(true);
                ChavePrimaria chavePrimaria = field.getAnnotation(ChavePrimaria.class);
                ColunaTabela colunaTabela = field.getAnnotation(ColunaTabela.class);

                if (chavePrimaria != null) {
                    try {
                        field.set(this, tChavePrimaria);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String obtenerNomeColuna(Field field) {
        ColunaTabela colunaTabela = field.getAnnotation(ColunaTabela.class);
        ChavePrimaria chavePrimaria = field.getAnnotation(ChavePrimaria.class);

        String strColunaTabela = "";

        if (colunaTabela != null) {
            strColunaTabela = colunaTabela.nomeColuna() != "" ? colunaTabela.nomeColuna() : field.getName();
            return strColunaTabela;
        }

        if (chavePrimaria != null) {
            strColunaTabela = chavePrimaria.nomeColuna() != "" ? chavePrimaria.nomeColuna() : field.getName();
            return strColunaTabela;
        }
        return "";
    }

}
