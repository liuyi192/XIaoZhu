package com.xiaozhu.dome.app;

import com.xiaozhu.common.app.BaseApplication;
import com.xiaozhu.common.widget.loading.LoadDataLayout;
import com.xiaozhu.dome.BuildConfig;
import com.xiaozhu.dome.R;

/**
 * @说明 例子
 * @作者 liuyi
 * @时间 2018/4/24 14:21
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class DomeApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initLoading();

        registerTables();
    }

    private void registerTables() {
        // databaseManager.getHelper().registerTable();
    }

    private void initLoading() {
        LoadDataLayout.getBuilder()
                .setLoadingText(getString(R.string.loading))
                .setLoadingTextSize(16)
                .setLoadingTextColor(R.color.colorPrimary)
                //.setLoadingViewLayoutId(R.layout.custom_loading_view) //如果设置了自定义loading视图,上面三个方法失效
                .setEmptyImgId(R.mipmap.ic_empty)
                .setErrorImgId(R.mipmap.ic_error)
                .setNoNetWorkImgId(R.mipmap.ic_no_network)
                .setEmptyImageVisible(true)
                .setErrorImageVisible(true)
                .setNoNetWorkImageVisible(true)
                .setEmptyText(getString(R.string.custom_empty_text))
                .setErrorText(getString(R.string.custom_error_text))
                .setNoNetWorkText(getString(R.string.custom_nonetwork_text))
                .setAllTipTextSize(16)
                .setAllTipTextColor(R.color.load_text_color_child)
                .setAllPageBackgroundColor(R.color.window_bg)
                .setReloadBtnText(getString(R.string.custom_reloadBtn_text))
                .setReloadBtnTextSize(16)
                .setReloadBtnTextColor(R.color.colorPrimary)
                .setReloadBtnBackgroundResource(R.drawable.selector_btn_normal)
                .setReloadBtnVisible(true)
                .setReloadClickArea(LoadDataLayout.FULL);
    }
}
