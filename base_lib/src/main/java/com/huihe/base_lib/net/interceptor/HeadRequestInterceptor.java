package com.huihe.base_lib.net.interceptor;

import com.huihe.base_lib.utils.manager.LoginHelper;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @desc 所有接口统一添加的参数的拦截器
 */
public class HeadRequestInterceptor implements Interceptor {

    public HeadRequestInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request;
        //定义一个Request 请求
        Request originalRequest = chain.request();
        boolean login = LoginHelper.isLogin() && LoginHelper.getLoginInfo().getUserToken() != null;
        HttpUrl.Builder builder = originalRequest.url().newBuilder();
        if (login) {
            String userToken = LoginHelper.getLoginInfo().getUserToken();
            builder.addQueryParameter("token", userToken);
        }
        builder.addQueryParameter("platform", "android");
        HttpUrl modifiedUrl = builder.build();
        request = originalRequest.newBuilder().url(modifiedUrl).build();

        return chain.proceed(request);
    }
}
