package com.baidu.geek.fragment;

import com.baidu.geek.R;
import com.baidu.geek.base.BaseFragment;
import com.baidu.geek.presenter.GankP;
import com.baidu.geek.presenter.WeChatP;
import com.baidu.geek.view.GankV;
import com.baidu.geek.view.WeChatV;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class GankFragment extends BaseFragment<GankV,GankP>
        implements GankV {
    @Override
    protected GankP initPresenter() {
        return new GankP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }
}
