package com.xiaozhu.common.http.observer;


import com.xiaozhu.common.http.function.HttpResultFunction;
import com.xiaozhu.common.http.function.ServerResultFunction;
import com.xiaozhu.common.http.function.ServerResultObjectFunction;
import com.xiaozhu.common.http.retrofit.HttpResponse;
import com.xiaozhu.common.utils.FileManagerUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @说明 适用Retrofit网络请求Observable(被监听者)
 * @作者 liuyi
 * @时间 2018/4/10 10:34
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class HttpRxObservable<T> {
    private static HttpRxObservable instance;

    public static HttpRxObservable getInstance() {
        if (instance == null) {
            synchronized (HttpRxObservable.class) {
                if (instance == null) {
                    instance = new HttpRxObservable();
                }
            }
        }
        return instance;
    }

    /**
     * 获取被监听者
     * 网络请求Observable构建
     * 网络请求参数
     * 无管理生命周期,容易导致内存溢出
     *
     * @param apiObservable
     * @return
     */
    public Observable getObservable(Observable<HttpResponse<T>> apiObservable) {
        Observable observable = apiObservable.map(new ServerResultFunction())
                .onErrorResumeNext(new HttpResultFunction<HttpResponse<T>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    /**
     * 获取被监听者
     * 网络请求Observable构建
     * 网络请求参数
     * 无管理生命周期,容易导致内存溢出
     *
     * @param apiObservable
     * @return
     */
    public static Observable getObservableObject(Observable<Object> apiObservable) {
        Observable observable = apiObservable.map(new ServerResultObjectFunction())
                .onErrorResumeNext(new HttpResultFunction<HttpResponse>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

}
