package com.baidu.geek.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.geek.R;
import com.baidu.geek.base.BaseActivity;
import com.baidu.geek.fragment.AboutFragment;
import com.baidu.geek.fragment.CollectFragment;
import com.baidu.geek.fragment.GankFragment;
import com.baidu.geek.fragment.GoldFragment;
import com.baidu.geek.fragment.SettingFragment;
import com.baidu.geek.fragment.V2exFragment;
import com.baidu.geek.fragment.WeChatFragment;
import com.baidu.geek.fragment.ZhihuDailyNewsFragment;
import com.baidu.geek.presenter.MainP;
import com.baidu.geek.view.MainView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xts
 *         Created  on 2019/4/17 15:54
 *         主页面,
 *         xts 于2019/4/18 修改了....
 */

public class MainActivity extends BaseActivity<MainView, MainP> implements MainView {

    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.nv)
    NavigationView mNv;
    @BindView(R.id.dl)
    DrawerLayout mDl;
    @BindView(R.id.lv)
    LinearLayout mLv;
    @BindView(R.id.search_view)
    MaterialSearchView msearch_view;
    @BindView(R.id.text_toolbar)
    TextView mtext_toolbar;


    private ArrayList<Fragment> mFragments;
    private ArrayList<Integer> mTitles;
    private FragmentManager mManager;
    private final int TYPE_ZHIHU = 0;
    private final int TYPE_WECHAT = 1;
    private final int TYPE_GANK = 2;
    private final int TYPE_GOLD = 3;
    private final int TYPE_V2EX = 4;
    private final int TYPE_COLLECT = 5;
    private final int TYPE_SETTINGS = 6;
    private final int TYPE_ABOUT = 7;
    private int mLastFragmentPosition;
    private MenuItem mItem;

    @Override
    protected MainP initPresenter() {
        return new MainP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mManager = getSupportFragmentManager();
        mToolBar.setTitle("");
        mToolBar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolBar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToolBar, R.string.about, R.string.about);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        mDl.addDrawerListener(toggle);
        toggle.syncState();
        //侧滑菜单右移,activity同时右移
        mDl.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                float v = mNv.getWidth() * slideOffset;
                mLv.setX(v);
            }
        });
        initFragments();
        initTitles();
        addZhihuDailyNewsFragment();
    }

    private void addZhihuDailyNewsFragment() {
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.fragment_container, mFragments.get(0));
        transaction.commit();

    }

    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(R.id.zhihu);
        mTitles.add(R.id.wechat);
        mTitles.add(R.id.gank);
        mTitles.add(R.id.gold);
        mTitles.add(R.id.v2ex);
        mTitles.add(R.id.collect);
        mTitles.add(R.id.settings);
        mTitles.add(R.id.about);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new ZhihuDailyNewsFragment());
        mFragments.add(new WeChatFragment());
        mFragments.add(new GankFragment());
        mFragments.add(new GoldFragment());
        mFragments.add(new V2exFragment());
        mFragments.add(new CollectFragment());
        mFragments.add(new SettingFragment());
        mFragments.add(new AboutFragment());
    }

    @Override
    protected void initListener() {
        mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId != R.id.info_title && itemId != R.id.option_title) {
                    item.setChecked(true);
                    switch (itemId) {
                        case R.id.zhihu:
                            switchFragment(TYPE_ZHIHU);
                            mtext_toolbar.setText(R.string.zhihu);
                            mtext_toolbar.setTextColor(Color.YELLOW);
                            break;
                        case R.id.wechat:
                            switchFragment(TYPE_WECHAT);
                            mtext_toolbar.setText(R.string.wechat);
                            mtext_toolbar.setTextColor(Color.WHITE);
                            break;
                        case R.id.gank:
                            switchFragment(TYPE_GANK);
                            mtext_toolbar.setText(R.string.gank);
                            mtext_toolbar.setTextColor(Color.GRAY);
                            break;
                        case R.id.gold:
                            switchFragment(TYPE_GOLD);
                            mtext_toolbar.setText(R.string.gold);
                            mtext_toolbar.setTextColor(Color.LTGRAY);
                            break;
                        case R.id.v2ex:
                            switchFragment(TYPE_V2EX);
                            mtext_toolbar.setText(R.string.v2ex);
                            mtext_toolbar.setTextColor(Color.WHITE);
                            break;
                        case R.id.collect:
                            switchFragment(TYPE_COLLECT);
                            mtext_toolbar.setText(R.string.collect);
                            mtext_toolbar.setTextColor(Color.GRAY);
                            break;
                        case R.id.settings:
                            switchFragment(TYPE_SETTINGS);
                            mtext_toolbar.setText(R.string.settings);
                            mtext_toolbar.setTextColor(Color.WHITE);
                            break;
                        case R.id.about:
                            switchFragment(TYPE_ABOUT);
                            mtext_toolbar.setText(R.string.about);
                            mtext_toolbar.setTextColor(Color.DKGRAY);
                            break;
                    }
                    mDl.closeDrawer(Gravity.LEFT);
                } else {
                    item.setChecked(false);
                }
                return false;
            }
        });
        msearch_view.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //按下搜索或者提交的时候回调,
                //ToastUtil.showShort("提交的内容:"+query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //搜索框内容发生改变的回调,
                //ToastUtil.showShort(newText);
                return false;
            }
        });
        msearch_view.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //搜索框展示
                //ToastUtil.showShort("展示");
            }

            @Override
            public void onSearchViewClosed() {
                //搜索框隐藏
                //ToastUtil.showShort("关闭");
            }
        });
    }


    private void switchFragment(int type) {
        //显示一个fragmnet,隐藏一个Fragment
        //显示
        Fragment fragment = mFragments.get(type);
        //需要隐藏
        Fragment hideFragment = mFragments.get(mLastFragmentPosition);
        FragmentTransaction transaction = mManager.beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.fragment_container, fragment);
        }

        transaction.hide(hideFragment);
        transaction.show(fragment);
        transaction.commit();

        mLastFragmentPosition = type;
        //显示或者隐藏搜索框
        if (type == TYPE_WECHAT || type == TYPE_GANK) {
            mItem.setVisible(true);
        } else {
            mItem.setVisible(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sousuo_menu, menu);
        mItem = menu.findItem(R.id.action_search);
        //隐藏搜索框
        mItem.setVisible(false);
        msearch_view.setMenuItem(mItem);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (msearch_view.isSearchOpen()) {
            msearch_view.closeSearch();
        } else {
            super.onBackPressed();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
