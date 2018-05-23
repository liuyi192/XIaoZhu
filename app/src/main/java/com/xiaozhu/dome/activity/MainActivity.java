package com.xiaozhu.dome.activity;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.xiaozhu.common.base.activitys.BaseActivity;
import com.xiaozhu.common.download.DownloadUtils;
import com.xiaozhu.common.download.callback.SimpleDownloadCallback;
import com.xiaozhu.common.download.manger.FileUtils;
import com.xiaozhu.common.eventBus.EventBusUtils;
import com.xiaozhu.common.permissions.PermissionCallback;
import com.xiaozhu.common.permissions.PermissionEnum;
import com.xiaozhu.common.permissions.PermissionManager;
import com.xiaozhu.common.utils.FileManagerUtils;
import com.xiaozhu.common.utils.LogUtils;
import com.xiaozhu.common.widget.dialog.CommonDialog;
import com.xiaozhu.common.widget.dialog.DialogViewHolder;
import com.xiaozhu.common.widget.toast.ToastUtils;
import com.xiaozhu.dome.R;
import com.xiaozhu.dome.activity.banner.BannerActivity;
import com.xiaozhu.dome.activity.expandable.ExpandableActivity;
import com.xiaozhu.dome.activity.fillet.FilletActivity;
import com.xiaozhu.dome.activity.http.HttpActivity;
import com.xiaozhu.dome.activity.indicator.IndicatorActivity;
import com.xiaozhu.dome.activity.list.ListDataActivity;
import com.xiaozhu.dome.activity.loading.LoadingActivity;
import com.xiaozhu.dome.activity.log.LogActivity;
import com.xiaozhu.dome.activity.progress.ProgressActivity;
import com.xiaozhu.dome.activity.refresh.RefreshActivity;
import com.xiaozhu.dome.activity.refresh.RefreshListActivity;
import com.xiaozhu.dome.activity.title.TitleBarActivity;
import com.xiaozhu.dome.activity.web.WebViewActivity;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private CommonDialog commonDialog;

    @Override
    public Class getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    public void bindEvenListener() {
        viewDelegate.setOnClickListener(this,
                R.id.tvTitle,
                R.id.btnBanner,
                R.id.btnFillet,
                R.id.btnProgress,
                R.id.btnLoading,
                R.id.btnIndicator,
                R.id.btnHttp,
                R.id.btnLog,
                R.id.btnWeb,
                R.id.btnTitle,
                R.id.btnRefresh,
                R.id.btnExpand,
                R.id.btnRefreshList,
                R.id.btnDownload
        );
    }

    @Override
    public void business() {
        checkPermissions();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvTitle:
                startActivity(new Intent(this, ListDataActivity.class));
                break;
            case R.id.btnBanner:
                startActivity(new Intent(this, BannerActivity.class));
                break;
            case R.id.btnFillet:
                startActivity(new Intent(this, FilletActivity.class));
                break;
            case R.id.btnProgress:
                startActivity(new Intent(this, ProgressActivity.class));
                break;
            case R.id.btnLoading:
                startActivity(new Intent(this, LoadingActivity.class));
                break;
            case R.id.btnIndicator:
                startActivity(new Intent(this, IndicatorActivity.class));
                break;
            case R.id.btnHttp:
                startActivity(new Intent(this, HttpActivity.class));
                break;
            case R.id.btnLog:
                startActivity(new Intent(this, LogActivity.class));
                break;
            case R.id.btnWeb:
                startActivity(new Intent(this, WebViewActivity.class));
                break;
            case R.id.btnTitle:
                startActivity(new Intent(this, TitleBarActivity.class));
                break;
            case R.id.btnExpand:
                startActivity(new Intent(this, ExpandableActivity.class));
                break;
            case R.id.btnRefresh:
                startActivity(new Intent(this, RefreshActivity.class));
                break;
            case R.id.btnRefreshList:
                startActivity(new Intent(this, RefreshListActivity.class));
                break;
            case R.id.btnDownload:
                //downloadFile("http://1.199.93.153/imtt.dd.qq.com/16891/5FE88135737E977CCCE1A4DAC9FAFFCB.apk");
                showShare();
                break;
        }
    }

    public void showShare() {
        commonDialog = new CommonDialog(this, R.layout.common_share_dialog) {
            @Override
            public void convert(DialogViewHolder holder) {
                holder.setOnClick(R.id.share_wechat, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        commonDialog.dismiss();
                    }
                });
                holder.setOnClick(R.id.share_circle_friends, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        commonDialog.dismiss();
                    }
                });
                holder.setOnClick(R.id.cancel, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        commonDialog.dismiss();
                    }
                });
            }
        }.fromBottom().showDialog().setCanceledOnTouchOutside(false);
    }


    public void downloadFile(String url) {
        DownloadUtils.init(this)
                .path(FileManagerUtils.getInstance().getDownloadFolder())
                .name(FileUtils.getSuffixName(url))
                .url(url)
                .childTaskCount(3)
                .build()
                .start(new SimpleDownloadCallback() {
                    @Override
                    public void onStart(long currentSize, long totalSize, float progress) {
                        LogUtils.i("开始下载       currentSize:" + currentSize + "  totalSize:" + totalSize + "   progress:" + progress);
                    }

                    @Override
                    public void onProgress(long currentSize, long totalSize, float progress) {
                        LogUtils.i("下载中      currentSize:" + currentSize + "  totalSize:" + totalSize + "   progress:" + progress);
                    }

                    @Override
                    public void onFinish(File file) {
                        LogUtils.i("下载完成");
                    }

                    @Override
                    public void onWait() {

                    }
                });
    }


    @Override
    protected void onDestroy() {
        EventBusUtils.getInstance().sendEventBus(1002);
        super.onDestroy();
    }

    public void checkPermissions() {
        PermissionManager.with(this).tag(1000)
                .permission(PermissionEnum.READ_EXTERNAL_STORAGE, PermissionEnum.WRITE_EXTERNAL_STORAGE)
                .callback(new PermissionCallback() {
                    @Override
                    public void onGranted(ArrayList<PermissionEnum> grantedList) {
                        ToastUtils.show("权限被允许", 3000, ToastUtils.ICON_TYPE_SUCCESS);
                    }

                    @Override
                    public void onDenied(ArrayList<PermissionEnum> deniedList) {
                        ToastUtils.show("权限被拒绝", 3000, ToastUtils.ICON_TYPE_FAIL);
                    }
                }).checkAsk();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.with(this).handleResult(requestCode, permissions, grantResults);
    }
}
