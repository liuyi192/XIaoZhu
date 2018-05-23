package com.xiaozhu.common.download.net;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @说明 网络管理
 * @作者 liuyi
 * @时间 2018/5/18 9:49
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class OkHttpManager {
    private OkHttpClient.Builder builder;

    private OkHttpManager() {
        builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS);
    }

    public static OkHttpManager getInstance() {
        return OkHttpHolder.instance;
    }

    private static class OkHttpHolder {
        private static final OkHttpManager instance = new OkHttpManager();
    }

    /**
     * 下载
     * 异步（根据断点请求）
     *
     * @param url      下载地址
     * @param start    开始位置
     * @param end      结束位置
     * @param callback 回调
     * @return
     */
    public Call initRequest(String url, long start, long end, final Callback callback) {
        Request request = new Request.Builder().url(url).header("Range", "bytes=" + start + "-" + end).build();
        Call call = builder.build().newCall(request);
        call.enqueue(callback);
        return call;
    }

    /**
     * 下载
     * 同步请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public Response initRequest(String url) throws IOException {
        Request request = new Request.Builder().url(url).header("Range", "bytes=0-").build();
        return builder.build().newCall(request).execute();
    }

    /**
     * 下载
     * 文件存在的情况下可判断服务端文件是否已经更改
     *
     * @param url
     * @param lastModify
     * @return
     * @throws IOException
     */
    public Response initRequest(String url, String lastModify) throws IOException {
        Request request = new Request.Builder().url(url).header("Range", "bytes=0-").header("If-Range", lastModify).build();
        return builder.build().newCall(request).execute();
    }

    /**
     * 下载
     * https请求时初始化证书
     *
     * @param certificates
     * @return
     */
    public void setCertificates(InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            builder.sslSocketFactory(sslContext.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
