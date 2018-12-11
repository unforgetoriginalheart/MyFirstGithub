package com.taikang.latter_core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.taikang.latter_core.app.Latte;

/**
 * Time：2018/12/11
 * Author: gaonz
 * Description:
 */
public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Latte.getContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}