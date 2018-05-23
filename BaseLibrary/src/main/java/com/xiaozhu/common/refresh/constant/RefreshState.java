package com.xiaozhu.common.refresh.constant;

/**
 * @说明
 * @作者 liuyi
 * @时间 2018/5/4 10:04
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public enum RefreshState {
    None(0, false, false, false),
    PullDownToRefresh(1, true, false, false), PullUpToLoad(2, true, false, false),
    PullDownCanceled(1, false, false, false), PullUpCanceled(2, false, false, false),
    ReleaseToRefresh(1, true, false, false), ReleaseToLoad(2, true, false, false),
    ReleaseToTwoLevel(1, true, false, false), TwoLevelReleased(1, false, false, false),
    RefreshReleased(1, false, false, false), LoadReleased(2, false, false, false),
    Refreshing(1, false, true, false), Loading(2, false, true, false), TwoLevel(1, false, true, false),
    RefreshFinish(1, false, false, true), LoadFinish(2, false, false, true), TwoLevelFinish(1, false, false, true),;
    public final boolean isHeader;
    public final boolean isFooter;
    public final boolean isDragging;// 正在拖动状态：PullDownToRefresh PullUpToLoad ReleaseToRefresh ReleaseToLoad ReleaseToTwoLevel
    public final boolean isOpening;// 正在刷新状态：Refreshing Loading TwoLevel
    public final boolean isFinishing;//正在完成状态：RefreshFinish LoadFinish TwoLevelFinish

    RefreshState(int role, boolean dragging, boolean opening, boolean finishing) {
        this.isHeader = role == 1;
        this.isFooter = role == 2;
        this.isDragging = dragging;
        this.isOpening = opening;
        this.isFinishing = finishing;
    }
}
