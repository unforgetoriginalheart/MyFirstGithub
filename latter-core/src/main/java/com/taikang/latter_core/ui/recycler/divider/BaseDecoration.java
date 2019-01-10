package com.taikang.latter_core.ui.recycler.divider;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Time：2019/1/8
 * Author: gaonz
 * Description:
 */
public class BaseDecoration extends DividerItemDecoration {

    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size) {
        return new BaseDecoration(color, size);
    }
}
