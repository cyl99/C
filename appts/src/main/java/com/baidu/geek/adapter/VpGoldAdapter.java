package com.baidu.geek.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.baidu.geek.bean.GoldShowBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/4/19.
 */

public class VpGoldAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> mFragments;
    private ArrayList<GoldShowBean> mList;

    public VpGoldAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<GoldShowBean> list) {
        super(fm);
        mFragments = fragments;
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).title;
    }
}
