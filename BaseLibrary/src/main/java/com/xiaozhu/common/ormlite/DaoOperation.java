package com.xiaozhu.common.ormlite;

/**
 * @说明 数据库操作类型
 * @作者 liuyi
 * @时间 2018/4/28 10:26
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface DaoOperation {

    /**
     * 插入
     */
    int INSERT = 1;
    /**
     * 删除
     */
    int DELETE = 2;
    /**
     * 更新
     */
    int UPDATE = 3;
    /**
     * 查询
     */
    int SELECT = 4;
    /**
     * 批量插入
     */
    int INSERT_BATCH = 5;
    /**
     * 批量删除
     */
    int DELETE_BATCH = 6;
    /**
     * 批量更新
     */
    int UPDATE_BATCH = 7;
}
