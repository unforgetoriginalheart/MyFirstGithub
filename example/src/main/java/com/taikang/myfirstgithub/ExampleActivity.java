package com.taikang.myfirstgithub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.taikang.latter_core.activitys.ProxyActivity;
import com.taikang.latter_core.app.Latte;
import com.taikang.latter_core.delegates.LatteDelegate;
import com.taikang.latter_core.ui.launcher.ILauncherListener;
import com.taikang.latter_core.ui.launcher.OnLauncherFinishTag;
import com.taikang.latter_ec.launcher.LauncherDelegate;
import com.taikang.latter_ec.main.ECBottomDelegate;
import com.taikang.latter_ec.sign.ISignListener;
import com.taikang.latter_ec.sign.SignInDelegate;

public class ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //绑定当前的activity
        Latte.getConfigurator().withActivity(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        //登录成功的回调
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        //注册成功的回调
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                startWithPop(new ECBottomDelegate());
                break;
            case NOT_SIGNED:
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
