package com.taikang.latter_core.util.timer;

import java.util.TimerTask;

/**
 * Time：2018/12/13
 * Author: gaonz
 * Description: 计时器
 */
public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener iTimerListener) {
        this.mITimerListener = iTimerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
