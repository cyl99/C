package com.baidu.geek.presenter;

import android.text.TextUtils;

import com.baidu.geek.base.BasePresenter;
import com.baidu.geek.bean.LoginBean;
import com.baidu.geek.model.LoginM;
import com.baidu.geek.net.ResultCallBack;
import com.baidu.geek.utils.Logger;
import com.baidu.geek.view.LoginView;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public class LoginP extends BasePresenter<LoginView> {

    private static final String TAG = "LoginP";
    private LoginM mMainM;

    public void getData(){
        //获取数据,假设数据网络来的
        String data = "网络回来的数据";
        if (mView != null){
            //每次转换类型,麻烦
            //((LoginView)mView).setData(data);
            mView.setData(data);
        }
    }

    public void login() {
        String name = mView.getUserName();
        String psd = mView.getPsd();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(psd)){
            mView.showToast("用户名或者密码不能为空");
            return;
        }

        //进行网络请求,去登陆,M
        mMainM.login(name,psd, new ResultCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {
                if (bean != null){
                    Logger.logD(TAG,bean.toString());
                    if (bean.getCode() == 200){
                        //防止页面销毁,数据返回后设置页面的时空指针
                        if (mView != null){
                            mView.showToast("登录成功");
                        }
                    }else {
                        if (mView != null){
                            mView.showToast("登录失败");
                        }
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                Logger.logD(TAG,msg);
                if (mView != null){
                    mView.showToast("登录失败");
                }
            }
        });
    }

    @Override
    protected void initModel() {
        mMainM = new LoginM();
        mModels.add(mMainM);
    }
}
