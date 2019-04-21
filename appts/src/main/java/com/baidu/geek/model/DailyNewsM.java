package com.baidu.geek.model;

import com.baidu.geek.base.BaseModel;
import com.baidu.geek.bean.DailyNewsBean;
import com.baidu.geek.net.BaseObserver;
import com.baidu.geek.net.HttpUtils;
import com.baidu.geek.net.ResultCallBack;
import com.baidu.geek.net.RxUtils;
import com.baidu.geek.net.ZhihuService;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * @author xts
 *         Created by asus on 2019/4/17.
 */

public class DailyNewsM extends BaseModel {
    public void getData(final ResultCallBack<DailyNewsBean> callBack) {
        ZhihuService apiserver = HttpUtils.getInstance().getApiserver(ZhihuService.sBaseUrl, ZhihuService.class);
        Observable<DailyNewsBean> lastDailyNews = apiserver.getLastDailyNews();
        lastDailyNews.compose(RxUtils.<DailyNewsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DailyNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DailyNewsBean dailyNewsBean) {
                        callBack.onSuccess(dailyNewsBean);
                    }
                });
    }
}
