package com.huihe.base_lib.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.huihe.base_lib.R;
import com.huihe.base_lib.R2;
import com.huihe.base_lib.constants.ArgumentsConfig;
import com.huihe.base_lib.ui.widget.title.CustomerTitle;
import com.huihe.base_lib.utils.LogUtils;

import butterknife.BindView;

public class H5TitleActivity extends BaseTitleActivity {

    @BindView(R2.id.cooperation_web)
    WebView webView;
    @BindView(R2.id.cooperation_web_pb)
    ProgressBar pb;

    public static final String KET_URL = ArgumentsConfig.KEY_URL;
    public static final String KET_TITLE = ArgumentsConfig.KEY_TITLE;
    public static final String KEY_WXPATH = ArgumentsConfig.KEY_WXPATH;
    public static final String KEY_CONTENT = ArgumentsConfig.KEY_CONTENT;
    public static final String KEY_IMGURL = ArgumentsConfig.KEY_IMGURL;

    private CustomerTitle customerTitle;

    private String title;
    private String url;
    private String wxPath;
    private String content;
    private String imgUrl;

    public static void actionStart(Context context, String url) {
        Intent intent = new Intent(context, H5TitleActivity.class);
        intent.putExtra(KET_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle(CustomerTitle customerTitle) {
        Intent intent = getIntent();
        if (intent != null) {
            title = intent.getStringExtra(KET_TITLE);
            wxPath = intent.getStringExtra(KEY_WXPATH);
            content = intent.getStringExtra(KEY_CONTENT);
            imgUrl = intent.getStringExtra(KEY_IMGURL);
            customerTitle.setTitle(title);
            if (useShare()){
                customerTitle.setRightImg(R.mipmap.share_black);
                customerTitle.setImgRightListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //分享
                        showSharePopWindow(url,title,wxPath,content,imgUrl);
                    }
                });
            }
        }
        this.customerTitle = customerTitle;
    }

    protected boolean useShare() {
        return false;
    }

    protected void showSharePopWindow(String url, String title, String wxPath, String content, String imgUrl) {

    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.activity_cooperation;
    }

    @Override
    protected void initData() {
        url = getIntent().getStringExtra(KET_URL);
        if (TextUtils.isEmpty(url)) {
            return;
        }
        // http://api.map.baidu.com/place/detail?uid=cfb1305e8ef132caa1d230f0&output=html&source=placeapi_v2
        //支持Js
        webView.getSettings().setJavaScriptEnabled(true);
        //设置默认编码
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        //设置在WebView内部是否允许访问文件，默认允许访问。
        webView.getSettings().setAllowFileAccess(true);
        //设置WebView是否使用viewport，当该属性被设置为false时，加载页面的宽度总是适应WebView控件宽度；
        //当被设置为true，当前页面包含viewport属性标签，在标签中指定宽度值生效，如果页面不包含viewport标签，
        //无法提供一个宽度值，这个时候该方法将被使用。
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //必须设置setWebviewclient  自定义的继承于webviewclient的类就是用来拦截url处理一些与H5交互的时候的逻辑
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.loadUrl(url);
    }

    //自定义的webviewclient，用来拦截url处理一些与H5交互的时候的逻辑等
    class MyWebViewClient extends WebViewClient {

        //此方法可以使H5在webview中显示，而不是手机浏览器
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("https://") || url.startsWith("http://")) {
                view.loadUrl(url);
                return false;
            } else {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(view.getUrl()));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        }

        //访问url开始时候触发
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (pb != null)
                pb.setVisibility(View.VISIBLE);
        }

        //访问url结束时候触发
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (pb != null)
                pb.setVisibility(View.GONE);
//            customerTitle.setTitle(view.getTitle());
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//            super.onReceivedSslError(view, handler, error);
            handler.proceed();// 接受所有网站的证书
        }
    }

    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            LogUtils.e("web", "title=" + title);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress <= 100) {
                if (pb != null)
                    pb.setProgress(newProgress);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showFinish();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void showFinish() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

}
