package com.dreamcoding.administrator.fightluckymoney.base;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.preference.PreferenceFragment;
import com.dreamcoding.administrator.fightluckymoney.config.QianghongbaoWechatConfig;


/**
 * Created by Administrator on 2016/9/27 0027 上午 12:06.
 * 注意这里是没用用到兼容包的，就是v4包
 */
public  class BasePreferenceFragment extends PreferenceFragment {

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
    }
}
