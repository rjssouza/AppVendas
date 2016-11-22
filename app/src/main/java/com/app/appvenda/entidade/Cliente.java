package com.app.appvenda.entidade;

import android.database.Cursor;

import com.app.bdframework.auxiliar.CampoTabela;
import com.app.bdframework.auxiliar.NomeTabela;
import com.app.bdframework.auxiliar.TipoCampo;
import com.app.bdframework.baseEntidade.Entidade;

/**
 * Created by Robson on 13/11/2016.
 */
@NomeTabela(nomeTabela = "tb_cliente")
public class Cliente extends Entidade {

    public Cliente(Cursor cursor) {
        super(cursor);
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    @CampoTabela(nomeCampo = "id_cliente", isprimary = true, tipoCampo = TipoCampo.INTEGER)
    private int id_cliente;

}
