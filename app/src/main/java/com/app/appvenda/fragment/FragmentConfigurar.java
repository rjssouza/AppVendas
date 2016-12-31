package com.app.appvenda.fragment;

import android.widget.EditText;
import android.widget.Switch;

import com.app.appvenda.dao._ConfiguracaoDAO;
import com.app.appvenda.R;
import com.app.appvenda.enums.EnumTipoConfiguracao;
import com.app.appvenda.fragment.base.BaseFragmentRN;
import com.app.appvenda.modelos.MConfiguracao;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.TratamentoExcecao;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.net.URI;

/**
 * Created by Robson on 03/12/2016.
 */
@EFragment(R.layout.fragment_configurar)
public class FragmentConfigurar extends BaseFragmentRN {

    @ViewById
    Switch swtTipoConfig;
    @ViewById
    EditText txtEndServicio;
    @ViewById
    EditText txtPastaFotos;
    @ViewById
    EditText txtPastaCliente;
    @ViewById
    EditText txtPastaEstoque;
    @ViewById
    EditText txtPastaProdutos;
    @ViewById
    EditText txtPastaVenda;
    @ViewById
    EditText txtPastaVendedor;
    @ViewById
    EditText txtPastaFormaPagto;

    private MConfiguracao mConfiguracao;
    private _ConfiguracaoDAO configuracaoDAO;

    @AfterViews
    void Init() {
        configuracaoDAO = new _ConfiguracaoDAO(this.getContext());
        configuracaoDAO.setEventoPosExecucao(new EventoVoid<Boolean>() {
            @Override
            public void executarEvento(Boolean item) throws Exception {
                if (item)
                    exibirMensagemToast(R.string.msg_config_salva_sucesso);
                esconderProgress();
            }
        });
        mConfiguracao = configuracaoDAO.obterConfiguracaoAtiva() == null ? new MConfiguracao() : configuracaoDAO.obterConfiguracaoAtiva();

        if (mConfiguracao.getTipoConfig() == EnumTipoConfiguracao.DROPBOX) {
            swtTipoConfig.setChecked(true);
        } else {
            mConfiguracao.setTipoConfig(EnumTipoConfiguracao.SERVICO);
            swtTipoConfig.setText("SERVICO");
            configuracaoDAO.transformarPrincipal(EnumTipoConfiguracao.SERVICO);
            preencherTela();
        }
    }

    @UiThread
    void configurarPreenchimento() {
        if (mConfiguracao.getEnderecoServico() != null)
            txtEndServicio.setText(mConfiguracao.getEnderecoServico().toString());
        else
            txtEndServicio.setText("");
        txtPastaCliente.setText(mConfiguracao.getPastaCliente());
        txtPastaEstoque.setText(mConfiguracao.getPastaEstoque());
        txtPastaFotos.setText(mConfiguracao.getPastaFotos());
        txtPastaProdutos.setText(mConfiguracao.getPastaProduto());
        txtPastaVenda.setText(mConfiguracao.getPastaVenda());
        txtPastaVendedor.setText(mConfiguracao.getPastaVendedor());
        txtPastaFormaPagto.setText(mConfiguracao.getPastaFormaPagto());
    }

    @CheckedChange(R.id.swtTipoConfig)
    void tipoConfig() {
        mConfiguracao = new MConfiguracao();
        if (swtTipoConfig.isChecked()) {
            mConfiguracao.setTipoConfig(EnumTipoConfiguracao.DROPBOX);
            swtTipoConfig.setText("DROPBOX");
            configuracaoDAO.transformarPrincipal(EnumTipoConfiguracao.DROPBOX);
        } else {
            mConfiguracao.setTipoConfig(EnumTipoConfiguracao.SERVICO);
            swtTipoConfig.setText("SERVICO");
            configuracaoDAO.transformarPrincipal(EnumTipoConfiguracao.SERVICO);
        }
        preencherTela();
    }

    private void preencherTela() {
        MConfiguracao mConfiguracao1 = configuracaoDAO.obterConfiguracao(mConfiguracao.getTipoConfig());
        mConfiguracao = mConfiguracao1 != null ? mConfiguracao1 : mConfiguracao;
        configurarPreenchimento();
    }

    @Click(R.id.btnSalvar)
    void salvarConfig() {
        exibirProgress(R.string.aguarde, false);
        String enderecoUri = txtEndServicio.getText().toString();
        URI uri = null;

        try {
            uri = new URI(enderecoUri);
            mConfiguracao.setEnderecoServico(uri.toString());
            mConfiguracao.setPastaCliente(txtPastaCliente.getText().toString());
            mConfiguracao.setPastaEstoque(txtPastaEstoque.getText().toString());
            mConfiguracao.setPastaProduto(txtPastaProdutos.getText().toString());
            mConfiguracao.setPastaFotos(txtPastaFotos.getText().toString());
            mConfiguracao.setPastaVenda(txtPastaVenda.getText().toString());
            mConfiguracao.setPastaVendedor(txtPastaVendedor.getText().toString());
            mConfiguracao.setPastaFormaPagto(txtPastaFormaPagto.getText().toString());
            mConfiguracao.setPrincipal(true);
            configuracaoDAO.salvar(mConfiguracao, null);
        } catch (Exception e) {
            esconderProgress();
            TratamentoExcecao.registrarExcecao(e);
        } finally {
            TratamentoExcecao.invocarEvento();
        }
    }

}
