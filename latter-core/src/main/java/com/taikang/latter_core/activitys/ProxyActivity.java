package com.taikang.latter_core.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.taikang.latter_core.R;
import com.taikang.latter_core.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Time：2018/12/9
 * Author: gaonz
 * Description:
 */
public abstract class ProxyActivity extends SupportActivity {

    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        @SuppressLint("RestrictedApi")
        final FrameLayout container = new FrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //activity退出时，程序结束
        System.gc();
        System.runFinalization();
    }
}
