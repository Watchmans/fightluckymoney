package com.dreamcoding.administrator.fightluckymoney.interf;

import android.view.View;

import com.dreamcoding.administrator.fightluckymoney.base.BasePreferenceFragment;

/**
 * Created by Administrator on 2016/9/21 0021 下午 11:48.
 */

public interface UIOperation extends BaseUIOperation,View.OnClickListener{

    /**
     * 按钮的点击事件
     *
     * @param view
     * @param id
     */
    void onClick(View view, int id);
}

