package com.taikang.latter_core.ui.recycler.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Time：2019/1/7
 * Author: gaonz
 * Description:
 */
public class MultipleViewHolder extends BaseViewHolder {

    private MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
