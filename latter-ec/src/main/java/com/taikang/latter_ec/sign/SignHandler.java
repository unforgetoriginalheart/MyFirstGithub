package com.taikang.latter_ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taikang.latter_core.app.AccountManager;
import com.taikang.latter_ec.database.DatabaseManager;
import com.taikang.latter_ec.database.UserProfile;

/**
 * Time：2018/12/18
 * Author: gaonz
 * Description:
 */
public class SignHandler {

    public static void onSignIn(String response, ISignListener iSignListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //注册成功，保存用户状态
        AccountManager.setSignState(true);
        iSignListener.onSignInSuccess();
    }

    public static void onSignUp(String response, ISignListener iSignListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //注册成功，保存用户状态
        AccountManager.setSignState(true);
        iSignListener.onSignUpSuccess();
    }
}
