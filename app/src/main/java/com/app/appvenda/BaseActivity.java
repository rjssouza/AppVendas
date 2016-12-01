package com.app.appvenda;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.app.appvenda.fragment.BaseFragment;
import com.app.appvenda.interfaces.IBaseViews;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe base para activities
 */
@EActivity
public abstract class BaseActivity extends AppCompatActivity implements IBaseViews {

    BaseFragment fragmentAtual;
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

    protected void carregarFragment(BaseFragment iFragment) {
        if (iFragment != null) {
            Class fragmentClass = iFragment.getClass();
            BaseFragment fragment = null;

            try {
                fragment = (BaseFragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flConteudo,  fragment).commit();
        }
    }

    protected void registrarFragment(Class fragment) {
        this.listaFragment.add(fragment);
    }

}
