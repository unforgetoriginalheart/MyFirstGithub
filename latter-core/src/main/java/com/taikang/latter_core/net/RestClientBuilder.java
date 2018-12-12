package com.taikang.latter_core.net;

import android.content.Context;

import com.taikang.latter_core.net.callback.IError;
import com.taikang.latter_core.net.callback.IFailure;
import com.taikang.latter_core.net.callback.IRequest;
import com.taikang.latter_core.net.callback.ISuccess;
import com.taikang.latter_core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Time：2018/12/10
 * Author: gaonz
 * Description:
 */
public class RestClientBuilder {

    private String mUrl;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mRequest;
    private ISuccess mSuccess;
    private IError mError;
    private IFailure mFailure;
    private RequestBody mBody;
    private LoaderStyle mLoaderStyle;
    private Context mContext;
    private File mFile;

    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder params(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mRequest = iRequest;
        return this;
    }

    public final RestClientBuilder loader(LoaderStyle loaderStyle, Context context) {
        this.mLoaderStyle = loaderStyle;
        this.mContext = context;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        this.mContext = context;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mSuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mError = iError;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mRequest, mSuccess, mError, mFailure, mBody, mLoaderStyle, mContext, mFile);
    }
}
