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
import com.app.appvenda.fragment.FragmentVendas_;
import com.app.appvenda.modelos.MConfiguracao;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.eventos.EventosCaixaDialogo;
import com.app.bdframework.excecoes.RegraNegocioMensagem;
import com.app.bdframework.utils.ArquivosUtils;

import org.androidannotations.annotations.EActivity;

/**
 * Created by Robson on 12/01/2017.
 */
@EActivity(R.layout.activity_configurar_usuario)
public class ActivityConfigurar extends BaseActivity {


    private MConfiguracao mConfiguracao;
    private ExportadorVendas exportadorVendas;
    private ConfiguracaoDAO configuracaoDAO;
    private FragmentConfigurar_ fragmentConfigurar_;
    private Context context;

    @Override
    protected void afterViews() {
        this.context = this;
        configurarSincronizacao();

        mConfiguracao = configuracaoDAO.obterConfiguracaoAtiva();
        if (mConfiguracao != null) {
            chamarFragmentVendedor();
        } else {
            chamarFragmentConfiguracao();
        }
    }

    private void chamarFragmentConfiguracao() {
        chamarFragment(FragmentConfigurar_.class, R.id.flConteudo, new EventoVoid<Fragment>() {
            @Override
            public void executarEvento(Fragment item) throws Exception {
                ((FragmentConfigurar) item).setPosSalvar(new EventoVoid<MConfiguracao>() {
                    @Override
                    public void executarEvento(MConfiguracao item) throws Exception {
                        chamarFragmentVendedor();
                    }
                });
            }
        });
    }

    private void chamarFragmentVendedor() {
        chamarFragment(FragmentVendas_.class, R.id.flConteudo, new EventoVoid<Fragment>() {
            @Override
            public void executarEvento(Fragment item) throws Exception {
                ((FragmentSelecionarVendedor) item).setPosSalvar(new EventoVoid<MConfiguracao>() {
                    @Override
                    public void executarEvento(MConfiguracao item) throws Exception {
                        Intent intent = new Intent(context, MainActivity_.class);
                        startActivity(intent);
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
                if (item) {
                    esconderProgress();
                }
            }
        });
    }

    private void sincronizar() {
        exibirProgress(R.string.aguarde, false);
        this.exportadorVendas.evetuarSincronizacao();
    }

}
