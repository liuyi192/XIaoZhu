package com.xiaozhu.common.http.helper;

import com.google.gson.JsonElement;

/**
 * @说明 数据解析helper
 * @作者 liuyi
 * @时间 2018/4/10 10:34
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface ParseHelper {
    Object[] parse(JsonElement jsonElement);
}
