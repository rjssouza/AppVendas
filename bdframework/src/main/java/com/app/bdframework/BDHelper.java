package com.app.bdframework;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.bdframework.baseEntidade.Entidade;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.app.bdframework.utils.Constantes;
import com.app.bdframework.utils.GeradorArquivo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qie representa o acesso a instancia do BD local, possui metodos para conversao e query de entidaes
 */
public abstract class BDHelper<TEntidade extends Entidade> extends SQLiteOpenHelper implements IExecutorQuery {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "VendasApp";

    private static String _dataBasePath;
    private final Context _context;

    protected BDHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        _context = context;
        _dataBasePath = getWritableDatabase().getPath();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            InputStream stream = _context.getApplicationContext().getAssets()
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
        try {
            Cursor mCount = this.getReadableDatabase().rawQuery("select count(*) from " + getNomeTabela() +
                    whereClause, argumentos);
            int count = -1;
            if (mCount.moveToFirst())
                count = mCount.getInt(0);
            mCount.close();
            return count;
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
        return 0;
    }

    @Override
    public List<TEntidade> executarQuery(String[] colunas, String whereClause, String[] argumentos) {
        try {
            List<TEntidade> tEntidades = new ArrayList<>();
            Cursor cursor = this.getReadableDatabase().query(getNomeTabela(), colunas, whereClause, argumentos, null, null, null);
            cursor.moveToPosition(0);
            do {
                TEntidade entidade = obterEntidade(cursor);
                tEntidades.add(entidade);
            } while (cursor.moveToNext());
            return tEntidades;
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
        return null;
    }

    @Override
    public TEntidade executarUnico(String[] colunas, String whereClause, String[] argumentos) {
        try {
            Cursor cursor = this.getReadableDatabase().query(getNomeTabela(),  colunas, whereClause, argumentos, null, null, null);
            TEntidade _entidade;
            cursor.moveToPosition(0);
            do {
                _entidade = obterEntidade(cursor);
            } while (cursor.moveToNext());
            return _entidade;
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
        return null;
    }

    protected abstract TEntidade obterEntidade(Cursor cursor);

    protected abstract String getNomeTabela();

    public void salvarBDLocal() {
        GeradorArquivo.copiarArquivo(_dataBasePath, DATABASE_NAME + ".db", GeradorArquivo.getPastaExternaAplicacao());
    }

}
