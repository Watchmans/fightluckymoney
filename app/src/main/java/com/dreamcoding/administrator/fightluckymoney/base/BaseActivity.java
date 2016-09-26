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
public abstract class BaseActivity extends AppCompatActivity implements UIOperation {

    private TextView tvTitle;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉界面标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutRes());

        /*// android.R.id.content系统的一个父控件，Activity的布局文件会作为子控件添加到该控件中
        View view = findViewById(android.R.id.content);
        // 查找一个布局中所有的按钮(Button或ImageButton)并设置点击事件
        Utils.findButtonAndSetListener(view, this);*/

        //初始话toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        initListener();
        initData();
    }

    /**
     * 设置界面标题
     */
    public void setPageTitle(String title) {
        tvTitle = findView(R.id.tv_title);
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
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
