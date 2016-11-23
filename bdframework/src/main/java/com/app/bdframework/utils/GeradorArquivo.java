package com.app.bdframework.utils;

import android.os.Environment;

import com.app.bdframework.excecoes.TratamentoExcecao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Classe para gerar arquivos de qualquer extensao
 */
public class GeradorArquivo {

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

    private static boolean validarExistenciaDiretorio(String diretorioArquivo) {
        File arquivo = new File(diretorioArquivo);
        if (arquivo.isDirectory()) {
            if (!arquivo.exists()) {
                return arquivo.mkdirs();
            }
            return arquivo.exists();
        }
        return validarExistenciaDiretorio(arquivo.getParent());
    }

}
