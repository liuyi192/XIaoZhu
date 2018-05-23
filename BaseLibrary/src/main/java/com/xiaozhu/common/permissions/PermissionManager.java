package com.xiaozhu.common.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @说明 权限管理类
 * @作者 liuyi
 * @时间 2018/4/11 11:57
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class PermissionManager {
    private PermissionCallback mPermissionCallback;
    private ArrayList<PermissionEnum> mPermissions;
    private ArrayList<PermissionEnum> mPermissionsGranted;
    private ArrayList<PermissionEnum> mPermissionsDenied;
    private int mTag = 100;
    private static WeakReference<Context> contextWeakReference;

    private static class PermissionManagerHolder {
        private static final PermissionManager INSTANCE = new PermissionManager();
    }

    private PermissionManager() {
    }

    public static PermissionManager with(Context context) {
        contextWeakReference = new WeakReference<>(context);
        return PermissionManagerHolder.INSTANCE;
    }

    private Context getContext() {
        return contextWeakReference.get();
    }

    /**
     * 处理结果
     *
     * @param requestCode  请求的Code
     * @param permissions  权限
     * @param grantResults 权限结果
     */
    public void handleResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == this.mTag) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    this.mPermissionsGranted.add(PermissionEnum.onResultPermissions(permissions[i]));
                } else {
                    this.mPermissionsDenied.add(PermissionEnum.onResultPermissions(permissions[i]));
                }
            }
            this.showResult();
        }
    }

    /**
     * 权限请求的返回状态区分
     *
     * @param tag
     * @return
     */
    public PermissionManager tag(int tag) {
        this.mTag = tag;
        return this;
    }

    /**
     * 校验权限
     */
    public void checkAsk() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            initArrayList();
            String[] permissionToAsk = permissionToAsk();
            if (permissionToAsk.length == 0) {
                showResult();
            } else {//权限不明
                for (int i = 0; i < permissionToAsk.length; i++) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) getContext(), permissionToAsk[i])) {
                        mPermissionsDenied.add(PermissionEnum.onResultPermissions(permissionToAsk[i]));
                    }
                }
                //权限已经被拒绝
                if (mPermissionsDenied.size() != 0) {
                    showResult();
                } else {//权限没有被拒绝 直接申请
                    ActivityCompat.requestPermissions((Activity) getContext(), permissionToAsk, mTag);
                }
            }
        } else {
            initArrayList();
            mPermissionsGranted.addAll(mPermissions);
            showResult();
        }
    }


    /**
     * 需要校验的权限
     *
     * @param permissions 权限列表
     * @return 当前实例
     */
    public PermissionManager permissions(ArrayList<PermissionEnum> permissions) {
        this.mPermissions = new ArrayList<>();
        this.mPermissions.addAll(permissions);
        return this;
    }


    /**
     * 需要校验的权限
     *
     * @param permissionEnum 程序需要的权限
     * @return 当前实例
     */
    public PermissionManager permission(PermissionEnum permissionEnum) {
        this.mPermissions = new ArrayList<>();
        this.mPermissions.add(permissionEnum);
        return this;
    }


    /**
     * 需要校验的权限
     *
     * @param permissions 程序需要的权限
     * @return 当前实例
     */
    public PermissionManager permission(PermissionEnum... permissions) {
        this.mPermissions = new ArrayList<>();
        Collections.addAll(this.mPermissions, permissions);
        return this;
    }

    /**
     * 权限回调
     *
     * @param callback 回调
     * @return 当前实例
     */
    public PermissionManager callback(PermissionCallback callback) {
        this.mPermissionCallback = callback;
        return this;
    }


    private void initArrayList() {
        this.mPermissionsGranted = new ArrayList<>();
        this.mPermissionsDenied = new ArrayList<>();
    }

    /**
     * 检查是否拥有权限
     *
     * @return 拥有的权限
     */
    @NonNull
    private String[] permissionToAsk() {
        ArrayList<String> permissionToAsk = new ArrayList<>();
        for (PermissionEnum permission : mPermissions) {
            if (!isGranted(permission)) {
                permissionToAsk.add(permission.getPermission());
            } else {
                mPermissionsGranted.add(permission);
            }
        }
        return permissionToAsk.toArray(new String[permissionToAsk.size()]);
    }

    /**
     * 权限是否同意
     *
     * @param permission 权限名称
     * @return {@code true}: 已同意<br>{@code false}: 未同意
     */
    private boolean isGranted(PermissionEnum permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || ContextCompat.checkSelfPermission(getContext(), permission.getPermission()) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 权限是否同意
     *
     * @param context
     * @param permission 权限多个
     * @return {@code true}: 已同意<br>{@code false}: 未同意
     */
    private boolean isGranted(Context context, PermissionEnum... permission) {
        for (PermissionEnum permissionEnum : permission) {
            if (!isGranted(context, permissionEnum)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 显示结果
     */
    private void showResult() {
        if (mPermissionCallback != null) {
            if (mPermissionsDenied.size() > 0) {
                mPermissionCallback.onDenied(mPermissionsDenied);
            } else {
                mPermissionCallback.onGranted(mPermissionsGranted);
            }
        }
    }
}
