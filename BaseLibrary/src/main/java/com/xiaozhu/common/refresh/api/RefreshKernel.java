package com.xiaozhu.common.refresh.api;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;

import com.xiaozhu.common.refresh.constant.RefreshState;

/**
 * @说明 刷新布局核心功能接口 为功能复杂的 Header 或者 Footer 开放的接口
 * @作者 liuyi
 * @时间 2018/5/4 10:02
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public interface RefreshKernel {
    @NonNull
    RefreshLayout getRefreshLayout();

    @NonNull
    RefreshContent getRefreshContent();

    RefreshKernel setState(@NonNull RefreshState state);


    /**
     * 开始执行二极刷新
     *
     * @param open 是否展开
     * @return RefreshKernel
     */
    RefreshKernel startTwoLevel(boolean open);

    /**
     * 结束关闭二极刷新
     *
     * @return RefreshKernel
     */
    RefreshKernel finishTwoLevel();

    /**
     * 移动视图到指定位置
     * moveSpinner 的取名来自 谷歌官方的 {@link android.support.v4.widget.SwipeRefreshLayout}
     *
     * @param spinner    位置 (px)
     * @param isDragging true 手指正在拖动 false 回弹动画执行
     * @return RefreshKernel
     */
    RefreshKernel moveSpinner(int spinner, boolean isDragging);

    /**
     * 执行动画使视图位移到指定的 位置
     * moveSpinner 的取名来自 谷歌官方的 {@link android.support.v4.widget.SwipeRefreshLayout}
     *
     * @param endSpinner 指定的结束位置 (px)
     * @return ValueAnimator 如果没有执行动画 null
     */
    ValueAnimator animSpinner(int endSpinner);

    /**
     * 指定在下拉时候为 Header 或 Footer 绘制背景
     *
     * @param backgroundColor 背景颜色
     * @return RefreshKernel
     */
    RefreshKernel requestDrawBackgroundFor(RefreshInternal internal, int backgroundColor);

    /**
     * 请求事件
     *
     * @param request 请求
     * @return RefreshKernel
     */
    RefreshKernel requestNeedTouchEventFor(@NonNull RefreshInternal internal, boolean request);

    /**
     * 请求设置默认内容滚动设置
     *
     * @param translation 移动
     * @return RefreshKernel
     */
    RefreshKernel requestDefaultTranslationContentFor(@NonNull RefreshInternal internal, boolean translation);

    /**
     * 请求重新测量 headerHeight 或 footerHeight , 要求 height 高度为 WRAP_CONTENT
     *
     * @return RefreshKernel
     */
    RefreshKernel requestRemeasureHeightFor(@NonNull RefreshInternal internal);

    /**
     * 设置二楼回弹时长
     *
     * @param duration 二楼回弹时长
     * @return RefreshKernel
     */
    RefreshKernel requestFloorDuration(int duration);
}
