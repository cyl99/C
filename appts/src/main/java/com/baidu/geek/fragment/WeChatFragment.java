package com.baidu.geek.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.geek.R;
import com.baidu.geek.adapter.WeChatlanAdapter;
import com.baidu.geek.base.BaseFragment;
import com.baidu.geek.bean.WeChatBean;
import com.baidu.geek.net.WeChatService;
import com.baidu.geek.presenter.WeChatP;
import com.baidu.geek.view.WeChatV;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class WeChatFragment extends BaseFragment<WeChatV, WeChatP>
        implements WeChatV {
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.sm)
    SmartRefreshLayout mSm;
    Unbinder unbinder;
    private ArrayList<WeChatBean.NewslistBean> mList;
    private WeChatlanAdapter mWeChatlanAdapter;
    private int num = 10;
    private int page = 1;
    @Override
    protected WeChatP initPresenter() {
        return new WeChatP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initView() {
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mList = new ArrayList<>();
        mWeChatlanAdapter = new WeChatlanAdapter(getContext(), mList);
        mRlv.setAdapter(mWeChatlanAdapter);
        mSm.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
                mWeChatlanAdapter.notifyDataSetChanged();
                mSm.finishLoadMore();
            }
        });
        mSm.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                mList.clear();
                initData();
                mWeChatlanAdapter.notifyDataSetChanged();
                mSm.finishRefresh();
            }
        });
    }
//key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1
    @Override
    protected void initData() {
      Retrofit retrofit = new Retrofit.Builder().baseUrl(WeChatService.url).addConverterFactory(GsonConverterFactory.create())
                               .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        WeChatService service = retrofit.create(WeChatService.class);
        Observable<WeChatBean> data = service.initData("wxnew/?key=52b7ec3471ac3bec6846577e79f20e4c&num="+num+"&page="+page);
         data.subscribeOn(Schedulers.io())
                         .observeOn(AndroidSchedulers.mainThread())
                         .subscribe(new Observer<WeChatBean>() {
                             @Override
                             public void onSubscribe(Disposable d) {
                             }

                             @Override
                             public void onNext(WeChatBean weChatBean) {
                                 mList.addAll(weChatBean.getNewslist());
                                 mWeChatlanAdapter.setList(mList);
                                 mWeChatlanAdapter.notifyDataSetChanged();
                             }

                             @Override
                             public void onError(Throwable e) {

                             }

                             @Override
                             public void onComplete() {

                             }
                         });
    }
}
