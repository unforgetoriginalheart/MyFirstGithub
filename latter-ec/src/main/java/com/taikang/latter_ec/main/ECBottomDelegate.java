package com.taikang.latter_ec.main;

import android.graphics.Color;

import com.taikang.latter_core.delegates.bottom.BaseBottomDelegate;
import com.taikang.latter_core.delegates.bottom.BottomItemDelegate;
import com.taikang.latter_core.delegates.bottom.BottomTabBean;
import com.taikang.latter_core.delegates.bottom.ItemBuilder;
import com.taikang.latter_ec.main.sort.SortDelegate;
import com.taikang.latter_ec.main.index.IndexDelegate;

import java.util.LinkedHashMap;

/**
 * Time：2018/12/26
 * Author: gaonz
 * Description:
 */
public class ECBottomDelegate extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItem(ItemBuilder builder) {
        LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
