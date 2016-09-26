package com.dreamcoding.administrator.fightluckymoney.base;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.dreamcoding.administrator.fightluckymoney.config.QianghongbaoWechatConfig;

/**
 * Created by Administrator on 2016/9/27 0027 上午 12:06.
 */
public class BasePreferenceFragment extends PreferenceFragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceManager().setSharedPreferencesName(QianghongbaoWechatConfig.PREFERENCE_NAME);
    }

}
