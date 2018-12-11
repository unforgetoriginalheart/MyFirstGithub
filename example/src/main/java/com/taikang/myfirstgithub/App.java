package com.taikang.myfirstgithub;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.taikang.latter_core.app.Latte;
import com.taikang.latter_ec.icon.EcIcons;
import com.taikang.latter_ec.icon.FontEcModule;

/**
 * Time：2018/12/1
 * Author: gaonz
 * Description:
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1")
                .configure();
    }
}
