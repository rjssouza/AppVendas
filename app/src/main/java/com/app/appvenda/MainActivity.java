package com.app.appvenda;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.app.appvenda.base.BaseActivityRN;
import com.app.appvenda.exportadorVenda.ExportadorVendas;
import com.app.appvenda.exportadorVenda.ExportadorVendasDropBox;
import com.app.appvenda.fragment.FragmentConfigurar_;
import com.app.appvenda.fragment.FragmentVendas_;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioMensagem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;


@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivityRN implements NavigationView.OnNavigationItemSelectedListener {

    private static final String FRAGM_VENDAS = "FragmentVendas";
    private static final String FRAGM_CONFIG = "FragmentConfigurar";

    @ViewById
    Toolbar toolbar;
    @ViewById
    DrawerLayout drawerLayout;
    @ViewById
    NavigationView nvView;

    ActionBarDrawerToggle mDrawerToggle;
    ExportadorVendas exportadorVendas;

    @AfterViews
    void Init() {
        configurarDrawerLayout();
        configurarFragmentPrincipal();
        this.exportadorVendas = new ExportadorVendasDropBox(this);
    }

    private void configurarDrawerLayout() {
        setSupportActionBar(toolbar);
        nvView.setNavigationItemSelectedListener(this);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(mDrawerToggle);
    }

    private void configurarFragmentPrincipal() {
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.flConteudo, new FragmentVendas_());
        tx.commit();
        this.nvView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch ((String) item.getTitleCondensed()) {
            case FRAGM_VENDAS:
                return chamarFragment(FragmentVendas_.class, item, null);
            case FRAGM_CONFIG:
                return chamarFragment(FragmentConfigurar_.class, item, null);
            default:
                this.sincronizar();
                return true;
        }
    }

    @Background
    void sincronizar() {
        this.exportadorVendas.exportarVendas();
        this.exportadorVendas.importarBaseDados();
    }

    private <T extends Fragment> boolean chamarFragment(Class fragmentClass, MenuItem item, EventoVoid<T> sucessoChamadaFragment) {
        try {
            T fragment = (T) fragmentClass.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flConteudo, fragment).commit();
            if (sucessoChamadaFragment != null)
                sucessoChamadaFragment.executarEvento(fragment);
            item.setChecked(true);
            setTitle(item.getTitle());
            drawerLayout.closeDrawers();
            return true;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    protected void executarAlerta(RegraNegocioMensagem item) {
        this.exibirMensagemToast(item.getMensagem());
    }

    @Override
    protected void executarPergunta(RegraNegocioMensagem item) {
        this.exibirMensagemToast(item.getMensagem());
    }

    @Override
    @UiThread
    protected void executarErro(RegraNegocioMensagem item) {
        this.exibirMensagemToast(item.getMensagem());
    }
}
