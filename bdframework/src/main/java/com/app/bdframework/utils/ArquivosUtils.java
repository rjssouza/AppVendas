package com.app.bdframework.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import com.app.bdframework.BDHelper;
import com.app.bdframework.excecoes.TratamentoExcecao;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * Classe para gerar arquivos de qualquer extensao
 */
public class ArquivosUtils {

    public static String getPastaExternaAplicacao() {
        File diretorio = new File(Environment.getExternalStorageDirectory(), Constantes.APP_VENDAS_PATH);
        if (!diretorio.exists()) {
            if (diretorio.mkdirs())
                return diretorio.getAbsolutePath();
        }
        return diretorio.getAbsolutePath();
    }

    public static void copiarArquivo(String fonte, String nomeArquivo, String destino) {
        InputStream in;
        OutputStream out;

        if (validarExistenciaDiretorio(destino)) {
            try {
                in = new FileInputStream(fonte);
                out = new FileOutputStream(destino + "/" + nomeArquivo);

                byte[] buffer = new byte[1024];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }
                in.close();

                out.flush();
                out.close();
            } catch (IOException ex) {
                TratamentoExcecao.registrarExcecao(ex);
            } finally {
                TratamentoExcecao.invocarEvento();
            }
        }
    }

    public static void criarArquivoTexto(String texto, String destino, String nomeArquivo) {
        try {
            if (validarExistenciaDiretorio(destino)) {

                String dest = destino + "/" + nomeArquivo + ".txt";
                File arquivo = new File(dest);

                if (arquivo.exists() || arquivo.createNewFile()) {
                    FileWriter fw = new FileWriter(arquivo, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw);
                    out.println(texto);
                    out.flush();
                    out.close();
                }
            }
        } catch (IOException ex) {
            TratamentoExcecao.registrarExcecao(ex);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

    private static boolean validarExistenciaDiretorio(String diretorioArquivo) {
        File arquivo = new File(diretorioArquivo);
        arquivo.mkdirs();
        return true;
    }

    public static void deleteCache(Context context) {
        try {
            SharedPreferences settings = context.getSharedPreferences("cda-preferences", Context.MODE_PRIVATE);
            settings.edit().clear().commit();
            File dir = context.getCacheDir();
            deleteDir(dir);
            context.deleteDatabase(BDHelper.DATABASE_NAME);
        } catch (Exception e) {
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

}
