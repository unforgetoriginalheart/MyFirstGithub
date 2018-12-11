package com.taikang.latter_core.net;

import android.content.Context;

import com.taikang.latter_core.app.Latte;
import com.taikang.latter_core.net.callback.IError;
import com.taikang.latter_core.net.callback.IFailure;
import com.taikang.latter_core.net.callback.IRequest;
import com.taikang.latter_core.net.callback.ISuccess;
import com.taikang.latter_core.net.callback.RequestCallbacks;
import com.taikang.latter_core.ui.LatteLoader;
import com.taikang.latter_core.ui.LoaderCreator;
import com.taikang.latter_core.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Time：2018/12/10
 * Author: gaonz
 * Description:
 */
public class RestClient {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody BODY;
    private final LoaderStyle LOADERSTYLE;
    private final Context CONTEXT;


    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IError error,
                      IFailure failure,
                      RequestBody body,
                      LoaderStyle loaderStyle,
                      Context context) {
        URL = url;
        PARAMS.putAll(params);
        REQUEST = request;
        SUCCESS = success;
        ERROR = error;
        FAILURE = failure;
        BODY = body;
        LOADERSTYLE = loaderStyle;
        CONTEXT = context;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        if (LOADERSTYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADERSTYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallbacks());
        }
    }

    private Callback<String> getRequestCallbacks() {
        return new RequestCallbacks(REQUEST, SUCCESS, ERROR, FAILURE, LOADERSTYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
