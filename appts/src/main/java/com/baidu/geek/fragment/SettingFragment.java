package com.baidu.geek.fragment;

import com.baidu.geek.R;
import com.baidu.geek.base.BaseFragment;
import com.baidu.geek.presenter.EmptyP;
import com.baidu.geek.view.EmptyV;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class SettingFragment extends BaseFragment<EmptyV,EmptyP>
        implements EmptyV {
    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }
}
