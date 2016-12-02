package com.app.appvenda;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.app.appvenda.exportadorVenda.ExportadorVendas;
import com.app.appvenda.exportadorVenda.ExportadorVendasDropBox;
import com.app.appvenda.fragment.BaseFragment;
import com.app.bdframework.eventos.EventoVoid;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private static final String SINC_ARQUIVOS = "Sincronizar";

    @ViewById
    Toolbar toolbar;
    @ViewById
    DrawerLayout drawer_layout;
    @ViewById
    NavigationView nvView;

    ActionBarDrawerToggle mDrawerToggle;
    ExportadorVendas exportadorVendas;

    @AfterViews
    void Init() {
        configurarDrawerLayout();
        this.exportadorVendas = new ExportadorVendasDropBox(this);
    }

    private void configurarDrawerLayout() {
        setSupportActionBar(toolbar);
        setupDrawerContent(nvView);
        mDrawerToggle = setupDrawerToggle();
        drawer_layout.addDrawerListener(mDrawerToggle);
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

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    private void selectDrawerItem(final MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        for (Class fragmentClass : listaFragment) {

            if (fragmentClass.getSimpleName().equals(menuItem.getTitleCondensed())) {
                try {
                    fragmentAtual = (BaseFragment) fragmentClass.newInstance();
                    fragmentAtual.registrarEventoVoltar(new EventoVoid<BaseFragment>() {
                        @Override
                        public void executarEvento(BaseFragment item) {
                            carregarFragment(item);
                            menuItem.setChecked(true);
                        }
                    });
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (menuItem.getTitleCondensed().equals(SINC_ARQUIVOS)) {
                exportadorVendas.importarBaseDados();
            }
        }

        carregarFragment(fragmentAtual);

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawer_layout.closeDrawers();
    }


}
