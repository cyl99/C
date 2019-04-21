package com.baidu.geek.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.geek.R;
import com.baidu.geek.adapter.V2exFragmentAdapter;
import com.baidu.geek.bean.V2exFragmentBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class V2exlistFragment extends Fragment {


    @BindView(R.id.rlv)
    XRecyclerView mRlv;
    Unbinder unbinder;
    private String myurl = "https://www.v2ex.com/";
    private static final String TAG = "V2exlistFragment";
    private String mHref;
    private String huifu = "几秒前 • 最后回复来自 ";

    public V2exlistFragment(String href) {
        // Required empty public constructor
        String substring = href.substring(1);
        mHref = substring;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_v2exlist, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initData();
        return inflate;

    }

    private void initData() {
        new Thread() {

            private String mPeople1;
            private String mPeople2;
            private String mPinglunurl;
            private String mPinglunshu;

            @Override
            public void run() {
                final ArrayList<V2exFragmentBean> list = new ArrayList<>();
                try {
                    Document doc = Jsoup.connect(myurl + mHref).get();
                    Elements items = doc.select("div.cell.item");
                    for (Element element : items) {
                        //图片
                        Element image = element.select("table tbody tr td > a >img.avatar").first();
                        String murl = image.attr("src");
                        //Log.d(TAG, "图片: "+src);

                        //评论,有可能没有,需要判空
                        Element comment = element.select("table tbody tr td >a.count_livid").first();
                        if (comment != null) {
                            mPinglunshu = comment.text();
                            mPinglunurl = comment.attr("href");
                            //Log.d(TAG, "评论数量: "+commentCount+",href:"+href);

                        }
                        //新闻的主体信息
                        Element titleElement = element.select("table tbody tr td span.item_title > a").first();
                        String text = titleElement.text();
                        Log.d(TAG, "标题: " + text);
                        //评论的信息
                        Elements topicElement = element.select("table tbody tr td span.topic_info");
                        String title = topicElement.text();
                        Log.d(TAG, "topic: " + title);

                        Element secondTab = topicElement.select("a.node").first();
                        String two = secondTab.text();
                        Log.d(TAG, "二类的Tab:" + two);
                        Element huifiall = topicElement.select("a.node").first();
                        String two1 = secondTab.text();
                        Log.d(TAG, "二类的Tab:" + two);

                        Elements people = topicElement.select("strong > a");
                        if (people.size() > 0) {
                            Element author = people.get(0);
                            mPeople1 = author.text();
                            Log.d(TAG, "作者: " + mPeople1);
                        }
                        if (people.size() > 1) {
                            Element commentPeople = people.get(1);
                            mPeople2 = commentPeople.text();
                            Log.d(TAG, "评论人: " + mPeople2);
                        }
// public V2exFragmentBean(String url, String pinglunshu, String text, String pinglunren, String zuozhe, String two, String huifu, String pinglunurl) {
                        V2exFragmentBean bean = new V2exFragmentBean(murl, mPinglunshu, text, mPeople2, mPeople1, two1, huifu, title);
                        list.add(bean);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
                        V2exFragmentAdapter adapter = new V2exFragmentAdapter(getContext(), list);
                        mRlv.setAdapter(adapter);
                    }
                });
            }
        }.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
