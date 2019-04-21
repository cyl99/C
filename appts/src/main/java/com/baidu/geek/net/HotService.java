package com.baidu.geek.net;

import com.baidu.geek.bean.HotBean;
import com.baidu.geek.bean.ZhuanlanBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2019/4/17.
 */

public interface HotService {

    public String url = "http://news-at.zhihu.com/api/4/";
    @GET("news/hot")
    Observable<HotBean> initData();
}
