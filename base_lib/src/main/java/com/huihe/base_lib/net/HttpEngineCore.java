package com.huihe.base_lib.net;

import android.util.Log;

import com.huihe.base_lib.constants.ApiConfig;
import com.huihe.base_lib.constants.AppConfigs;
import com.huihe.base_lib.net.interceptor.ForceCacheInterceptor;
import com.huihe.base_lib.net.interceptor.HeadRequestInterceptor;
import com.huihe.base_lib.ui.activity.BaseApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bruce on 16/4/22.
 */
public class HttpEngineCore {
    public static final String TAG = HttpEngineCore.class.getSimpleName();
    private static Retrofit retrofit;
    private static OkHttpClient client;

    public static Retrofit getRetrofit() {
        initialize();
        return retrofit;
    }

    public static void initialize() {
        if (client == null) {
            synchronized (HttpEngineCore.class) {
                if (client == null) {
                    File cacheFile = new File(BaseApplication.getInstance().getExternalCacheDir(), AppConfigs.project_name);

                    client = new OkHttpClient.Builder()
//                .addNetworkInterceptor(new FilterFastRequestInterceptor(10))
                            .addInterceptor(new HeadRequestInterceptor())
                            .addNetworkInterceptor(new ForceCacheInterceptor(BaseApplication.getInstance()))
                            .cache(new Cache(cacheFile, 1024 * 1024 * 50))
                            .retryOnConnectionFailure(true)
                            .readTimeout(15, TimeUnit.SECONDS)//设置读取超时时间
                            .writeTimeout(15, TimeUnit.SECONDS)//设置写的超时时间
                            .connectTimeout(15, TimeUnit.SECONDS)//设置连接超时时间
                            .build();
                    retrofit = new Retrofit.Builder()
                            .baseUrl(ApiConfig.END_POINT)
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();

                }
            }
        }

    }

    private static Request setupRequestBody(Request oldRequests) {
        String json = getBaseJson(oldRequests);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Log.i(TAG, "requestBody-json:" + json);
        //返回一个新的RequestBody
        return oldRequests.newBuilder()
                .url(oldRequests.url())
                .method(oldRequests.method(), body)
                .build();
    }

    private static String getBaseJson(Request request) {
        JSONObject obj = new JSONObject();
        if (request.body() instanceof FormBody) {
            FormBody body = (FormBody) request.body();
            for (int i = 0; i < body.size(); i++) {
                try {
                    obj.putOpt(body.encodedName(i), body.encodedValue(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj.toString();
    }
}