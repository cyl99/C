package com.baidu.geek.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.geek.R;
import com.baidu.geek.adapter.VpZhihuAdapter;
import com.baidu.geek.base.BaseFragment;
import com.baidu.geek.presenter.ZhihuDailyNewsP;
import com.baidu.geek.view.ZhihuDailyNewsV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class ZhihuDailyNewsFragment extends BaseFragment<ZhihuDailyNewsV, ZhihuDailyNewsP>
        implements ZhihuDailyNewsV {
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<Integer> mTitles;
    private ArrayList<BaseFragment> mFragments;

    @Override
    protected ZhihuDailyNewsP initPresenter() {
        return new ZhihuDailyNewsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_daily_news;
    }

    @Override
    protected void initView() {
        initTitles();
        initFragments();

        VpZhihuAdapter adapter = new VpZhihuAdapter(getContext(),
                getChildFragmentManager(), mFragments, mTitles);
        mVp.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mVp);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new DailyNewsFragment());
        mFragments.add(new SectionsFragment());
        mFragments.add(new SectionsFragment());
        mFragments.add(new HotFragment());
    }

    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(R.string.dailyNews);
        mTitles.add(R.string.sections);
        mTitles.add(R.string.sections);
        mTitles.add(R.string.hot);
    }
}
