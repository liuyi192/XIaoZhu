package com.xiaozhu.common.widget.toast;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaozhu.common.R;
import com.xiaozhu.common.app.BaseApplication;

import static java.lang.Integer.MAX_VALUE;

/**
 * @说明 提示对话框
 * @作者 liuyi
 * @时间 2018/4/23 10:33
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class ToastUtils {
    public static final int ICON_TYPE_NOTHING = 0;//不显示任何icon
    public static final int ICON_TYPE_SUCCESS = 2;//显示成功图标
    public static final int ICON_TYPE_FAIL = 3;//显示失败图标
    public static final int ICON_TYPE_INFO = 4;//显示信息图标
    public static final long ONE_SECOND = 1000;//时长
    private static boolean isShowing = false;//toast状态
    private static Toast toast;
    private static CountDownTimer timer;

    /**
     * 显示提示对话框
     *
     * @param msg      提示消息
     * @param duration toast时间 单位毫秒
     * @param type     toast类型
     */
    public static void show(String msg, int duration, int type) {
        if (isShowing()) {
            return;
        }
        toast = getToast(BaseApplication.getInstance().getmContext());
        View contentView = View.inflate(BaseApplication.getInstance().getmContext(), R.layout.common_toast_view, null);
        TextView infoTv = (TextView) contentView.findViewById(R.id.info);
        ImageView img = (ImageView) contentView.findViewById(R.id.img);
        infoTv.setText(msg);
        if (type == ICON_TYPE_SUCCESS) {
            img.setVisibility(View.VISIBLE);
            img.setImageResource(R.mipmap.qmui_icon_notify_done);
        } else if (type == ICON_TYPE_FAIL) {
            img.setVisibility(View.VISIBLE);
            img.setImageResource(R.mipmap.qmui_icon_notify_error);
        } else if (type == ICON_TYPE_INFO) {
            img.setVisibility(View.VISIBLE);
            img.setImageResource(R.mipmap.qmui_icon_notify_info);
        } else {
            img.setVisibility(View.GONE);
        }
        toast.setView(contentView);
        toast.setGravity(Gravity.CENTER, 0, 0);
        timer = getTimer(duration);
        timer.start();
        isShowing = true;
    }

    private static Toast getToast(Context context) {
        if (toast == null) {
            toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_LONG);
        }
        return toast;
    }

    public static boolean isShowing() {
        return isShowing;
    }

    private static CountDownTimer getTimer(int duration) {
        long totalTime;
        if (duration > 0) {
            totalTime = duration;
        } else {
            totalTime = MAX_VALUE;
        }
        if (timer == null) {
            timer = new CountDownTimer(totalTime, totalTime / 2) {
                @Override
                public void onTick(long l) {
                    toast.show();
                }

                @Override
                public void onFinish() {
                    toast.cancel();
                    toast = null;
                    isShowing = false;
                }
            };
        }
        return timer;
    }

    /**
     * 取消toast
     */
    public static void dismissLoad() {
        if (timer != null && toast != null) {
            timer.onFinish();
            timer.cancel();
            toast = null;
            isShowing = false;
        }
    }
}
