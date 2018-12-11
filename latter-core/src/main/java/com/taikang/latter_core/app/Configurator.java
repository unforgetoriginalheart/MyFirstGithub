package com.taikang.latter_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * Time：2018/12/1
 * Author: gaonz
 * Description:
 */
public class Configurator {

    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    /**相当于单例模式（线程安全的懒汉模式）**/
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final WeakHashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    //配置完成
    public void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
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

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }


}
