package com.xiaozhu.dome.activity.http.api;

import com.xiaozhu.common.entity.BaseEntity;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/24 14:36
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class VersionEntity extends BaseEntity {
    private String versionMsg;
    private String imUrl;
    private int versionNum;
    private int isForce;
    private int platType;
    private String versionCode;
    private String versionUrl;

    public String getVersionMsg() {
        return versionMsg;
    }

    public void setVersionMsg(String versionMsg) {
        this.versionMsg = versionMsg;
    }

    public String getImUrl() {
        return imUrl;
    }

    public void setImUrl(String imUrl) {
        this.imUrl = imUrl;
    }

    public int getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

    public int getIsForce() {
        return isForce;
    }

    public void setIsForce(int isForce) {
        this.isForce = isForce;
    }

    public int getPlatType() {
        return platType;
    }

    public void setPlatType(int platType) {
        this.platType = platType;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionUrl() {
        return versionUrl;
    }

    public void setVersionUrl(String versionUrl) {
        this.versionUrl = versionUrl;
    }

    @Override
    public String toString() {
        return "VersionEntity{" +
                "versionMsg='" + versionMsg + '\'' +
                ", imUrl='" + imUrl + '\'' +
                ", versionNum=" + versionNum +
                ", isForce=" + isForce +
                ", platType=" + platType +
                ", versionCode='" + versionCode + '\'' +
                ", versionUrl='" + versionUrl + '\'' +
                '}';
    }
}
