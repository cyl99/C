package com.baidu.geek.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public class BaseModel {
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    public void onDestory() {
        mCompositeDisposable.clear();
    }
}
