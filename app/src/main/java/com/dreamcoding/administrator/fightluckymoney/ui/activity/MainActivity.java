package com.dreamcoding.administrator.fightluckymoney.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.dreamcoding.administrator.fightluckymoney.R;
import com.dreamcoding.administrator.fightluckymoney.base.BaseActivity;
import com.dreamcoding.administrator.fightluckymoney.ui.MainTab;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private FragmentTabHost mTabHost;
    private FrameLayout mFltRealcontent;


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login_and_register) {
            // Handle the camera action
        } else if (id == R.id.nav_notice) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_feedback) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string
                .navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mFltRealcontent = findView(R.id.flt_realcontent);
        mTabHost = findView(android.R.id.tabhost);

        //初始化底部标签
        /**
         * 设置TabHost
         * context :上下文
         * manager ：片段管理器
         * containerId:装载片段布局的容器
         */
        mTabHost.setup(this, getSupportFragmentManager(), R.id.flt_realcontent);
        if(android.os.Build.VERSION.SDK_INT>10){ //大于10版本，不显示分割线
            //标签小部件，是一个View，
            TabWidget tabWidget = mTabHost.getTabWidget();
            //不显示分割线
            tabWidget.setShowDividers(0);
        }

        // 往FragmentTabHost ：添加tab标签
        addTabs();
    }

    //初始化FragmentTabHost，往标签容器中添加标签
    private void addTabs() {
        MainTab[] tabs = MainTab.values();
        final int size = tabs.length;
        for (int i = 0; i < size; i++) {
            // 找到每一个枚举的Fragment对象
            MainTab mainTab = tabs[i];
            //fragment
            // 1. 创建一个新的选项卡
            TabHost.TabSpec tab=mTabHost.newTabSpec(this.getResources().getString(mainTab
                    .getResName()));
            // ------------------------------------------------- 自定义选项卡 ↓
            View indicator = LayoutInflater.from(getApplicationContext())
                    .inflate(R.layout.tab_indicator, null);
            TextView title = (TextView) indicator.findViewById(R.id.tab_title);
            Drawable drawable = this.getResources().getDrawable(
                    mainTab.getResIcon());
            //设置TextView上下左右的绘图
            title.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null,
                    null);
            /*if (i == 2) {

                //当是正中间的标签，则隐藏 ，用于显示ImageView 弹出对话框的控件
                indicator.setVisibility(View.INVISIBLE);
            }*/
            title.setText(getString(mainTab.getResName()));
            //设置指示器，是一个View
            tab.setIndicator(indicator);
            // ------------------------------------------------- 以上 ↑

            //把数据传给fragment
            Bundle bundle = new Bundle();
            bundle.putString("key",
                    "content: " + getString(mainTab.getResName()));
            // 2. 把新的选项卡添加到TabHost中,这里关联fragment
            mTabHost.addTab(tab, mainTab.getClz(), bundle);
        }
    }



    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view, int id) {

    }

}
