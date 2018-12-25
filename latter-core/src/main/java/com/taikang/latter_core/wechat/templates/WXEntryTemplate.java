package com.taikang.latter_core.wechat.templates;

import com.taikang.latter_core.wechat.BaseWXEntryActivity;
import com.taikang.latter_core.wechat.LatteWeChat;

/**
 * 微信返回后的界面，设置成透明的
 */
public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        //取消动画
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
