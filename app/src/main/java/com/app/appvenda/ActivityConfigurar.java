package com.app.appvenda;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.app.appvenda.base.BaseActivity;
import com.app.appvenda.dao.ConfiguracaoDAO;
import com.app.appvenda.exportadorVenda.ExportadorVendas;
import com.app.appvenda.fragment.FragmentConfigurar;
import com.app.appvenda.fragment.FragmentConfigurar_;
import com.app.appvenda.fragment.FragmentSelecionarVendedor;
import com.app.appvenda.fragment.FragmentSelecionarVendedor_;
import com.app.appvenda.modelos.MConfiguracao;
import com.app.appvenda.modelos.MVendedor;
import com.app.appvenda.utils.InformacoesVendedor;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.eventos.EventosCaixaDialogo;
import com.app.bdframework.excecoes.RegraNegocioMensagem;
import com.app.bdframework.utils.ArquivosUtils;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

/**
 * Created by Robson on 12/01/2017.
 */
@EActivity(R.layout.activity_configurar_usuario)
public class ActivityConfigurar extends BaseActivity {


    private MConfiguracao mConfiguracao;
    private ExportadorVendas exportadorVendas;
    private ConfiguracaoDAO configuracaoDAO;
    private Context context;

    @Override
    protected void afterViews() {
        this.context = this;
        this.configuracaoDAO = new ConfiguracaoDAO(context);
        configurarSincronizacao();

        mConfiguracao = configuracaoDAO.obterConfiguracaoAtiva();
        if (mConfiguracao != null) {
            sincronizar();
        } else {
            chamarFragmentConfiguracao();
        }
    }

    private void chamarFragmentConfiguracao() {
        chamarFragment(FragmentConfigurar_.class, R.id.flConteudo, new EventoVoid<Fragment>() {
            @Override
            public void executarEvento(Fragment item) throws Exception {
                final FragmentConfigurar fragmentConfigurar = ((FragmentConfigurar) item);
                fragmentConfigurar.setPosSalvar(new EventoVoid<MConfiguracao>() {
                    @Override
                    public void executarEvento(MConfiguracao item) throws Exception {
                        fragmentConfigurar.exibirProgress(R.string.aguarde, false);
                        sincronizar();
                    }
                });
            }
        });
    }

    private void chamarFragmentVendedor() {
        chamarFragment(FragmentSelecionarVendedor_.class, R.id.flConteudo, new EventoVoid<Fragment>() {
            @Override
            public void executarEvento(Fragment item) throws Exception {
                ((FragmentSelecionarVendedor) item).setPosSalvar(new EventoVoid<MVendedor>() {
                    @Override
                    public void executarEvento(MVendedor item) throws Exception {
                        esconderProgress();
                        InformacoesVendedor.getInstance(getBaseContext());
                        configuracaoDAO.salvarBD();
                        Intent intent = new Intent(context, MainActivity_.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

    @Override
    protected void executarAlerta(RegraNegocioMensagem item) {

    }

    @Override
    protected void executarPergunta(RegraNegocioMensagem item) {

    }

    @Override
    public void executarEvento(RegraNegocioMensagem item) {
        exibirMensagemToast(item.getMensagem());
        ArquivosUtils.deleteCache(this);
    }

    @Override
    public void onBackPressed() {
        exibirDialogoAlerta(R.string.confirma_acao, R.string.msg_sair, new EventosCaixaDialogo() {
            @Override
            public void onClickPositivo() {
                ArquivosUtils.deleteCache(getApplicationContext());
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }

            @Override
            public void onClickNegativo() {

            }
        });
    }

    private void configurarSincronizacao() {
        this.exportadorVendas = new ExportadorVendas(this);
        this.exportadorVendas.setEventoPosProcessamento(new EventoVoid<Boolean>() {
            @Override
            public void executarEvento(Boolean item) throws Exception {
                esconderProgress();
                chamarFragmentVendedor();
                if (item) {
                    exibirSucesso();
                }
            }
        });
    }

    @UiThread
    void exibirSucesso() {
        exibirMensagemToast(R.string.sucesso_sinc);
    }

    @UiThread
    void sincronizar() {
        exibirProgress(R.string.aguarde, false);
        exportadorVendas.evetuarSincronizacao();
    }

}
