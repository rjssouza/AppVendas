package com.app.appvenda.conversores;

import com.app.appvenda.entidade.Configuracao;
import com.app.appvenda.enums.EnumTipoConfiguracao;
import com.app.appvenda.modelos.MConfiguracao;
import com.app.bdframework.conversor.Conversor;

/**
 * Created by Robson on 04/12/2016.
 */

public class ConversorConfiguracao extends Conversor<MConfiguracao, Configuracao> {

    @Override
    public Configuracao converterDePara(MConfiguracao mConfiguracao) {
        Configuracao configuracao = new Configuracao(null);
        configuracao.setId_configuracao(mConfiguracao.getIdConfiguracao());
        configuracao.setEndereco_servico(mConfiguracao.getEnderecoServico().toString());
        configuracao.setTipo_config(mConfiguracao.getTipoConfig().getNumVal().shortValue());
        configuracao.setPasta_cliente(mConfiguracao.getPastaCliente());
        configuracao.setPasta_estoque(mConfiguracao.getPastaEstoque());
        configuracao.setPasta_fotos(mConfiguracao.getPastaFotos());
        configuracao.setPasta_venda(mConfiguracao.getPastaVenda());
        configuracao.setPasta_vendedor(mConfiguracao.getPastaVendedor());
        configuracao.setPasta_produto(mConfiguracao.getPastaProduto());
        configuracao.setPrincipal(mConfiguracao.isPrincipal());
        return configuracao;
    }

    @Override
    public MConfiguracao converterParaDe(Configuracao configuracao) {
        MConfiguracao mConfiguracao = new MConfiguracao();
        mConfiguracao.setPastaCliente(configuracao.getPasta_cliente());
        EnumTipoConfiguracao enumTipoConfiguracao;
        if (configuracao.getTipo_config() == 1)
            enumTipoConfiguracao = EnumTipoConfiguracao.DROPBOX;
        else
            enumTipoConfiguracao = EnumTipoConfiguracao.SERVICO;
        mConfiguracao.setTipoConfig(enumTipoConfiguracao);
        mConfiguracao.setIdConfiguracao(configuracao.getId_configuracao());
        mConfiguracao.setPastaEstoque(configuracao.getPasta_estoque());
        mConfiguracao.setPastaVendedor(configuracao.getPasta_vendedor());
        mConfiguracao.setEnderecoServico(configuracao.getEndereco_servico());
        mConfiguracao.setPastaProduto(configuracao.getPasta_produto());
        mConfiguracao.setPastaVenda(configuracao.getPasta_venda());
        mConfiguracao.setPastaFotos(configuracao.getPasta_fotos());
        mConfiguracao.setPrincipal(configuracao.isPrincipal());
        return mConfiguracao;
    }

}
