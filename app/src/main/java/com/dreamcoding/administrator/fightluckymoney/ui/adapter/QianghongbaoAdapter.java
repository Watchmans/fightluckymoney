package com.dreamcoding.administrator.fightluckymoney.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dreamcoding.administrator.fightluckymoney.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/24 0024 下午 9:54.
 */
public class QianghongbaoAdapter extends FragmentPagerAdapter {
    ArrayList<BaseFragment> fragments=new ArrayList<BaseFragment>();

    public QianghongbaoAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragments(ArrayList<BaseFragment> fragments){
        this.fragments.addAll(fragments);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments!=null?this.fragments.size():0;
    }
}
