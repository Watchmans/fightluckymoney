package com.dreamcoding.administrator.fightluckymoney.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Administrator on 2016/9/27 0027 上午 12:37.
 */
public class PackageUtil {
    /**
     * 获取版本信息
     * @param context
     * @return
     */
    public static String getVersionName(Context context){

        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName
                    (), 0);
            return  info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "0";
    }

}
