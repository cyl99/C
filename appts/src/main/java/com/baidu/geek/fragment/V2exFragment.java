package com.baidu.geek.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baidu.geek.R;
import com.baidu.geek.activity.V2showActivity;
import com.baidu.geek.adapter.VpV2exAdapter;
import com.baidu.geek.base.BaseFragment;
import com.baidu.geek.bean.V2exTabBean;
import com.baidu.geek.presenter.V2exP;
import com.baidu.geek.view.V2exV;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class V2exFragment extends BaseFragment<V2exV, V2exP>
        implements V2exV {
    private static final String TAG = "V2exFragment";
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.tab_iv)
    ImageView mTabIv;
    @BindView(R.id.vp)
    ViewPager mVp;
    Unbinder unbinder;
    Unbinder unbinder1;
    private ArrayList<V2exTabBean> mList;
    private String mHref;

    @Override
    protected V2exP initPresenter() {
        return new V2exP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2ex;
    }

    @Override
    protected void initData() {
        final ArrayList<V2exTabBean> list1 = new ArrayList<>();
        new Thread() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("https://www.v2ex.com/").get();
                    Element tabs = document.getElementById("Tabs");
                    Elements all = tabs.getElementsByTag("a");

                    for (Element element : all) {
                        mHref = element.attr("href");
                        String text = element.text();
                        Log.e(TAG, "href: " + mHref + "text" + text);
                        final V2exTabBean bean = new V2exTabBean(mHref, text);
                        list1.add(bean);
                        Log.e(TAG, "run: " + list1.toString());

                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            ArrayList<Fragment> fragments = new ArrayList<>();
                            for (int i = 0; i < list1.size(); i++) {
                                fragments.add(new V2exlistFragment(list1.get(i).key));
                            }
                            VpV2exAdapter vpV2exAdapter = new VpV2exAdapter(getChildFragmentManager(), fragments, list1);
                            mVp.setAdapter(vpV2exAdapter);
                            mTab.setupWithViewPager(mVp);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @OnClick(R.id.tab_iv)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), V2showActivity.class));
    }
}
