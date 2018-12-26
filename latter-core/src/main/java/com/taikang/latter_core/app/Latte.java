package com.taikang.latter_core.app;

import android.content.Context;

import java.util.ArrayList;

import okhttp3.Interceptor;

/**
 * Time：2018/12/1
 * Author: gaonz
 * Description:
 */
public class Latte {

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigType.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    //获取application上下文
    public static Context getApplicationContext() {
        return getConfiguration(ConfigType.APPLICATION_CONTEXT);
    }

    //获取api_host
    public static String getApiHost() {
        return (String) getConfiguration(ConfigType.API_HOST);
    }

    //获取interceptors
    @SuppressWarnings("unchecked")
    public static ArrayList<Interceptor> getInterceptors() {
        return (ArrayList<Interceptor>) getConfiguration(ConfigType.INTERCEPTOR);
    }
}
