package com.xiaozhu.common.permissions;

import java.util.ArrayList;

/**
 * @说明 权限请求回调
 * @作者 liuyi
 * @时间 2018/4/11 11:55
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface PermissionCallback {
    /**
     * 同意权限
     *
     * @param grantedList
     */
    void onGranted(ArrayList<PermissionEnum> grantedList);

    /**
     * 拒绝权限
     *
     * @param deniedList
     */
    void onDenied(ArrayList<PermissionEnum> deniedList);
}
