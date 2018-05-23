package com.xiaozhu.dome.activity.http.api;

import com.xiaozhu.common.http.retrofit.RetrofitUtils;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/26 15:08
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class ApiUtils {
    //订单
    private static HttpApi httpApi;

    /**
     * 用户信息
     *
     * @return
     */
    public static HttpApi getSystemApi() {
        if (httpApi == null) {
            httpApi = RetrofitUtils.get().retrofit("http://ainuo-agent.hzbayi.com:83/V1.0.0/").create(HttpApi.class);
        }
        return httpApi;
    }
}
