package com.app.appvenda;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.app.appvenda.interfaces.IBaseViews;
import com.app.appvenda.interfaces.IFragment;
import com.app.bdframework.enums.EnumTipoMensagem;
import com.app.bdframework.eventos.EventoVoid;
import com.app.bdframework.excecoes.RegraNegocioException;
import com.app.bdframework.excecoes.RegraNegocioMensagem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe base para activities
 */
@EActivity
public abstract class BaseActivity extends AppCompatActivity implements IBaseViews {

    @ViewById
    Toolbar toolbar;
    @ViewById
    DrawerLayout drawer_layout;
    @ViewById
    NavigationView nvView;

    ActionBarDrawerToggle mDrawerToggle;
    IFragment fragmentAtual;
    List<IFragment> listaFragment;

    @AfterViews
    public void init() {
        setSupportActionBar(toolbar);
        setupDrawerContent(nvView);
        mDrawerToggle = setupDrawerToggle();
        drawer_layout.addDrawerListener(mDrawerToggle);
        listaFragment = new ArrayList<>();
    }

    public void registrarFragment(IFragment fragment) {
        this.listaFragment.add(fragment);
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
    public void onBackPressed() {
        if (fragmentAtual != null) {
            fragmentAtual.voltar();
        }
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
        Class fragmentClass = null;

        for (IFragment iFragment : listaFragment) {
            if (iFragment.getFragmentID().equals(menuItem.getTitleCondensed())) {
                fragmentAtual = iFragment;
                fragmentAtual.registrarEventoVoltar(new EventoVoid<IFragment>() {
                    @Override
                    public void executarEvento(IFragment item) {
                        carregarFragment(item.getClass());
                        menuItem.setChecked(true);
                    }
                });
            }
        }

        carregarFragment(fragmentAtual.getClass());

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawer_layout.closeDrawers();
    }

    private void carregarFragment(Class fragmentClass) {
        Fragment fragment = null;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flConteudo, fragment).commit();
    }

}
