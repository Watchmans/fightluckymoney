package com.dreamcoding.administrator.fightluckymoney.base;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dreamcoding.administrator.fightluckymoney.R;
import com.dreamcoding.administrator.fightluckymoney.ui.activity.NotifySettingsActivity;
import com.dreamcoding.administrator.fightluckymoney.ui.fragment.NotifySettingsFragment;

/**
 * Created by Administrator on 2016/9/22 0022 下午 4:56.
 */
public class Global {
        /** 上下文对象 */
        public static Context mContext;
        /** 屏幕宽度 */
        public static int mScreenWidth;
        /** 屏幕高度 */
        public static int mScreenHeight;
        /** 屏幕密度 */
        public static float mDensity;

        public static Handler mHandler = new Handler();

        /** 初始化上下文和屏幕参数 */
        public static void init(Context context) {
            mContext = context;
            initScreenSize();
        }

        /** 初始化屏幕尺寸和密度 */
        private static void initScreenSize() {
            DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
            mScreenWidth = dm.widthPixels;
            mScreenHeight = dm.heightPixels;
            mDensity = dm.density;
        }

        /** dp转px */
        public static int dp2px(int dp) {
            return (int) (dp * mDensity + 0.5f);
        }

        public static Handler getHandler() {
            return mHandler;
        }

        /** 判断当前是否是在主线程运行 */
        public static boolean isInMainThread() {
// 主线程的Looper == 当前线程的Looper
            return Looper.getMainLooper() == Looper.myLooper();
        }

        /** 在主线运行 */
        public static void runOnMainThread(Runnable run) {
            if (isInMainThread()) {// 如果是主线程，则直接运行
                run.run();
            } else {
                mHandler.post(run);
            }
        }

        private static Toast mToast;

        public static void showToast(final String text) {
            runOnMainThread(new Runnable() {
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
                    }
                    mToast.setText(text);
                    mToast.show();
                }
            });
        }

        /**
         * 解析布局文件生成对应的视图对象
         */
        public static View inflate(int resource, ViewGroup parent) {
            return LayoutInflater.from(mContext).inflate(resource, parent, false);
        }


}
