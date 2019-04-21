package com.baidu.geek.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.geek.R;
import com.baidu.geek.adapter.ReZhuanlanAdapter;
import com.baidu.geek.base.BaseFragment;
import com.baidu.geek.bean.ZhuanlanBean;
import com.baidu.geek.net.Zhuanlan;
import com.baidu.geek.presenter.EmptyP;
import com.baidu.geek.view.EmptyV;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

public class SectionsFragment extends BaseFragment<EmptyV, EmptyP>
        implements EmptyV {
    @BindView(R.id.rlv)
    XRecyclerView mRlv;

    private ArrayList<ZhuanlanBean.DataBean> mList;
    private ReZhuanlanAdapter mReZhuanlanAdapter;

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_sections;
    }

    @Override
    protected void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Zhuanlan.url).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        Zhuanlan zhuanlan = retrofit.create(Zhuanlan.class);
        Observable<ZhuanlanBean> data = zhuanlan.initData();
         data.subscribeOn(Schedulers.io())
                         .observeOn(AndroidSchedulers.mainThread())
                         .subscribe(new Observer<ZhuanlanBean>() {
                             @Override
                             public void onSubscribe(Disposable d) {

                             }

                             @Override
                             public void onNext(ZhuanlanBean zhuanlan) {
                                 mList.addAll(zhuanlan.getData());
                                 mReZhuanlanAdapter.setList(mList);
                                 mReZhuanlanAdapter.notifyDataSetChanged();
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

        mRlv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mList = new ArrayList<>();
        mReZhuanlanAdapter = new ReZhuanlanAdapter(getContext(),mList);
        mRlv.setAdapter(mReZhuanlanAdapter);
        super.initView();
    }
}
