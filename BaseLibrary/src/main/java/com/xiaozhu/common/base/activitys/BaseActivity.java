package com.xiaozhu.common.base.activitys;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.umeng.socialize.UMShareAPI;
import com.xiaozhu.common.base.BaseDelegate;
import com.xiaozhu.common.mvp.presenter.ActivityPresenter;
import com.xiaozhu.common.permissions.PermissionManager;
import com.xiaozhu.common.umeng.UmengManagement;
import com.xiaozhu.common.utils.AppUtils;

/**
 * @说明 Activity公共类
 * @作者 liuyi
 * @时间 2018/4/10 10:34
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public abstract class BaseActivity<T extends BaseDelegate> extends ActivityPresenter {

    @Override
    protected void onPause() {
        super.onPause();
        UmengManagement.getInstance().onPause(this, getClass().getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        UmengManagement.getInstance().onResume(this, getClass().getName());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.with(this).handleResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
