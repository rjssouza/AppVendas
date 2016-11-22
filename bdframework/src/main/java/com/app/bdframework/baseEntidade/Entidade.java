package com.app.bdframework.baseEntidade;

import android.content.ContentValues;
import android.database.Cursor;

import com.app.bdframework.auxiliar.CampoTabela;
import com.app.bdframework.auxiliar.NomeTabela;
import com.app.bdframework.auxiliar.ParCampoValor;
import com.app.bdframework.auxiliar.TipoCampo;

import java.lang.annotation.Annotation;
import java.lang.annotation.IncompleteAnnotationException;
import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.Map;

/**
 * Created by Robson on 12/11/2016.
 */
public class Entidade {

    public Entidade(Cursor cursor) {
        if (cursor != null) {
            for (Field field : this.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(CampoTabela.class)) {
                    field.setAccessible(true);
                    CampoTabela campoTabela = field.getAnnotation(CampoTabela.class);
                    try {
                        switch (campoTabela.tipoCampo().toString()) {
                            case "VARCHAR":
                                field.set(this, cursor.getString(cursor.getColumnIndex(campoTabela.nomeCampo())));
                                break;
                            case "BOOLEAN":
                                field.set(this, (cursor.getShort(cursor.getColumnIndex(campoTabela.nomeCampo())) > 0));
                                break;
                            case "INTEGER":
                                field.set(this, cursor.getInt(cursor.getColumnIndex(campoTabela.nomeCampo())));
                                break;
                            case "LONG":
                                field.set(this, cursor.getLong(cursor.getColumnIndex(campoTabela.nomeCampo())));
                                break;
                            default:
                                break;
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public String getNomeTabela() {
        if (this.getClass().isAnnotationPresent(NomeTabela.class)) {
            return this.getClass().getAnnotation(NomeTabela.class).nomeTabela();
        }
        return "";
    }

    public ParCampoValor<Integer> getChavePrimaria() {
        ContentValues contentValues = new ContentValues();
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(CampoTabela.class)) {
                field.setAccessible(true);
                CampoTabela campoTabela = field.getAnnotation(CampoTabela.class);
                if (campoTabela.isprimary()) {
                    try {
                        return new ParCampoValor<>(field.getInt(this), campoTabela);
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
            if (field.isAnnotationPresent(CampoTabela.class)) {
                field.setAccessible(true);
                CampoTabela campoTabela = field.getAnnotation(CampoTabela.class);
                try {
                    switch (campoTabela.tipoCampo().toString()) {
                        case "VARCHAR":
                            contentValues.put(campoTabela.nomeCampo(), field.get(this).toString());
                            break;
                        case "BOOLEAN":
                            contentValues.put(campoTabela.nomeCampo(), field.getBoolean(this));
                            break;
                        case "INTEGER":
                            contentValues.put(campoTabela.nomeCampo(), field.getInt(this));
                            break;
                        case "LONG":
                            contentValues.put(campoTabela.nomeCampo(), field.getLong(this));
                            break;
                        default:
                            break;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return contentValues;
    }

}
