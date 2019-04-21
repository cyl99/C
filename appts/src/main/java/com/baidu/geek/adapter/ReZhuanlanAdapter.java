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
import com.baidu.geek.bean.ZhuanlanBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/4/17.
 */

public class ReZhuanlanAdapter extends RecyclerView.Adapter<ReZhuanlanAdapter.MyViewHolder>{
    private Context mContext;
    private ArrayList<ZhuanlanBean.DataBean> mList;

    public ReZhuanlanAdapter(Context context, ArrayList<ZhuanlanBean.DataBean> list) {

        mContext = context;
        mList = list;
    }

    public void setList(ArrayList<ZhuanlanBean.DataBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_zhuanlan, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ZhuanlanBean.DataBean dataBean = mList.get(position);
        holder.mTv2.setText(dataBean.getName());
        holder.mTv1.setText(dataBean.getDescription());
        Glide.with(mContext).load(dataBean.getThumbnail()).into(holder.mIv);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIv;
        private final TextView mTv1;
        private final TextView mTv2;

        public MyViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.rlv1);
            mTv1 = itemView.findViewById(R.id.tv1);
            mTv2 = itemView.findViewById(R.id.tv2);
        }
    }
}
