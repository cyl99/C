package com.baidu.geek.presenter;

import com.baidu.geek.base.BasePresenter;
import com.baidu.geek.bean.DailyNewsBean;
import com.baidu.geek.model.DailyNewsM;
import com.baidu.geek.net.ResultCallBack;
import com.baidu.geek.view.DailyNewsV;
import com.baidu.geek.view.EmptyV;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class DailyNewsP extends BasePresenter<DailyNewsV> {

    private DailyNewsM mDailyNewsM;

    @Override
    protected void initModel() {
        mDailyNewsM = new DailyNewsM();
        mModels.add(mDailyNewsM);
    }

    public void getData() {
        mDailyNewsM.getData(new ResultCallBack<DailyNewsBean>() {
            @Override
            public void onSuccess(DailyNewsBean bean) {
                if (bean != null){
                    if (mView != null){
                        mView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
