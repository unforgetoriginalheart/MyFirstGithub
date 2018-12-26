package com.taikang.latter_ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.taikang.latter_core.app.AccountManager;
import com.taikang.latter_core.app.IUserChecker;
import com.taikang.latter_core.delegates.LatteDelegate;
import com.taikang.latter_core.ui.launcher.ILauncherListener;
import com.taikang.latter_core.ui.launcher.LauncherHolderCreator;
import com.taikang.latter_core.ui.launcher.OnLauncherFinishTag;
import com.taikang.latter_core.util.storage.LattePreference;
import com.taikang.latter_ec.R;

import java.util.ArrayList;

/**
 * Time：2018/12/14
 * Author: gaonz
 * Description: 启动页轮播图界面
 */
public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private ArrayList<Integer> integers = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    private void initBanner() {
        integers.add(R.drawable.launcher01);
        integers.add(R.drawable.launcher02);
        integers.add(R.drawable.launcher03);

        mConvenientBanner
                .setPages(new LauncherHolderCreator(), integers)
                .setPageIndicator(new int[] {R.drawable.shape_dot_normal, R.drawable.shape_dot_select})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        if (position == integers.size() - 1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCH_APP.name(), true);
            //检查用户是否已经登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}
