package com.taikang.myfirstgithub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.taikang.latter_core.delegates.LatteDelegate;
import com.taikang.latter_core.net.RestClient;

/**
 * Time：2018/12/10
 * Author: gaonz
 * Description:
 */
public class ExampleDelegate extends LatteDelegate {

    private static final String TAG = "ExampleDelegate";

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
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(response -> Log.d(TAG, response))
                .build()
                .get();
    }
}
