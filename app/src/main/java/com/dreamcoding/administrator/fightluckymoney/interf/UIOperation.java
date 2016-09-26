package com.dreamcoding.administrator.fightluckymoney.interf;

import android.view.View;

/**
 * Created by Administrator on 2016/9/21 0021 下午 11:48.
 */

public interface UIOperation extends View.OnClickListener {

    /**
     * 获取activity或者Fragment的布局文件
     */
    int getLayoutRes();

    /**
     * 查找子控件
     */
    void initView();

    /**
     * 初始化控件的监听器
     */
    void initListener();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 按钮的点击事件
     *
     * @param view
     * @param id
     */
    void onClick(View view, int id);
}

