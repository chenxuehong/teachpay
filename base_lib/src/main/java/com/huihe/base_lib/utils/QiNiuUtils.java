package com.huihe.base_lib.utils;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.huihe.base_lib.constants.AppConfigs;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.util.Date;
import java.util.List;

public class QiNiuUtils {

    private static final int MSG_SUCCESS = 100;
    private static final int MSG_FAIL = 101;
    private static final String TAG = "QiNiuUtils";
    private OnUploadListener listener;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_SUCCESS) {
                String url = (String) msg.obj;
                if (listener != null) {
                    listener.onUploadFinish(url);
                }
            } else if (msg.what == MSG_FAIL) {
                String data = (String) msg.obj;
                String[] split = data.split(":");
                int code = Integer.valueOf(split[0]);
                String error = split[1];
                if (listener != null) {
                    listener.onUploadFail(code, error);
                }
            }
        }
    };

    public final static String DOMAIN = "http://img.huihejituan.com/";
    public final static String bucketname = "qmore";

    /**
     * 上传图片或视频到七牛云
     *
     * @param filePath       要上传的文件
     * @param fileServerName 在服务器的文件名
     */
    public void uploadPic(final String filePath, final String fileServerName, final OnUploadListener listener) {

        LogUtils.i(TAG, "filePath ========= " + filePath);
        LogUtils.i(TAG, "fileServerName ========= " + fileServerName);
        LogUtils.i(TAG, "DOMAIN + filePath ========= " + (DOMAIN + filePath));
        LogUtils.i(TAG, "DOMAIN + fileServerName ========= " + (DOMAIN + fileServerName));
        this.listener = listener;
        if (TextUtils.isEmpty(filePath)){
            if (listener != null) {
                listener.onUploadFinish(filePath);
            }
            return;
        }

        if (filePath.contains("http")) {
            Message msg = Message.obtain();
            msg.what = MSG_SUCCESS;
            msg.obj = filePath;
            handler.removeCallbacks(null);
            handler.sendMessage(msg);
            return;
        }
        ///////////////////////指定上传的Zone的信息//////////////////
        //第一种方式: 指定具体的要上传的zone
        //注：该具体指定的方式和以下自动识别的方式选择其一即可
        //要上传的空间(bucket)的存储区域为华东时
//         Zone z = Zone.zone0();
        //要上传的空间(bucket)的存储区域为华北时
        // Zone z = Zone.zone1();
        //要上传的空间(bucket)的存储区域为华南时
        // Zone z = Zone.zone2();

        //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
        Zone z = Zone.autoZone();
        Configuration c = new Configuration(z);
        //创建上传对象
        final UploadManager uploadManager = new UploadManager(c);

        //密钥配置
        Auth auth = Auth.create(AppConfigs.QiNiu.QINIU_AK, AppConfigs.QiNiu.QINIU_SK);
        /**
         * 生成token
         * uoloadToken()方法的参数是 要上传到的空间(bucket)
         */
        final String token = auth.uploadToken(bucketname);

        /**
         * 调用put方法上传
         * 第一个参数 data：可以是字符串，是要上传图片的路径
         *                可以是File对象，是要上传的文件
         *                可以是byte[]数组，要是上传的数据
         * 第二个参数 key：字符串，是图片在服务器上的名称，要具有唯一性，可以用UUID
         * 第三个参数 token：根据开发者的 AK和SK 生成的token，这个token 应该在后端提供一个接口，然后android代码中发一个get请求获得这个tocken，但这里为了演示，直接写在本地了.
         * 第四个参数：UpCompletionHandler的实例，有个回调方法
         * 第五个参数：可先参数
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 文件外链地址
                    StringBuffer fileUrl = new StringBuffer(DOMAIN);
                    Response response = uploadManager.put(filePath, fileServerName, token);
                    if (response.isOK()) {
                        LogUtils.i("QiNiuUtils", "response -> " + response);
                        DefaultPutRet putRet = JsonUtil.fromJson(response.bodyString(), DefaultPutRet.class);
                        Message msg = Message.obtain();
                        msg.what = MSG_SUCCESS;
                        msg.obj = fileUrl.append(putRet.key).toString();
                        handler.removeCallbacks(null);
                        handler.sendMessage(msg);
                    } else {
                        LogUtils.i("QiNiuUtils", "error -> " + response.error);
                        Message msg = Message.obtain();
                        msg.what = MSG_FAIL;
                        msg.obj = response.statusCode + ":" + response.error;
                        handler.removeCallbacks(null);
                        handler.sendMessage(msg);
                    }
                } catch (QiniuException e) {
                    e.printStackTrace();
                    LogUtils.i("QiNiuUtils", "QiniuException -> " + e.fillInStackTrace());
                    Message msg = Message.obtain();
                    msg.what = MSG_FAIL;
                    msg.obj = -1 + ":" + e.toString();
                    handler.removeCallbacks(null);
                    handler.sendMessage(msg);
                }
            }
        }).start();

    }

    public static void loadMutiPic(final int curPicIndex, final List<String> picList, final OnUpMutiloadListener loadPicListener) {
        if (picList == null || curPicIndex >= picList.size()) {
            if (loadPicListener != null)
                loadPicListener.loadFinish();
            return;
        }
        String path = picList.get(curPicIndex);
        if (path.contains("http")) {
            if (loadPicListener != null)
                loadPicListener.loadPic(curPicIndex, path);
            loadMutiPic(curPicIndex + 1, picList, loadPicListener);
            return;
        }
        new QiNiuUtils().uploadPic(path, Long.valueOf((new Date()).getTime()) + ".jpg", new QiNiuUtils.OnUploadListener() {


            @Override
            public void onUploadFinish(String picUrl) {
                if (loadPicListener != null)
                    loadPicListener.loadPic(curPicIndex, picUrl);
                loadMutiPic(curPicIndex + 1, picList, loadPicListener);
            }

            @Override
            public void onUploadFail(int code, String error) {
                if (loadPicListener != null) {
                    loadPicListener.loadError(curPicIndex);
                }
                loadMutiPic(curPicIndex + 1, picList, loadPicListener);
            }
        });
    }

    public static void upLoadMutiVideo(final int curPicIndex, final List<String> videoList, final OnUpMutiloadListener loadVideoListener) {
        if (videoList == null || curPicIndex >= videoList.size()) {
            if (loadVideoListener != null)
                loadVideoListener.loadFinish();
            return;
        }

        String path = videoList.get(curPicIndex);
        if (path.contains("http")) {
            if (loadVideoListener != null)
                loadVideoListener.loadPic(curPicIndex, path);
            loadMutiPic(curPicIndex + 1, videoList, loadVideoListener);
            return;
        }

        new QiNiuUtils().uploadPic(path, Long.valueOf((new Date()).getTime()) + ".mp4", new QiNiuUtils.OnUploadListener() {


            @Override
            public void onUploadFinish(String picUrl) {
                if (loadVideoListener != null)
                    loadVideoListener.loadPic(curPicIndex, picUrl);
                loadMutiPic(curPicIndex + 1, videoList, loadVideoListener);
            }

            @Override
            public void onUploadFail(int code, String error) {
                if (loadVideoListener != null) {
                    loadVideoListener.loadError(curPicIndex);
                }
                loadMutiPic(curPicIndex + 1, videoList, loadVideoListener);
            }
        });
    }

    public interface OnUpMutiloadListener {
        void loadFinish();

        void loadPic(int position, String path);

        void loadError(int position);
    }

    public interface OnUploadListener {

        void onUploadFinish(String picUrl);

        void onUploadFail(int code, String error);
    }
}
