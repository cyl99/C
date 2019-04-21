package com.baidu.geek.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.baidu.geek.bean.V2exTabBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/4/19.
 */

public class VpV2exAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> mFragments;
    private ArrayList<V2exTabBean> mList;

    public VpV2exAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<V2exTabBean> list) {
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
        return mList.get(position).tab;
    }
}
