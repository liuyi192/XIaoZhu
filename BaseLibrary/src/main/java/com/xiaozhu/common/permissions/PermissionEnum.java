package com.xiaozhu.common.permissions;

import android.Manifest;
import android.support.annotation.NonNull;

/**
 * @说明 权限类
 * @作者 liuyi
 * @时间 2018/4/11 11:52
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public enum PermissionEnum {
    BODY_SENSORS(Manifest.permission.BODY_SENSORS, "传感器"),
    READ_CALENDAR(Manifest.permission.READ_CALENDAR, "日历"),
    WRITE_CALENDAR(Manifest.permission.WRITE_CALENDAR, "日历"),
    READ_CONTACTS(Manifest.permission.READ_CONTACTS, "联系人"),
    WRITE_CONTACTS(Manifest.permission.WRITE_CONTACTS, "联系人"),
    GET_ACCOUNTS(Manifest.permission.GET_ACCOUNTS, "手机账户列表"),
    READ_EXTERNAL_STORAGE(Manifest.permission.READ_EXTERNAL_STORAGE, "内存卡"),
    WRITE_EXTERNAL_STORAGE(Manifest.permission.WRITE_EXTERNAL_STORAGE, "内存卡"),
    ACCESS_FINE_LOCATION(Manifest.permission.ACCESS_FINE_LOCATION, "定位"),
    ACCESS_COARSE_LOCATION(Manifest.permission.ACCESS_COARSE_LOCATION, "定位"),
    RECORD_AUDIO(Manifest.permission.RECORD_AUDIO, "录音"),
    READ_PHONE_STATE(Manifest.permission.READ_PHONE_STATE, "电话"),
    CALL_PHONE(Manifest.permission.CALL_PHONE, "电话"),
    READ_CALL_LOG(Manifest.permission.READ_CALL_LOG, "电话"),
    WRITE_CALL_LOG(Manifest.permission.WRITE_CALL_LOG, "电话"),
    ADD_VOICEMAIL(Manifest.permission.ADD_VOICEMAIL, "语音信箱"),
    USE_SIP(Manifest.permission.USE_SIP, "视频"),
    PROCESS_OUTGOING_CALLS(Manifest.permission.PROCESS_OUTGOING_CALLS, "电话"),
    CAMERA(Manifest.permission.CAMERA, "相机"),
    SEND_SMS(Manifest.permission.SEND_SMS, "发送短信"),
    RECEIVE_SMS(Manifest.permission.RECEIVE_SMS, "接收短信"),
    READ_SMS(Manifest.permission.READ_SMS, "读取短信"),
    RECEIVE_WAP_PUSH(Manifest.permission.RECEIVE_WAP_PUSH, "接收Wap Push"),
    RECEIVE_MMS(Manifest.permission.RECEIVE_MMS, "接收彩信"),
    GROUP_CALENDAR(Manifest.permission_group.CALENDAR, "日历"),
    GROUP_CAMERA(Manifest.permission_group.CAMERA, "相机"),
    GROUP_CONTACTS(Manifest.permission_group.CONTACTS, "联系人"),
    GROUP_LOCATION(Manifest.permission_group.LOCATION, "位置"),
    GROUP_MICROPHONE(Manifest.permission_group.MICROPHONE, "麦克风"),
    GROUP_PHONE(Manifest.permission_group.PHONE, "电话"),
    GROUP_SENSORS(Manifest.permission_group.SENSORS, "传感器"),
    GROUP_SMS(Manifest.permission_group.SMS, "短信"),
    GROUP_STORAGE(Manifest.permission_group.STORAGE, "内存卡");

    private final String permission;
    private String nameCn;

    PermissionEnum(String permission, String nameCn) {
        this.permission = permission;
        this.nameCn = nameCn;
    }

    /**
     * 对请求权限的响应遍历
     *
     * @param value
     * @return
     */
    public static PermissionEnum onResultPermissions(@NonNull String value) {
        for (PermissionEnum permissionEnum : PermissionEnum.values()) {
            if (value.equalsIgnoreCase(permissionEnum.permission)) {
                return permissionEnum;
            }
        }
        return null;
    }

    public String getPermission() {
        return permission;
    }

    public String getNameCn() {
        return nameCn;
    }
}
