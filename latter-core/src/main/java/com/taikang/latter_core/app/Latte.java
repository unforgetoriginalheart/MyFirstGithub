package com.taikang.latter_core.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Time：2018/12/1
 * Author: gaonz
 * Description:
 */
public class Latte {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static WeakHashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    //获取application上下文
    public static Context getContext() {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }

    //获取api_host
    public static String getApiHost() {
        return (String) getConfigurations().get(ConfigType.API_HOST.name());
    }
}
