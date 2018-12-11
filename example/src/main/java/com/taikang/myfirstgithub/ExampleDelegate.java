package com.taikang.myfirstgithub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.taikang.latter_core.app.Latte;
import com.taikang.latter_core.delegates.LatteDelegate;
import com.taikang.latter_core.net.RestClient;
import com.taikang.latter_core.net.callback.IError;
import com.taikang.latter_core.net.callback.IFailure;
import com.taikang.latter_core.net.callback.ISuccess;
import com.taikang.latter_core.ui.LatteLoader;

/**
 * Time：2018/12/10
 * Author: gaonz
 * Description:
 */
public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://news.baidu.com")
                .loader(getContext())
                .build()
                .get();
    }
}
