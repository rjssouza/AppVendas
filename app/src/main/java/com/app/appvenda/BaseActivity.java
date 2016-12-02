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

    @AfterViews
    public void init() {
    }

}
