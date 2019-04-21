package com.baidu.geek.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baidu.geek.R;
import com.baidu.geek.activity.GoldShowActivity;
import com.baidu.geek.adapter.VpGoldAdapter;
import com.baidu.geek.base.BaseFragment;
import com.baidu.geek.bean.GoldShowBean;
import com.baidu.geek.presenter.GoldP;
import com.baidu.geek.view.GankV;
import com.baidu.geek.view.GoldV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class GoldFragment extends BaseFragment<GoldV, GoldP>
        implements GankV {
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.tab_iv)
    ImageView mTabIv;
    @BindView(R.id.vp)
    ViewPager mVp;
    Unbinder unbinder;
    Unbinder unbinder1;
    private ArrayList<GoldShowBean> mList;
    private ArrayList<Fragment> mFragments;

    @Override
    protected GoldP initPresenter() {
        return new GoldP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold;

    }

    @Override
    protected void initView() {
        initTitle();
        setFragment();
    }

    private void setFragment() {
        initFragment();
        VpGoldAdapter vpGoldAdapter = new VpGoldAdapter(getChildFragmentManager(), mFragments, mList);
        mVp.setAdapter(vpGoldAdapter);
        mTab.setupWithViewPager(mVp);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            GoldShowBean bean = mList.get(i);
            if (bean.ischecked) {
                mFragments.add(new GoldlistFragment(bean.title));

            }
        }
    }
    private void initTitle() {
        mList = new ArrayList<>();
        mList.add(new GoldShowBean("工具资源", true));
        mList.add(new GoldShowBean("Android", true));
        mList.add(new GoldShowBean("iOS", true));
        mList.add(new GoldShowBean("设计", true));
        mList.add(new GoldShowBean("产品", true));
        mList.add(new GoldShowBean("阅读", true));
        mList.add(new GoldShowBean("前端", true));
        mList.add(new GoldShowBean("后端", true));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    @OnClick(R.id.tab_iv)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), GoldShowActivity.class);
        intent.putExtra("show", mList);
        startActivityForResult(intent, 100);
    }

}
