package com.xiaozhu.common.mvp.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * @说明 视图层代理的基类
 * @作者 liuyi
 * @时间 2018/4/12 9:16
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public abstract class AppDelegate implements IDelegate {
    protected final SparseArray<View> mViews = new SparseArray<>();
    protected View rootView;
    protected Bundle bundle;

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getRootLayoutId(), container, false);
        //注解
        ButterKnife.bind(this, rootView);
    }

    protected <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = (T) rootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    @Override
    public void setOnClickListener(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            getView(id).setOnClickListener(listener);
        }
    }

    @Override
    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    protected <T extends View> T getView(int id) {
        return (T) bindView(id);
    }

    protected <T extends Activity> T getActivity() {
        return (T) rootView.getContext();
    }

    @Override
    public void onDestroy() {

    }
}
