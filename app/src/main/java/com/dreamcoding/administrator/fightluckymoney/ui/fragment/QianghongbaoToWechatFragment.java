package com.dreamcoding.administrator.fightluckymoney.ui.fragment;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.preference.Preference;
import android.support.v4.preference.SwitchPreference;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.dreamcoding.administrator.fightluckymoney.R;
import com.dreamcoding.administrator.fightluckymoney.base.BasePreferenceFragment;
import com.dreamcoding.administrator.fightluckymoney.base.Global;
import com.dreamcoding.administrator.fightluckymoney.base.MyApplication;
import com.dreamcoding.administrator.fightluckymoney.config.QianghongbaoWechatConfig;
import com.dreamcoding.administrator.fightluckymoney.job.WechatAccessbilityJob;
import com.dreamcoding.administrator.fightluckymoney.service.QiangHongBaoService;
import com.dreamcoding.administrator.fightluckymoney.ui.activity.NotifySettingsActivity;
import com.dreamcoding.administrator.fightluckymoney.ui.activity.WechatSettingsActivity;
import com.dreamcoding.administrator.fightluckymoney.util.BitmapUtils;
import java.io.File;

/**
 * Created by Administrator on 2016/9/24 0024 下午 10:03.
 */
public class QianghongbaoToWechatFragment extends BasePreferenceFragment{
    private SwitchPreference notificationPref;
    private Dialog mTipsDialog;
    private boolean notificationChangeByUser = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.qianghongbao_wechat);

        //微信红包开关
        Preference wechatPref = findPreference(QianghongbaoWechatConfig.KEY_ENABLE_WECHAT);
        wechatPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if((Boolean) newValue && !QiangHongBaoService.isRunning()) {
                    showOpenAccessibilityServiceDialog();
                }
                return true;
            }
        });

        notificationPref = (SwitchPreference) findPreference("KEY_NOTIFICATION_SERVICE_TEMP_ENABLE");
        notificationPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    Toast.makeText(getActivity(), "该功能只支持安卓4.3以上的系统", Toast.LENGTH_SHORT).show();
                    return false;
                }

                if(!notificationChangeByUser) {
                    notificationChangeByUser = true;
                    return true;
                }

                boolean enalbe = (boolean) newValue;

                QianghongbaoWechatConfig.getConfig(getActivity()).setNotificationServiceEnable(enalbe);

                if(enalbe && !QiangHongBaoService.isNotificationServiceRunning()) {
                    openNotificationServiceSettings();
                    return false;
                }
                MyApplication.eventStatistics(getActivity(), "notify_service", String.valueOf(newValue));
                return true;
            }
        });

        Preference preference = findPreference("KEY_FOLLOW_ME");
        if(preference != null) {
            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    showQrDialog();
                    MyApplication.eventStatistics(getActivity(), "about_author");
                    return true;
                }

            });
        }

        preference = findPreference("KEY_DONATE_ME");
        if(preference != null) {
            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    showDonateDialog();
                    MyApplication.eventStatistics(getActivity(), "donate");
                    return true;
                }

            });
        }

        findPreference("WECHAT_SETTINGS").setOnPreferenceClickListener(new Preference
                .OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(getActivity(), WechatSettingsActivity.class));
                return true;
            }

        });

        findPreference("NOTIFY_SETTINGS").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(getActivity(), NotifySettingsActivity.class));
                return true;
            }

        });

    }



    /** 显示未开启辅助服务的对话框*/
    private void showOpenAccessibilityServiceDialog() {
        if(mTipsDialog != null && mTipsDialog.isShowing()) {
            return;
        }
        View view = Global.inflate(R.layout.dialog_tips_layout, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccessibilityServiceSettings();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.open_service_title);
        builder.setView(view);
        builder.setPositiveButton(R.string.open_service_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openAccessibilityServiceSettings();
            }
        });
        mTipsDialog = builder.show();
    }

    /** 打开辅助服务的设置*/
    private void openAccessibilityServiceSettings() {
        try {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
            Toast.makeText(getActivity(), R.string.tips, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 打开通知栏设置*/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private void openNotificationServiceSettings() {
        try {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            startActivity(intent);
            Toast.makeText(getActivity(), R.string.tips, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showQrDialog() {
        final Dialog dialog = new Dialog(getActivity(), R.style.QR_Dialog_Theme);
        View view =Global.inflate(R.layout.qr_dialog_layout, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getString(R.string.qr_wx_id);
                ClipboardManager clipboardManager = (ClipboardManager) getActivity()
                        .getSystemService(Context
                        .CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", id);
                clipboardManager.setPrimaryClip(clip);

                //跳到微信
                Intent wxIntent = getActivity().getPackageManager().getLaunchIntentForPackage(
                        WechatAccessbilityJob.WECHAT_PACKAGENAME);
                if(wxIntent != null) {
                    try {
                        startActivity(wxIntent);
                    } catch (Exception e){}
                }

                Toast.makeText(getActivity().getApplicationContext(), "已复制到粘贴板", Toast
                        .LENGTH_LONG).show();
                MyApplication.eventStatistics(getActivity(), "copy_qr");
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }


    /** 显示捐赠的对话框*/
    private void showDonateDialog() {
        final Dialog dialog = new Dialog(getActivity(), R.style.QR_Dialog_Theme);
        View view = Global.inflate(R.layout.donate_dialog_layout,null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                File output = new File(android.os.Environment.getExternalStorageDirectory(), "codeboy_wechatpay_qr.jpg");
                if(!output.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wechatpay_qr);
                    BitmapUtils.saveBitmap(getActivity(), output, bitmap);
                }
                Toast.makeText(getActivity(), "已保存到:" + output.getAbsolutePath(), Toast.LENGTH_LONG).show();
                return true;
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }

}
