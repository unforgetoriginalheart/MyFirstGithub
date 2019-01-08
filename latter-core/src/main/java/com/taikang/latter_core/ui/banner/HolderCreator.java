package com.taikang.latter_core.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Time：2019/1/7
 * Author: gaonz
 * Description:
 */
public class HolderCreator implements CBViewHolderCreator<ImageHolder> {

    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
