package com.taikang.latter_ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.taikang.latter_core.delegates.bottom.BottomItemDelegate;
import com.taikang.latter_ec.R;

/**
 * Time：2018/12/26
 * Author: gaonz
 * Description:
 */
public class IndexDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
