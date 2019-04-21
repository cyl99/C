package com.baidu.geek.view;

import com.baidu.geek.base.BaseMvpView;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public interface LoginView extends BaseMvpView {
    void setData(String data);

    String getUserName();
    String getPsd();

    void showToast(String msg);

}
