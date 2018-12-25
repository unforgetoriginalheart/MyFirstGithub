package com.taikang.latter_ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.taikang.latter_core.delegates.LatteDelegate;
import com.taikang.latter_core.net.RestClient;
import com.taikang.latter_core.net.callback.ISuccess;
import com.taikang.latter_core.util.log.LatteLogger;
import com.taikang.latter_ec.R;
import com.taikang.latter_ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Time：2018/12/16
 * Author: gaonz
 * Description:
 */
public class SignUpDelegate extends LatteDelegate {

    @BindView(R2.id.edit_register_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_register_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_register_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_register_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_register_re_password)
    TextInputEditText mRePassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_register)
    void onClickSignUp() {
        if (checkForm()) {
            RestClient.builder()
                    .url("")
                    .params("", "")
                    .params("", "")
                    .params("", "")
                    .params("", "")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignUp(response, mISignListener);
                        }
                    })
                    . build()
                    .post();
        }
    }

    @OnClick(R2.id.tv_register_please)
    void onClickLink() {
        start(new SignInDelegate());
    }

    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位号码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !rePassword.equals(password)) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
