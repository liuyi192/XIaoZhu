package com.xiaozhu.common.download.data;

/**
 * @说明 常量
 * @作者 liuyi
 * @时间 2018/5/18 9:46
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class Consts {
    public static final int NONE = 0x1000; //无状态
    public static final int START = 0x1001; //准备下载
    public static final int PROGRESS = 0x1002; //下载中
    public static final int PAUSE = 0x1003; //暂停
    public static final int RESUME = 0x1004; //继续下载
    public static final int CANCEL = 0x1005; //取消
    public static final int RESTART = 0x1006; //重新下载
    public static final int FINISH = 0x1007; //下载完成
    public static final int ERROR = 0x1008; //下载出错
    public static final int WAIT = 0x1009; //等待中
    public static final int DESTROY = 0x1010; //释放资源
}
