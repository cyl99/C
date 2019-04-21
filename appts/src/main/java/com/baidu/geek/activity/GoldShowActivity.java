package com.baidu.geek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.baidu.geek.R;
import com.baidu.geek.adapter.RlvShowAdapter;
import com.baidu.geek.bean.GoldShowBean;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoldShowActivity extends AppCompatActivity {

    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private ArrayList<GoldShowBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gold_show);
        ButterKnife.bind(this);
        mList = (ArrayList<GoldShowBean>) getIntent().getSerializableExtra("show");
        initView();
    }

    private void initView() {
        mToolBar.setTitle(R.string.special_show);
        mToolBar.setNavigationIcon(R.drawable.left);
        setSupportActionBar(mToolBar);

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishA();
            }
        });
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        RlvShowAdapter adapter = new RlvShowAdapter(mList);
        mRlv.setAdapter(adapter);
        mRlv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

    }

    private void finishA() {
        Intent intent = new Intent();
        intent.putExtra("hui",mList);
        setResult(200,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finishA();
    }
}
