package com.xiaozhu.dome.activity.http;


import android.content.Context;

import com.xiaozhu.common.base.BaseDelegate;
import com.xiaozhu.common.http.exception.ApiException;
import com.xiaozhu.common.http.observer.HttpRxObservable;
import com.xiaozhu.common.http.observer.HttpRxObserver;
import com.xiaozhu.common.widget.loading.LoadDataLayout;
import com.xiaozhu.dome.R;
import com.xiaozhu.dome.activity.http.api.ApiUtils;
import com.xiaozhu.dome.activity.http.api.VersionEntity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/24 14:31
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class HttpDelegate extends BaseDelegate {
    private LoadDataLayout loadingView;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_http;
    }

    @Override
    public void initWidget(Context context) {
        loadingView = getView(R.id.loadingView);
        checkVersion();
    }

    public void checkVersion() {
        Map<String, Object> _map = new HashMap<>();
        _map.put("type", "2");
        _map.put("platform", "2");
        HttpRxObservable.getInstance().getObservable(ApiUtils.getSystemApi().checkVersion(_map)).subscribe(new HttpRxObserver<VersionEntity>() {
            @Override
            protected void onStart(Disposable d) {
                loadingView.setStatus(LoadDataLayout.LOADING);
            }

            @Override
            protected void onError(ApiException e) {
                loadingView.setStatus(LoadDataLayout.ERROR);
            }

            @Override
            protected void onSuccess(VersionEntity response) {
                loadingView.setStatus(LoadDataLayout.SUCCESS);
            }
        });
    }
}
