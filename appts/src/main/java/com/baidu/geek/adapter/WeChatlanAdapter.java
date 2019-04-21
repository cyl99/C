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
import com.baidu.geek.bean.WeChatBean;
import com.baidu.geek.bean.ZhuanlanBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/4/17.
 */

public class WeChatlanAdapter extends RecyclerView.Adapter<WeChatlanAdapter.MyViewHolder>{
    private Context mContext;
    private ArrayList<WeChatBean.NewslistBean> mList;

    public WeChatlanAdapter(Context context, ArrayList<WeChatBean.NewslistBean> list) {

        mContext = context;
        mList = list;
    }

    public void setList(ArrayList<WeChatBean.NewslistBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_wechar, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        WeChatBean.NewslistBean dataBean = mList.get(position);

        holder.mTv1.setText(dataBean.getTitle());
        holder.mTv21.setText(dataBean.getDescription());
        holder.mTv22.setText(dataBean.getCtime());
        Glide.with(mContext).load(dataBean.getPicUrl()).into(holder.mIv);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIv;
        private final TextView mTv1;
        private final TextView mTv21;
        private final TextView mTv22;

        public MyViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
            mTv1 = itemView.findViewById(R.id.tv);
            mTv21 = itemView.findViewById(R.id.tv21);
            mTv22 = itemView.findViewById(R.id.tv22);
        }
    }
}
