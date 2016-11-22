package com.app.bdframework;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.app.bdframework.baseEntidade.Entidade;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.app.bdframework.utils.Constantes;
import com.app.bdframework.utils.GeradorArquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robson on 12/11/2016.
 */

public abstract class BDHelper<TEntidade extends Entidade> extends SQLiteOpenHelper implements IExecutorQuery {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "VendasApp";

    private static String dataBasePath;
    protected static SQLiteOpenHelper instancia;
    protected static Context context;

    public BDHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        this.dataBasePath = getWritableDatabase().getPath();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            if (instancia == null) {
                InputStream stream = context.getApplicationContext().getAssets()
                        .open(Constantes.SCRIPT_VENDAS_APP);
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(stream));
                String line = br.readLine();
                while (line != null) {
                    if (line.length() > 0 && !line.contains("--")) {
                        db.execSQL(line);
                    }
                    line = br.readLine();
                }
                instancia = this;
            }
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public int executarScalar(String whereClause, String[] argumentos) {
        Cursor mCount = instancia.getReadableDatabase().rawQuery("select count(*) from " + getNomeTabela() +
                whereClause, argumentos);
        int count = -1;
        if (mCount.moveToFirst())
            count = mCount.getInt(0);
        mCount.close();
        return count;
    }

    @Override
    public List<TEntidade> executarQuery(String[] colunas, String whereClause, String[] argumentos) {
        List<TEntidade> tEntidades = new ArrayList<>();
        Cursor cursor = instancia.getReadableDatabase().query(getNomeTabela(), colunas, whereClause, argumentos, null, null, null);
        while (cursor.moveToNext()) {
            TEntidade entidade = obterEntidade(cursor);
            tEntidades.add(entidade);
        }
        return tEntidades;
    }

    @Override
    public TEntidade executarUnico(String[] colunas, String whereClause, String[] argumentos) {
        Cursor cursor = instancia.getReadableDatabase().query(getNomeTabela(), colunas, whereClause, argumentos, null, null, null);
        while (cursor.moveToNext()) {
            TEntidade entidade = obterEntidade(cursor);
            return entidade;
        }
        return null;
    }

    protected abstract TEntidade obterEntidade(Cursor cursor);

    protected abstract String getNomeTabela();

    public void salvarBDLocal() {
        GeradorArquivo.copiarArquivo(dataBasePath, DATABASE_NAME + ".db", GeradorArquivo.getPastaExternaAplicacao());
    }

}
