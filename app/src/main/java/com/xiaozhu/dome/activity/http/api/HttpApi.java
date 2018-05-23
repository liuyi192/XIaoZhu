package com.xiaozhu.dome.activity.http.api;


import com.xiaozhu.common.http.retrofit.HttpResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/4/24 14:34
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface HttpApi {
    /**
     * 版本更新
     *
     * @param map
     * @return
     */
    @POST("system/version")
    Observable<HttpResponse<VersionEntity>> checkVersion(@QueryMap Map<String, Object> map);
}
