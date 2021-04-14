package com.huihe.base_lib.utils.apk;

public interface UpdateDownloadListener {

    void onProgressChanged(long length, int progress, String downloadUrl);
    void onFinished(long completeSize, String downloadUrl);
    void onFailure();
}
