package com.dreamcoding.administrator.fightluckymoney.ui.fragment;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.dreamcoding.administrator.fightluckymoney.R;
import com.dreamcoding.administrator.fightluckymoney.base.BaseFragment;
import com.dreamcoding.administrator.fightluckymoney.base.Global;
import com.dreamcoding.administrator.fightluckymoney.ui.adapter.QianghongbaoAdapter;
import com.dreamcoding.administrator.fightluckymoney.ui.view.PagerSlidingTabStrip;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/24 0024 下午 3:51.
 */
public class QianghongbaoFragment extends BaseFragment{

    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private ViewPager vp_qianghongbao;
    private ArrayList<BaseFragment> fragments;
    private QianghongbaoAdapter qianghongbaoAdapter;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_qianghongbao;
    }

    @Override
    public void initView() {
        pagerSlidingTabStrip = findView(R.id.page_tabs);
        vp_qianghongbao = findView(R.id.vp_qianghongbao);

        initFragments();
        if(qianghongbaoAdapter==null){
            qianghongbaoAdapter = new QianghongbaoAdapter(getChildFragmentManager());
            vp_qianghongbao.setAdapter(qianghongbaoAdapter);
        }else{
            qianghongbaoAdapter.notifyDataSetChanged();
        }

        qianghongbaoAdapter.addFragments(fragments);
        //pagerSlidingTabStrip对viewpager进行绑定
        pagerSlidingTabStrip.setViewPager(vp_qianghongbao);
        initTab();
        //初始化Viewpager
        vp_qianghongbao.setCurrentItem(0);
    }

    private void initTab() {
        View tabViewWechat=Global.inflate(R.layout.tab_item,null);
        TextView tvTabInWechat= (TextView) tabViewWechat.findViewById(R.id.tab_title);
        tvTabInWechat.setText(R.string.model_qianghongbao_tab_wechat);
        tvTabInWechat.setGravity(Gravity.CENTER);
        pagerSlidingTabStrip.addTab(tabViewWechat);

        View tabViewQQ=Global.inflate(R.layout.tab_item,null);
        TextView tvTabInQQ= (TextView) tabViewQQ.findViewById(R.id.tab_title);
        tvTabInQQ.setText(R.string.model_qianghongbao_tab_qq);
        tvTabInQQ.setGravity(Gravity.CENTER);
        pagerSlidingTabStrip.addTab(tabViewQQ);
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        QianghongbaoToWechatFragment qianghongbaoToWechatFragment=new
                QianghongbaoToWechatFragment();
        QianghongbaoToQQFragment qianghongbaoToQQFragment=new QianghongbaoToQQFragment();
        fragments.add(qianghongbaoToWechatFragment);
        fragments.add(qianghongbaoToQQFragment);
    }

    @Override
    public void initListener() {
        pagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view, int id) {

    }
}
