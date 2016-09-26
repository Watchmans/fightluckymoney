package com.dreamcoding.administrator.fightluckymoney.base;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dreamcoding.administrator.fightluckymoney.R;
import com.dreamcoding.administrator.fightluckymoney.interf.UIOperation;

/**
 * Created by Administrator on 2016/9/26 0026 下午 11:47.
 */
public abstract class BaseSettingActivity extends BaseActivity implements UIOperation {
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        //初始化toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
        }

        if(isShowBack()) {
            if (toolbar != null) {
                toolbar.setNavigationIcon(R.drawable.fanhui);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        }

        initView();
        initListener();
        initData();
    }

    protected boolean isShowBack() {
        return true;
    }

    /**
     * 设置界面标题
     */
    public void setPageTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:    // 点击标题栏的返回按钮
                finish();
                break;
            default:
                onClick(v, v.getId());
                break;
        }
    }
}
