package com.dreamcoding.administrator.fightluckymoney.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.dreamcoding.administrator.fightluckymoney.R;
import com.dreamcoding.administrator.fightluckymoney.interf.UIOperation;


/**
 * Created by Administrator on 2016/9/21 0021 下午 11:47.
 */
public abstract class BaseActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //每个Activity中的生命周期的onCreate方法
        MyApplication.activityCreateStatistics(this);

        // 去掉界面标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    }


    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.activityResumeStatistics(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyApplication.activityPauseStatistics(this);
    }


    /**
     * 查找子控件，可省强转
     */
    public <T> T findView(int id) {
        T view = (T) findViewById(id);
        return view;
    }

    public void showToast(String text) {
        Global.showToast(text);
    }


}
