package com.taikang.myfirstgithub;

import com.taikang.latter_core.activitys.ProxyActivity;
import com.taikang.latter_core.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
