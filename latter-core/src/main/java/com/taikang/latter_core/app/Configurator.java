package com.taikang.latter_core.app;

import android.app.Activity;
import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Time：2018/12/1
 * Author: gaonz
 * Description:
 */
public class Configurator {

    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    private static final Handler HANDLER = new Handler();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY, false);
        LATTE_CONFIGS.put(ConfigType.HANDLER, HANDLER);
    }

    /**相当于单例模式（线程安全的懒汉模式）**/
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    //配置完成
    public void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY, true);
    }

    //初始化图标
    private void initIcons() {
        if (ICONS.size() > 0) {
            Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    //插入图标
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    //设置拦截器
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

//    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
//        INTERCEPTORS.addAll(interceptors);
//        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
//        return this;
//    }

    public final Configurator withWechatId(String appId) {
        LATTE_CONFIGS.put(ConfigType.WECHAT_APP_ID, appId);
        return this;
    }

    public final Configurator withWechatSecret(String appSecret) {
        LATTE_CONFIGS.put(ConfigType.WECHAT_APP_SECRET, appSecret);
        return this;
    }

    public final void withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigType.ACTIVITY, activity);
    }

    private void checkConfiguration() {
        boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not really, call configure");
        }
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST, host);
        return this;
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }
}
