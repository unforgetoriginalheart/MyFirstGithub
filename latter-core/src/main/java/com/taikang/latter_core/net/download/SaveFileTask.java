package com.taikang.latter_core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.taikang.latter_core.app.Latte;
import com.taikang.latter_core.net.callback.IRequest;
import com.taikang.latter_core.net.callback.ISuccess;
import com.taikang.latter_core.util.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Time：2018/12/12
 * Author: gaonz
 * Description:
 */
public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest request, ISuccess success) {
        REQUEST = request;
        SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody responseBody = (ResponseBody) params[2];
        final String name = (String) params[3];
        final InputStream is = responseBody.byteStream();
        if (TextUtils.isEmpty(downloadDir)) {
            downloadDir = "down_loads";
        }
        if(TextUtils.isEmpty(extension)) {
            extension = "";
        }
        if (TextUtils.isEmpty(name)) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        //安装不严谨（没有考虑兼容问题）
        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        if ("apk".equals(FileUtil.getExtension(file.getPath()))) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }
    }
}
