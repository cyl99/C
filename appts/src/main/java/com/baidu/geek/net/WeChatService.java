package com.baidu.geek.net;

import com.baidu.geek.bean.WeChatBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2019/4/19.
 */

public interface WeChatService {
    //http://api.tianapi.com/wxnew/?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1
    public String url = "http://api.tianapi.com/";
    @GET
    Observable<WeChatBean> initData(@Url String str);
}
