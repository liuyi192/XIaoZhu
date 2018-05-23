package com.xiaozhu.common.download.data;

/**
 * @说明 记录断点信息
 * @作者 liuyi
 * @时间 2018/5/18 9:46
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class Ranges {
    public long[] start;
    public long[] end;

    public Ranges(long[] start, long[] end) {
        this.start = start;
        this.end = end;
    }
}
