package com.baidu.geek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.geek.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimeActivity extends AppCompatActivity {
    private static final String TAG = "TimeActivity";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt)
    TextView mBt;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.mcv)
    MaterialCalendarView mMcv;
    private CalendarDay currentDate;
    private String newMonth;
    private String newDay;
    private String mCurrentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.left);
        setSupportActionBar(mToolbar);
        mMcv.setShowOtherDates(MaterialCalendarView.SHOW_ALL);

        //编辑日历属性
        mMcv.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)   //设置每周开始的第一天
                .setMinimumDate(CalendarDay.from(2010, 4, 3))  //设置可以显示的最早时间
                .setMaximumDate(CalendarDay.from(2500, 5, 12))//设置可以显示的最晚时间
                .setCalendarDisplayMode(CalendarMode.MONTHS)//设置显示模式，可以显示月的模式，也可以显示周的模式
                .commit();// 返回对象并保存
        //      设置点击日期的监听
        mMcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                currentDate = date;
            }
        });

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    @OnClick(R.id.bt)
    public void onViewClicked() {
        getTime();

    }

    private void getTime() {
        if (currentDate != null) {
            int year = currentDate.getYear();
            int month = currentDate.getMonth() + 1; //月份跟系统一样是从0开始的，实际获取时要加1
            int day = currentDate.getDay();
            if (month<10) {
                newMonth = "0"+month;
            }else {
                newMonth = ""+month;
            }
            if (day < 10) {
                newDay = "0" + day;
            } else {
                newDay = day+"";
            }
            String data = ""+year+ newMonth + newDay;
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
            mCurrentDate = sdf.format(date);
            if (Integer.parseInt(data) > Integer.parseInt(mCurrentDate)) {
                Toast.makeText(TimeActivity.this, "以后的事情我可不知道呢，先看看以前的吧", Toast.LENGTH_SHORT).show();
            } else {
            Log.i(TAG, "getTime: "+data);
            Intent intent = new Intent();
            intent.putExtra("hui",data);
            setResult(200,intent);
            finish();
            }
//            Toast.makeText(this, currentDate.toString() + "你选中的是：" + year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "请选择时间", Toast.LENGTH_LONG).show();
        }


    }
}
