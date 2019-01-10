package com.taikang.latter_core.ui.recycler.color;

import com.google.auto.value.AutoValue;

/**
 * Time：2019/1/9
 * Author: gaonz
 * Description:
 */
@AutoValue
public abstract class RgbValue {

    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }
}
