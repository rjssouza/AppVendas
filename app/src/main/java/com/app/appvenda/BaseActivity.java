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

    IFragment fragmentAtual;
    List<Class> listaFragment;

    @AfterViews
    public void init() {
        listaFragment = new ArrayList<>();
    }

    @Override
    public void onBackPressed() {
        if (fragmentAtual != null) {
            fragmentAtual.voltar();
        }
    }

    protected void carregarFragment(IFragment iFragment) {
        Class fragmentClass = iFragment.getClass();
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

    protected void registrarFragment(Class fragment) {
        this.listaFragment.add(fragment);
    }

}
