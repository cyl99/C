package com.baidu.geek.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.geek.R;
import com.baidu.geek.adapter.ReHotAdapter;
import com.baidu.geek.adapter.ReZhuanlanAdapter;
import com.baidu.geek.base.BaseFragment;
import com.baidu.geek.bean.HotBean;
import com.baidu.geek.net.HotService;
import com.baidu.geek.presenter.EmptyP;
import com.baidu.geek.view.EmptyV;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public class HotFragment extends BaseFragment<EmptyV, EmptyP>
        implements EmptyV {
    @BindView(R.id.rlv)
    XRecyclerView mRlv;
    Unbinder unbinder;
    private ArrayList<HotBean.RecentBean> mList;
    private ReHotAdapter mReHotAdapter;

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HotService.url).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        HotService hotService = retrofit.create(HotService.class);
        Observable<HotBean> data = hotService.initData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotBean zhuanlan) {
                        mList.addAll(zhuanlan.getRecent());
                        mReHotAdapter.setList(mList);
                        mReHotAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    protected void initView() {

        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mList = new ArrayList<>();
        mReHotAdapter = new ReHotAdapter(getContext(), mList);
        mRlv.setAdapter(mReHotAdapter);
        super.initView();
    }


}
