package com.baidu.geek.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.geek.R;
import com.baidu.geek.bean.DailyNewsBean;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.internal.operators.completable.CompletableLift;

/**
 * @author xts
 *         Created by asus on 2019/4/17.
 */

public class RlvDailyNewsAdapter extends RecyclerView.Adapter {
    private Context mContext;
    public ArrayList<DailyNewsBean.StoriesBean> mNewsList;
    public ArrayList<DailyNewsBean.TopStoriesBean> mBanners;
    private static final int TYPE_BANNER = 0;
    private static final int TYPE_TIME = 1;
    private static final int TYPE_NEWS = 2;
    public String mDate = "今日新闻";
    private ArrayList<DailyNewsBean.StoriesBean> mDatas;
    private List<DailyNewsBean.StoriesBean> mData2;
    private List<DailyNewsBean.TopStoriesBean> mData3;
    private String mTime;

    public RlvDailyNewsAdapter(Context context,
                               ArrayList<DailyNewsBean.StoriesBean> newsList,
                               ArrayList<DailyNewsBean.TopStoriesBean> banners) {

        mContext = context;
        mNewsList = newsList;
        mBanners = banners;
    }

    @Override
    public int getItemViewType(int position) {
        if (mBanners.size() > 0) {
            if (position == 0) {
                return TYPE_BANNER;
            } else if (position == 1) {
                return TYPE_TIME;
            } else {
                return TYPE_NEWS;
            }
        } else {
            if (position == 0) {
                return TYPE_TIME;
            } else {
                return TYPE_NEWS;
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (viewType == TYPE_BANNER) {
            return new BannerVH(inflater.inflate(R.layout.item_banner, null));
        } else if (viewType == TYPE_TIME) {
            return new TimeVH(inflater.inflate(R.layout.item_time, null));
        } else {
            return new NewsVH(inflater.inflate(R.layout.item_item, null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_BANNER) {
            BannerVH bannerVH = (BannerVH) holder;
            bannerVH.mBanner.setImages(mBanners)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path,
                                                 ImageView imageView) {
                            DailyNewsBean.TopStoriesBean bean = (DailyNewsBean.TopStoriesBean) path;
                            Glide.with(mContext).load(bean.getImage()).into(imageView);
                        }
                    }).start();
        } else if (viewType == TYPE_TIME) {
            TimeVH timeVH = (TimeVH) holder;
            timeVH.mTvTime.setText(mDate);
        } else {
            NewsVH newsVH = (NewsVH) holder;
            int newPosition = position - 1;
            if (mBanners.size() > 0) {
                newPosition -= 1;
            }
            final DailyNewsBean.StoriesBean storiesBean = mNewsList.get(newPosition);
            newsVH.mTvTime.setText(storiesBean.getTitle());
            Glide.with(mContext).load(storiesBean.getImages().get(0)).into(newsVH.miv);
            final int finalNewPosition = newPosition;
            newsVH.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickListener.OnClick(finalNewPosition,storiesBean);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mBanners.size() > 0) {
            return mNewsList.size() + 1 + 1;
        } else {
            return mNewsList.size() + 1;
        }
    }

 /*   public void setData(DailyNewsBean bean) {
        mDate = bean.getDate();

        mBanners.clear();
        if (bean.getTop_stories() != null && bean.getTop_stories().size() > 0) {
            mBanners.addAll(bean.getTop_stories());
        }

        mNewsList.clear();
        if (bean.getStories() != null && bean.getStories().size() > 0) {
            mNewsList.addAll(bean.getStories());
        }
        notifyDataSetChanged();
    }*/

    public void setData2(List<DailyNewsBean.StoriesBean> bean) {
        if (bean != null && bean.size() > 0) {
            mNewsList.addAll(bean);
            notifyDataSetChanged();
        }

    }

    public void setData3(List<DailyNewsBean.TopStoriesBean> bean) {
        if (bean != null && bean.size() > 0) {
            mBanners.addAll(bean);
            notifyDataSetChanged();
        }
    }

    public void settime(String time) {
        mDate = time;
    }

    class BannerVH extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        Banner mBanner;

        public BannerVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class TimeVH extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView mTvTime;

        public TimeVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class NewsVH extends RecyclerView.ViewHolder {
        @BindView(R.id.tv)
        TextView mTvTime;

        @BindView(R.id.iv)
        ImageView miv;

        public NewsVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
     OnClickListener mOnClickListener;

         public interface OnClickListener{
             void OnClick(int position,DailyNewsBean.StoriesBean beans);
         }

         public void setOnClickListener(OnClickListener onClickListener) {
             mOnClickListener = onClickListener;
         }
}
