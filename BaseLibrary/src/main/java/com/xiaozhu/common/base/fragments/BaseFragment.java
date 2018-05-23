package com.xiaozhu.common.base.fragments;

import com.xiaozhu.common.base.BaseDelegate;
import com.xiaozhu.common.mvp.presenter.FragmentPresenter;
import com.xiaozhu.common.umeng.UmengManagement;

/**
 * @说明 Fragment基类工具
 * @作者 liuyi
 * @时间 2018/4/27 10:19
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public abstract class BaseFragment<T extends BaseDelegate> extends FragmentPresenter {

    @Override
    public void onPause() {
        super.onPause();
        UmengManagement.getInstance().onPause(getActivity(), getClass().getName());
    }

    @Override
    public void onResume() {
        super.onResume();
        UmengManagement.getInstance().onResume(getActivity(), getClass().getName());
    }
}
