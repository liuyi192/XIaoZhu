package com.xiaozhu.common.umeng;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * @说明 友盟管理类
 * @作者 liuyi
 * @时间 2018/5/2 9:34
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class UmengManagement {
    private static UmengManagement instance;

    public static UmengManagement getInstance() {
        if (instance == null) {
            synchronized (UmengManagement.class) {
                if (instance == null) {
                    instance = new UmengManagement();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化
     *
     * @param context
     * @param appKey
     * @param channel
     */
    public void initUmeng(Context context, String appKey, String channel) {
        UMConfigure.init(context, appKey, channel, 1, null);
    }

    /**
     * 初始化分享微信
     *
     * @param appKey    微信KEY
     * @param appSecret 微信Secret
     */
    public void initShareWeiXin(String appKey, String appSecret) {
        PlatformConfig.setWeixin(appKey, appSecret);
    }

    /**
     * 第三方登录
     *
     * @param context
     * @param share_media    分享类型
     * @param umAuthListener 回调监听
     */
    public void login(Activity context, SHARE_MEDIA share_media, UMAuthListener umAuthListener) {
        UMShareAPI umShareAPI = UMShareAPI.get(context);
        umShareAPI.getPlatformInfo(context, share_media, umAuthListener);
    }

    /**
     * 数据统计
     *
     * @param context
     * @param viewName 界面名称
     */
    public void onResume(Context context, String viewName) {
        if (TextUtils.isEmpty(viewName))
            MobclickAgent.onPageStart(viewName);
        MobclickAgent.onResume(context);
    }

    /**
     * 数据统计
     *
     * @param context
     * @param viewName 界面名称
     */
    public void onPause(Context context, String viewName) {
        if (TextUtils.isEmpty(viewName))
            MobclickAgent.onPageEnd(viewName);
        MobclickAgent.onPause(context);
    }

    /**
     * 上传错误信息
     *
     * @param context
     * @param error   错误信息
     */
    public void sendError(Context context, String error) {
        MobclickAgent.reportError(context, error);
    }

    /**
     * 友盟分享
     *
     * @param activity    上下文对象
     * @param share_media 分享类型
     * @param url         外链地址
     * @param title       标题
     * @param content     内容
     * @param resId       图标
     * @return
     */
    public ShareAction umengShare(Activity activity, SHARE_MEDIA share_media, String url, String title, String content, int resId) {
        ShareAction shareAction = null;
        if (!TextUtils.isEmpty(url)) {
            UMWeb umWeb = new UMWeb(url);
            if (!TextUtils.isEmpty(title)) {
                umWeb.setTitle(title);
            }
            if (!TextUtils.isEmpty(content)) {
                umWeb.setDescription(content);
            }
            if (resId != 0) {
                UMImage image = new UMImage(activity, resId);
                umWeb.setThumb(image);
            }
            shareAction = new ShareAction(activity).setPlatform(share_media).withMedia(umWeb);
        }
        return shareAction;
    }
}
