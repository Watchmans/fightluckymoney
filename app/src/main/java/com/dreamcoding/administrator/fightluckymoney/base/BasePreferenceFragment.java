package com.dreamcoding.administrator.fightluckymoney.base;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.preference.PreferenceFragment;
import android.util.Log;
import android.view.View;
import com.dreamcoding.administrator.fightluckymoney.R;
import com.dreamcoding.administrator.fightluckymoney.config.QianghongbaoWechatConfig;
import com.dreamcoding.administrator.fightluckymoney.interf.UIOperation;

/**
 * Created by Administrator on 2016/9/27 0027 上午 12:06.
 * 注意这里是没用用到兼容包的，就是v4包
 */
public abstract class BasePreferenceFragment extends PreferenceFragment implements UIOperation{

    protected Activity mActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 获取管理Fragment的Activity
        mActivity = (BaseSettingActivity) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceManager().setSharedPreferencesName(QianghongbaoWechatConfig.PREFERENCE_NAME);
        addPreferencesFromResource(getLayoutRes());

        initView();
        initListener();
        initData();
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
