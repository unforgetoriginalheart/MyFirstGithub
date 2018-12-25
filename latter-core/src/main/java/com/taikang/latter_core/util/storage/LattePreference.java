package com.taikang.latter_core.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taikang.latter_core.app.Latte;

/**
 * Time：2018/12/13
 * Author: gaonz
 * Description: Perference 工具类
 */
public final class LattePreference {

    /**
     * 提示：
     * Activity.getPreference(int mode) 生成Activity名.xml 用于activity内部存储
     * PreferenceManager.getDefaultSharedPreferences(Context) 生成 包名_preferences.xml
     * Context.getSharePreferences(String name, int mode) 生成name.xml
     */
    private static final SharedPreferences PREFERENCES =
            PreferenceManager.getDefaultSharedPreferences(Latte.getApplicationContext());
    private static final String APP_PREFERENCES_KEY = "profile";

    private static SharedPreferences getAppPreferences() {
        return PREFERENCES;
    }

    public static void setAppProfile(String val) {
        getAppPreferences()
                .edit()
                .putString(APP_PREFERENCES_KEY, val)
                .apply();
    }

    public static String getAppProfile() {
        return getAppPreferences().getString(APP_PREFERENCES_KEY, null);
    }

    public static JSONObject getAppProfileJson() {
        String profile = getAppProfile();
        return JSON.parseObject(profile);
    }

    public static void removeAppProfile() {
        getAppPreferences()
                .edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }

    public static void clearAppPreferences() {
        getAppPreferences()
                .edit()
                .clear()
                .apply();
    }

    public static void setAppFlag(String key, boolean flag) {
        getAppPreferences()
                .edit()
                .putBoolean(key, flag)
                .apply();
    }

    public static boolean getAppFlag(String key) {
        return getAppPreferences()
                .getBoolean(key, false);
    }
}
