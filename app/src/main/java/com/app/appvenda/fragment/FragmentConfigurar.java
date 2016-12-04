package com.app.appvenda.fragment;

import android.widget.EditText;
import android.widget.Switch;

import com.app.appvenda.DAO.ConfiguracaoDAO;
import com.app.appvenda.R;
import com.app.appvenda.enums.EnumTipoConfiguracao;
import com.app.appvenda.fragment.Base.BaseFragmentRN;
import com.app.appvenda.modelos.MConfiguracao;
import com.app.bdframework.excecoes.RegraNegocioMensagem;
import com.app.bdframework.excecoes.TratamentoExcecao;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.net.URI;
import java.net.URISyntaxException;

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

    private MConfiguracao mConfiguracao;
    private ConfiguracaoDAO configuracaoDAO;

    @AfterViews
    void Init() {
        mConfiguracao = new MConfiguracao();
        configuracaoDAO = new ConfiguracaoDAO(this.getContext());
    }

    @CheckedChange(R.id.swtTipoConfig)
    void tipoConfig() {
        if (swtTipoConfig.isChecked()) {
            mConfiguracao.setTipoConfig(EnumTipoConfiguracao.DROPBOX);
            swtTipoConfig.setText("DROPBOX");
        } else {
            mConfiguracao.setTipoConfig(EnumTipoConfiguracao.SERVICO);
            swtTipoConfig.setText("SERVICO");
        }
    }

    @Click(R.id.btnSalvar)
    void salvarConfig() {
        String enderecoUri = txtEndServicio.getText().toString();
        URI uri = null;
        try {
            uri = new URI(enderecoUri);
            mConfiguracao.setEnderecoServico(uri);
            mConfiguracao.setIdConfiguracao(Integer.parseInt(txtEndServicio.getText().toString()));
            mConfiguracao.setPastaCliente(txtPastaCliente.getText().toString());
            mConfiguracao.setPastaEstoque(txtPastaEstoque.getText().toString());
            mConfiguracao.setPastaProduto(txtPastaProdutos.getText().toString());
            mConfiguracao.setPastaFotos(txtPastaFotos.getText().toString());
            mConfiguracao.setPastaVenda(txtPastaVenda.getText().toString());
            mConfiguracao.setPastaVendedor(txtPastaVendedor.getText().toString());

            configuracaoDAO.salvar(mConfiguracao, null);
        } catch (URISyntaxException e) {
            TratamentoExcecao.registrarExcecao(e);
        }
    }

    @Override
    protected void executarAlerta(RegraNegocioMensagem item) {

    }

    @Override
    protected void executarPergunta(RegraNegocioMensagem item) {

    }

    @Override
    protected void executarErro(RegraNegocioMensagem item) {

    }

}
