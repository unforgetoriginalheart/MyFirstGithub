package com.taikang.latter_core.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.taikang.latter_core.R;
import com.taikang.latter_core.R2;
import com.taikang.latter_core.delegates.LatteDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Time：2018/12/25
 * Author: gaonz
 * Description:
 */
public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {

    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES= new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.RED;

    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItem(ItemBuilder builder);

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }
        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItem(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTabBean bean = item.getKey();
            final BottomItemDelegate delegate = item.getValue();
            TAB_BEANS.add(bean);
            ITEM_DELEGATES.add(delegate);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //设置item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView itemIcon = item.findViewById(R.id.icon_bottom_item);
            final AppCompatTextView itemTitle = item.findViewById(R.id.tv_bottom_item);
            final BottomTabBean bean = TAB_BEANS.get(i);
            //初始化数据
            itemIcon.setText(bean.getIcon());
            itemTitle.setText(bean.getTitle());
            if (i == mCurrentDelegate) {
                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }
        }

        final SupportFragment[] delegateArray =  ITEM_DELEGATES.toArray(new SupportFragment[size]);
        loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);
    }

    private void resetColor(int tabSelectIndex) {
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            if (tabSelectIndex != i) {
                final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
                final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
                itemIcon.setTextColor(Color.GRAY);
                final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
                itemTitle.setTextColor(Color.GRAY);
            } else {
                final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
                final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
                itemIcon.setTextColor(mClickedColor);
                final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
                itemTitle.setTextColor(mClickedColor);
            }
        }
    }

    @Override
    public void onClick(View v) {
        final int tabSelectIndex = (int) v.getTag();
        resetColor(tabSelectIndex);
        showHideFragment(ITEM_DELEGATES.get(tabSelectIndex), ITEM_DELEGATES.get(mCurrentDelegate));
        mCurrentDelegate = tabSelectIndex;
    }
}
