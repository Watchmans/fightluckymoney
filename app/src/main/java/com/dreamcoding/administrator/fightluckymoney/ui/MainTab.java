package com.dreamcoding.administrator.fightluckymoney.ui;


import com.dreamcoding.administrator.fightluckymoney.R;
import com.dreamcoding.administrator.fightluckymoney.ui.fragment.FlowFragment;
import com.dreamcoding.administrator.fightluckymoney.ui.fragment.MeFragment;
import com.dreamcoding.administrator.fightluckymoney.ui.fragment.QianghongbaoFragment;
import com.dreamcoding.administrator.fightluckymoney.ui.fragment.ServerFragment;

public enum MainTab {

	LUCKY_MONEY(0, R.string.main_tab_qianghongbao, R.drawable.tab_icon_new,
			QianghongbaoFragment.class),

	FLOW(1, R.string.main_tab_flow, R.drawable.tab_icon_tweet,
			FlowFragment.class),

/*	QUICK(2, R.string.main_tab_name_quick, R.drawable.tab_icon_new,
			null),*/

	SERVER(2, R.string.main_tab_name_server, R.drawable.tab_icon_explore,
			ServerFragment.class),
			
	ME(3, R.string.main_tab_name_my, R.drawable.tab_icon_me,
			MeFragment.class);

	private int idx;
	private int resName;//名称的资源id
	private int resIcon;//图标的资源id
	private Class<?> clz;

	private MainTab(int idx, int resName, int resIcon, Class<?> clz) {
		this.idx = idx;
		this.resName = resName;
		this.resIcon = resIcon;
		this.clz = clz;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getResName() {
		return resName;
	}

	public void setResName(int resName) {
		this.resName = resName;
	}

	public int getResIcon() {
		return resIcon;
	}

	public void setResIcon(int resIcon) {
		this.resIcon = resIcon;
	}

	public Class<?> getClz() {
		return clz;
	}

	public void setClz(Class<?> clz) {
		this.clz = clz;
	}
}
