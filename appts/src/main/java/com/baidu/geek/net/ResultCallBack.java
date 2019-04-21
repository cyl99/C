package com.baidu.geek.net;

import com.baidu.geek.bean.LoginBean;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public interface ResultCallBack<T> {
    void onSuccess(T bean);
    void onFail(String msg);
}
