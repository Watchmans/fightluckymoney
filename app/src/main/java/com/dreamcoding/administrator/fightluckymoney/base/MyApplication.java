package com.dreamcoding.administrator.fightluckymoney.base;

import android.app.Application;

/**
 * Created by Administrator on 2016/9/22 0022 下午 4:59.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化Global
        Global.init(this);
    }
}
