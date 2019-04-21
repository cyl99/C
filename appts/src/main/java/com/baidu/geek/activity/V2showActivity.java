package com.baidu.geek.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.baidu.geek.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class V2showActivity extends AppCompatActivity {

    @BindView(R.id.tool_text)
    TextView mToolText;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rlv)
    XRecyclerView mRlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v2show);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("http://example.com/").get();
                    Elements select = document.select("div.cell");
                    String text = select.text();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
