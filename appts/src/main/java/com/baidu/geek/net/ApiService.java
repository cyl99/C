package com.baidu.geek.net;

import com.baidu.geek.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public interface ApiService {
    String sBaseUrl = "http://yun918.cn/study/public/index.php/";

    /**
     * 登录
     * @param name,用户名
     * @param psd,密码
     * @return
     */
    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean> login(@Field("username") String name,
                                @Field("password") String psd);
}
