package com.app.bdframework;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.bdframework.auxiliar.NomeTabela;
import com.app.bdframework.baseEntidade.Entidade;
import com.app.bdframework.baseEntidade.ParCampoValor;
import com.app.bdframework.excecoes.TratamentoExcecao;
import com.app.bdframework.utils.ArquivosUtils;
import com.app.bdframework.utils.Constantes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Classe qie representa o acesso a instancia do BD local, possui metodos para conversao e query de entidaes
 */
public class BDHelper<TEntidade extends Entidade> extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "VendasApp";
    private static final int DATABASE_VERSION = 1;

    private Context _context;
    private String _dataBasePath;
    private SQLiteDatabase database;

    private static BDHelper helper;

    BDHelper(Context context, Class<TEntidade> tEntidadeClass) {
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
            TratamentoExcecao.chamarOnPrimeiroAcesso();
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static <T extends Entidade> BDHelper<T> getBDHelper(Context context, Class<T> tEntidadeClass) {
        if (helper == null) {
            helper = new BDHelper<T>(context, tEntidadeClass);
        }
        return helper;
    }

    public synchronized void salvarBDLocal() {
        ArquivosUtils.copiarArquivo(_dataBasePath, DATABASE_NAME + ".db", ArquivosUtils.getPastaExternaAplicacao());
    }

    public synchronized SQLiteDatabase getDatabase() {
        if (database == null)
            database = SQLiteDatabase.openDatabase(_dataBasePath, null, 0);
        return database;
    }

    public synchronized int executarScalar(String whereClause, String[] argumentos, Class<TEntidade> tEntidadeClass) {
        try {
            Cursor mCount = this.getReadableDatabase().rawQuery("select count(*) from " + getNomeTabela(tEntidadeClass) +
                    " where " + whereClause, argumentos);
            int count = -1;
            if (mCount.moveToFirst()) {
                do {
                    count = mCount.getInt(0);
                } while (mCount.moveToNext());

                mCount.close();
                return count;
            }
        } catch (Exception e) {
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
        return 0;
    }

    public synchronized boolean salvarEntidade(TEntidade entidade) {
        boolean sucesso = false;
        ParCampoValor parCampoValor = entidade.getChavePrimaria();
        boolean existe = this.executarScalar(parCampoValor.getNomeCampo() + " = ?",
                new String[]{parCampoValor.getValor() == null ? "" : parCampoValor.getValor().toString()}, (Class<TEntidade>) entidade.getClass()) > 0;
        if (existe)
            sucesso = getDatabase().update(getNomeTabela((Class<TEntidade>) entidade.getClass()), entidade.getContentValue(),
                    parCampoValor.getNomeCampo() + " = ?",
                    new String[]{parCampoValor.getValor().toString()}) > 0;
        else
            sucesso = getDatabase().insert(getNomeTabela((Class<TEntidade>) entidade.getClass()), null, entidade.getContentValue()) > 0;
        return sucesso;
    }

    public synchronized boolean atualizarEntidade(TEntidade entidade, String queryString, String... parametros) {
        boolean sucesso = false;

        sucesso = getDatabase().update(getNomeTabela((Class<TEntidade>) entidade.getClass()), entidade.getContentValue(),
                queryString, parametros) > 0;

        return sucesso;
    }

    public synchronized boolean deletarEntidade(TEntidade entidade) {
        boolean sucesso = false;
        ParCampoValor<Integer> parCampoValor = entidade.getChavePrimaria();
        sucesso = getDatabase().delete(getNomeTabela((Class<TEntidade>) entidade.getClass()),
                "where " + parCampoValor.getNomeCampo() + " = ?",
                new String[]{parCampoValor.getValor().toString()}) > 0;
        return sucesso;
    }

    public String getNomeTabela(Class<TEntidade> tEntidadeClass) {
        NomeTabela nomeTabela = tEntidadeClass.getAnnotation(NomeTabela.class);
        if (nomeTabela != null)
            return nomeTabela.nomeTabela();
        return "";
    }

}
