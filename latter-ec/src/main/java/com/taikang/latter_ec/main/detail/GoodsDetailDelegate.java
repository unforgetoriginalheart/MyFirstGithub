package com.taikang.latter_ec.main.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.taikang.latter_core.delegates.LatteDelegate;
import com.taikang.latter_ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Time：2019/1/10
 * Author: gaonz
 * Description:
 */
public class GoodsDetailDelegate extends LatteDelegate {

    public static GoodsDetailDelegate create() {
        return new GoodsDetailDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        //创建默认水平的动画
        return new DefaultHorizontalAnimator();
    }
}
