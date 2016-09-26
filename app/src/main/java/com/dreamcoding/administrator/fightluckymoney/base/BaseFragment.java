package com.dreamcoding.administrator.fightluckymoney.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dreamcoding.administrator.fightluckymoney.R;
import com.dreamcoding.administrator.fightluckymoney.interf.UIOperation;

/**
 * Created by Administrator on 2016/9/22 0022 下午 5:31.
 */
public abstract class BaseFragment extends Fragment implements UIOperation {

    protected BaseActivity mActivity;

    /**
     * Fragment显示的界面视图
     */
    protected View mRoot;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 获取管理Fragment的Activity
        mActivity = (BaseActivity) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // 为空时才创建界面，否则会重复创建
        if (mRoot == null) {
            mRoot = Global.inflate(getLayoutRes(), container);
            initView();
            initListener();
            initData();

            /*// 查找一个布局中所有的按钮(Button或ImageButton)并设置点击事件
            Utils.findButtonAndSetListener(mRoot, this);*/

        } else {
            // 避免mRoot重复添加到多个父控件导致出错，这个在高版本的v4包不用写也是可以的，但低版本的v4
            // 包就会有问题如果不写这段代码会报一个异常的
            ViewGroup parent = (ViewGroup) mRoot.getParent();
            if (parent != null) {
                parent.removeView(mRoot);
            }
        }
        return mRoot;
    }

    /**
     * 查找子控件，可省强转
     */
    public <T> T findView(int id) {
        T view = (T) mRoot.findViewById(id);
        return view;
    }

    public void showToast(String text) {
        Global.showToast(text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:    // 点击标题栏的返回按钮
                mActivity.finish();
                break;
            default:
                onClick(v, v.getId());
                break;
        }
    }
}

