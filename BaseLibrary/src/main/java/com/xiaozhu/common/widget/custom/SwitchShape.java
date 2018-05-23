package com.xiaozhu.common.widget.custom;

import android.graphics.drawable.ShapeDrawable;

/**
 * @说明 切换形状
 * @作者 liuyi
 * @时间 2018/5/3 14:17
 * @邮箱 2743569843@qq.com
 * @版权 Copyright(c) 2018 小烛-版权所有
 * @备注
 */
public class SwitchShape {
    private float x = 0, y = 0;
    private ShapeDrawable shape;
    private int color;
    private float width, height;

    public SwitchShape(ShapeDrawable s) {
        shape = s;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public ShapeDrawable getShape() {
        return shape;
    }

    public void setShape(ShapeDrawable shape) {
        this.shape = shape;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setScale(float x) {
        shape.setBounds(0, (int) (height - height * x) / 2, (int) (width * x), (int) (height * x));
    }
}

