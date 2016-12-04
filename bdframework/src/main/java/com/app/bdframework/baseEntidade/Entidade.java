package com.app.bdframework.baseEntidade;

import android.content.ContentValues;
import android.database.Cursor;

import com.app.bdframework.auxiliar.ChavePrimaria;
import com.app.bdframework.auxiliar.ColunaTabela;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe base para todas as entidades, esta possui metodos necessarios a operações CRUD do objeto
 */
public abstract class Entidade {

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
                        if (cursor.getColumnIndex(field.getName()) > -1) {
                            switch (field.getType().getSimpleName().toUpperCase()) {
                                case "STRING":
                                    field.set(this, cursor.getString(cursor.getColumnIndex(field.getName())));
                                    break;
                                case "BOOLEAN":
                                    field.set(this, (cursor.getShort(cursor.getColumnIndex(field.getName())) > 0));
                                    break;
                                case "INT":
                                case "INTEGER":
                                    field.set(this, cursor.getInt(cursor.getColumnIndex(field.getName())));
                                    break;
                                case "LONG":
                                    field.set(this, cursor.getLong(cursor.getColumnIndex(field.getName())));
                                    break;
                                case "SHORT":
                                    field.set(this, cursor.getShort(cursor.getColumnIndex(field.getName())));
                                    break;
                                case "DOUBLE":
                                    field.set(this, cursor.getDouble(cursor.getColumnIndex(field.getName())));
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

    ParCampoValor<Integer> getChavePrimaria() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ChavePrimaria.class)) {
                field.setAccessible(true);
                ChavePrimaria chavePrimaria = field.getAnnotation(ChavePrimaria.class);
                if (chavePrimaria != null) {
                    try {
                        return new ParCampoValor<>(field.getInt(this), field.getName());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    ContentValues getContentValue() {
        ContentValues contentValues = new ContentValues();
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ColunaTabela.class) || field.isAnnotationPresent(ChavePrimaria.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(this) != null) {
                        switch (field.getType().getSimpleName().toUpperCase()) {
                            case "STRING":
                                contentValues.put(field.getName(), field.get(this).toString());
                                break;
                            case "BOOLEAN":
                                contentValues.put(field.getName(), field.getBoolean(this));
                                break;
                            case "INT":
                                contentValues.put(field.getName(), field.getInt(this));
                                break;
                            case "INTEGER":
                                contentValues.put(field.getName(), (Integer) field.get(this));
                                break;
                            case "LONG":
                                contentValues.put(field.getName(), field.getLong(this));
                                break;
                            case "SHORT":
                                contentValues.put(field.getName(), (Short) field.get(this));
                                break;
                            case "DOUBLE":
                                contentValues.put(field.getName(), field.getDouble(this));
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

}
