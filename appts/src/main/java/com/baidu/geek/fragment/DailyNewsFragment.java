package com.baidu.geek.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.geek.R;
import com.baidu.geek.activity.TimeActivity;
import com.baidu.geek.adapter.RlvDailyNewsAdapter;
import com.baidu.geek.base.BaseFragment;
import com.baidu.geek.bean.DailyNewsBean;
import com.baidu.geek.bean.HotBean;
import com.baidu.geek.bean.NewTimeBean;
import com.baidu.geek.net.HotService;
import com.baidu.geek.net.NewstimeService;
import com.baidu.geek.presenter.DailyNewsP;
import com.baidu.geek.view.DailyNewsV;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

public class DailyNewsFragment extends BaseFragment<DailyNewsV, DailyNewsP>
        implements DailyNewsV {
    private static final String TAG = "DailyNewsFragment";
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    Unbinder unbinder;

    private RlvDailyNewsAdapter mAdapter;
    private String mExtra;
    private ArrayList<DailyNewsBean.StoriesBean> mNewsList;
    private String mCurrentDate;


    @Override
    protected DailyNewsP initPresenter() {
        return new DailyNewsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily_news;
    }

    @Override
    protected void initView() {
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mNewsList = new ArrayList<>();
        ArrayList<DailyNewsBean.TopStoriesBean> banners = new ArrayList<>();
        mAdapter = new RlvDailyNewsAdapter(getContext(), mNewsList, banners);
        mRlv.setAdapter(mAdapter);
        mAdapter.setOnClickListener(new RlvDailyNewsAdapter.OnClickListener() {
            @Override
            public void OnClick(int position, DailyNewsBean.StoriesBean beans) {

            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    @Override
    public void setData(DailyNewsBean bean) {
        mAdapter.setData2(bean.getStories());
        mAdapter.setData3(bean.getTop_stories());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(),TimeActivity.class);
        startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data!=null) {
            if (requestCode == 100&& resultCode == 200) {
                mExtra = data.getStringExtra("hui");
                initData2();
               // Toast.makeText(getContext(), mExtra, Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initData2() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(NewstimeService.url).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        NewstimeService service = retrofit.create(NewstimeService.class);
        Observable<NewTimeBean> data = service.initData(mExtra);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewTimeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewTimeBean newTimeBean) {
                        Date date = new Date(System.currentTimeMillis());
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
                        mCurrentDate = sdf.format(date);
                        List<NewTimeBean.StoriesBean> list = newTimeBean.getStories();
                        ArrayList<DailyNewsBean.StoriesBean> storiesBeans = new ArrayList<>();
                        for (int i = 0; i < list.size(); i++) {
                            NewTimeBean.StoriesBean bean = list.get(i);
                            DailyNewsBean.StoriesBean bean1 = new DailyNewsBean.StoriesBean();
                            bean1.setImages(bean.getImages());
                            bean1.setTitle(bean.getTitle());
                            storiesBeans.add(bean1);
                        }
                        if (!mExtra.isEmpty()){
                             if (Integer.parseInt(mExtra)!=Integer.parseInt(mCurrentDate)){
                                mAdapter.mBanners.clear();
                            }
                        }
                        mAdapter.mNewsList.clear();
                        mNewsList.addAll(storiesBeans);
                        mAdapter.settime(mExtra);
                        mAdapter.setData2(mNewsList);

                        mAdapter.notifyDataSetChanged();


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
