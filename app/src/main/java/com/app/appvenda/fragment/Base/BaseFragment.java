package com.app.appvenda.fragment.Base;

import android.support.v4.app.Fragment;

import com.app.appvenda.interfaces.IBaseViews;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Robson on 29/11/2016.
 */
@EFragment
public abstract class BaseFragment extends Fragment implements IBaseViews {

    @AfterViews
    public void init() {

    }

}
