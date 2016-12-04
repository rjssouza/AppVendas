package com.app.appvenda.base;

import android.support.v7.app.AppCompatActivity;

import com.app.appvenda.interfaces.IBaseViews;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Classe base para activities
 */
@EActivity
public abstract class BaseActivity extends AppCompatActivity implements IBaseViews {

    @AfterViews
    public void init() {

    }

}
