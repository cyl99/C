package com.baidu.geek.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 *         MVP
 *         V:处理视图,用户交互
 *         P:业务逻辑,连接V和M层的桥梁
 *         M:处理数据:网络数据,数据库,io操作....耗时操作
 */

public abstract class BaseActivity<V extends BaseMvpView,P extends BasePresenter>
        extends AppCompatActivity  implements BaseMvpView{
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mPresenter = initPresenter();
        if (mPresenter != null){
            //直接强转不对,因为BaseActivity不作为页面展示,展示的都是他的子类,
            // 而子类必须实现BaseMvpView
            mPresenter.bind((V) this);
        }
        initView();
        initListener();
        initData();
    }

    protected abstract P initPresenter();

    protected void initData() {

    }

    protected void initListener() {

    }

    protected void initView(){

    };

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //切断V层和P层的联系
        mPresenter.onDestory();
        mPresenter = null;
    }
}
