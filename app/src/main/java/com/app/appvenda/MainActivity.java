package com.app.appvenda;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.appvenda.fragment.FragmentVendas_;
import com.app.appvenda.interfaces.IFragment;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioMensagem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewById
    Toolbar toolbar;
    @ViewById
    DrawerLayout drawer_layout;
    @ViewById
    NavigationView nvView;

    ActionBarDrawerToggle mDrawerToggle;

    @AfterViews
    void Init(){
        setSupportActionBar(toolbar);
        setupDrawerContent(nvView);
        mDrawerToggle = setupDrawerToggle();
        drawer_layout.addDrawerListener(mDrawerToggle);

        registrarFragment(com.app.appvenda.fragment.FragmentVendas.class);
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
                    fragmentAtual =  (IFragment) fragmentClass.newInstance();
                    fragmentAtual.registrarEventoVoltar(new EventoVoid<IFragment>() {
                        @Override
                        public void executarEvento(IFragment item) {
                            carregarFragment(item);
                            menuItem.setChecked(true);
                        }
                    });
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

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
