package com.taikang.latter_core.app;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
 * Time：2018/12/1
 * Author: gaonz
 * Description:
 */
public class Latte {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static HashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    //获取application上下文
    public static Context getApplicationContext() {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT);
    }

    //获取api_host
    public static String getApiHost() {
        return (String) getConfigurations().get(ConfigType.API_HOST);
    }

    //获取interceptors
    @SuppressWarnings("unchecked")
    public static ArrayList<Interceptor> getInterceptors() {
        return (ArrayList<Interceptor>) getConfigurations().get(ConfigType.INTERCEPTOR);
    }
}
