package com.app.bdframework.utils;

import android.os.Environment;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.app.bdframework.excecoes.TratamentoExcecao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by Robson on 20/11/2016.
 */

public class GeradorArquivo {

    public static String getPastaExternaAplicacao() {
        File diretorio = new File(Environment.getExternalStorageDirectory(), Constantes.APP_VENDAS_PATH);
        if (!diretorio.exists())
            diretorio.mkdirs();
        return diretorio.getAbsolutePath();
    }

    public static void copiarArquivo(String fonte, String nomeArquivo, String destino) {
        InputStream in = null;
        OutputStream out = null;

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
                in = null;

                out.flush();
                out.close();
                out = null;
            } catch (FileNotFoundException e) {
                TratamentoExcecao.registrarExcecao(e);
            } catch (IOException e) {
                TratamentoExcecao.registrarExcecao(e);
            } finally {
                TratamentoExcecao.invocarEvento();
            }
        }
    }

    public static boolean validarExistenciaDiretorio(String diretorioArquivo) {
        File arquivo = new File(diretorioArquivo);
        if (arquivo.isDirectory()) {
            if (!arquivo.exists())
                arquivo.mkdirs();
            return arquivo.exists();
        }
        return validarExistenciaDiretorio(arquivo.getParent());
    }

}
