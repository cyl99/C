package com.baidu.geek.base;

import java.util.ArrayList;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public abstract class BasePresenter<V extends BaseMvpView> {
    protected V mView;
    protected ArrayList<BaseModel> mModels = new ArrayList<>();

    public BasePresenter() {
       initModel();
    }

    protected abstract void initModel();

    public void bind(V view) {
        this.mView = view;
    }

    public void onDestory() {
        mView = null;
        if (mModels.size()>0){
            for (BaseModel model : mModels) {
                model.onDestory();
            }
        }
    }
}
