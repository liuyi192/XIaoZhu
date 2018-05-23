package com.xiaozhu.common.log.crash;

import java.io.File;

/**
 * @说明 异常日志
 * @作者 liuyi
 * @时间 2018/4/26 10:36
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface OnCrashInfoListener {
    void onUpdateCrashInfo(File file);
}
