package com.xiaozhu.common.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiaozhu.common.R;
import com.xiaozhu.common.widget.loading.LoadDataLayout;

/**
 * @说明 网页基类委托
 * @作者 liuyi
 * @时间 2018/4/27 15:10
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public abstract class BaseWebDelegate extends BaseDelegate {
    protected LoadDataLayout loadView;
    protected WebView webView;

    @Override
    public int getRootLayoutId() {
        return R.layout.common_base_web;
    }

    @Override
    public void initWidget(Context context) {
        loadView = getView(R.id.loadView);
        webView = getView(R.id.webView);
        //网页设置
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setDomStorageEnabled(true);
        GeoClient geo = new GeoClient();
        webView.setWebViewClient(new PTCWebViewClient(context, webView));
        webView.setWebChromeClient(geo);
        String origin = "";
        geo.onGeolocationPermissionsShowPrompt(origin, new GeolocationPermissions.Callback() {
            @Override
            public void invoke(String origin, boolean allow, boolean retain) {

            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    loadView.setStatus(LoadDataLayout.SUCCESS);
                } else {
                    loadView.setStatus(LoadDataLayout.LOADING);
                }
            }
        });
    }

    /**
     * 加载网页
     *
     * @param url
     */
    public void loadUrl(String url) {
        loadView.setStatus(LoadDataLayout.LOADING);
        webView.loadUrl(url);
    }

    private class GeoClient extends WebChromeClient {
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            super.onGeolocationPermissionsShowPrompt(origin, callback);
            callback.invoke(origin, true, false);
        }
    }

    public class PTCWebViewClient extends WebViewClient {
        public PTCWebViewClient(Context con, final WebView web) {
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            loadView.setStatus(LoadDataLayout.ERROR);
        }
    }

}
