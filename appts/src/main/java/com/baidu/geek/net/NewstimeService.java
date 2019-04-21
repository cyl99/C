package com.baidu.geek.net;

import com.baidu.geek.bean.DailyNewsBean;
import com.baidu.geek.bean.NewTimeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2019/4/18.
 */

public interface NewstimeService  {
    public String url = "http://news-at.zhihu.com/api/4/";

    @GET("news/before/{date}")
     Observable<NewTimeBean> initData(@Path("date") String data);
}
