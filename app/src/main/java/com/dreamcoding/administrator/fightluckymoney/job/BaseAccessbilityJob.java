package com.dreamcoding.administrator.fightluckymoney.job;

import android.content.Context;

import com.dreamcoding.administrator.fightluckymoney.config.QianghongbaoWechatConfig;
import com.dreamcoding.administrator.fightluckymoney.service.QiangHongBaoService;


/**
 * <p>Created 16/1/16 上午12:38.</p>
 * <p><a href="mailto:codeboy2013@gmail.com">Email:codeboy2013@gmail.com</a></p>
 * <p><a href="http://www.happycodeboy.com">LeonLee Blog</a></p>
 *
 * @author LeonLee
 */
public abstract class BaseAccessbilityJob implements AccessbilityJob {

    private QiangHongBaoService service;

    @Override
    public void onCreateJob(QiangHongBaoService service) {
        this.service = service;
    }

    public Context getContext() {
        return service.getApplicationContext();
    }

    public QianghongbaoWechatConfig getConfig() {
        return service.getConfig();
    }

    public QiangHongBaoService getService() {
        return service;
    }
}
