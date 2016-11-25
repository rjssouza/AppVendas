package com.app.bdframework.negocio;

import android.content.Context;

/**
 * Created by Robson on 23/11/2016.
 */

public abstract class RegraNegocioResource {

    private Context context;

    public RegraNegocioResource(Context context){
        this.context = context;
    }

    protected String getMsgRegraNegocio(){
        String resName = "msg_" + this.getClass().getSimpleName().toLowerCase();
        int resId = context.getResources().getIdentifier(resName, "string", context.getPackageName());
        return this.context.getResources().getString(resId);
    }
}
