package com.app.appvenda;

import android.app.Activity;

import com.app.appvenda.interfaces.IBaseViews;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Classe base para activities
 */
@EActivity
public abstract class BaseActivity extends Activity implements IBaseViews {

    @AfterViews
    public void init(){
    }

}
