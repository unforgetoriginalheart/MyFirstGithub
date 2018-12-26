package com.taikang.latter_core.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.taikang.latter_core.delegates.LatteDelegate;

/**
 * Time：2018/12/25
 * Author: gaonz
 * Description:
 */
public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public void onResume() {
        super.onResume();
        //fragment每次onResume Touch时需要重新获取focus
        final View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                _mActivity.finish();
            } else {
                Toast.makeText(getContext(), "双击退出", Toast.LENGTH_LONG).show();
                TOUCH_TIME = System.currentTimeMillis();
            }
            return true;
        }
        return false;
    }

}
