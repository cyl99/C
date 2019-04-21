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
import com.baidu.geek.bean.V2exFragmentBean;
import com.baidu.geek.bean.WeChatBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/4/17.
 */

public class V2exFragmentAdapter extends RecyclerView.Adapter<V2exFragmentAdapter.MyViewHolder>{
    private Context mContext;
    private ArrayList<V2exFragmentBean> mList;

    public V2exFragmentAdapter(Context context, ArrayList<V2exFragmentBean> list) {

        mContext = context;
        mList = list;
    }

    public void setList(ArrayList<V2exFragmentBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_wecharfragment, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        V2exFragmentBean bean = mList.get(position);
        holder.mTv1.setText(bean.zuozhe);
        holder.mTv2.setText(bean.two);
        holder.mTv3.setText(bean.pinglunshu);
        holder.mTv4.setText(bean.pinglunurl);
        holder.mTv5.setText(bean.text);

        Glide.with(mContext).load("https://"+bean.url).into(holder.mIv);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIv;
        private final TextView mTv1;
        private final TextView mTv2;
        private final TextView mTv3;
        private final TextView mTv4;
        private final TextView mTv5;


        public MyViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
            mTv1 = itemView.findViewById(R.id.text1);
            mTv2 = itemView.findViewById(R.id.text2);
            mTv3 = itemView.findViewById(R.id.text3);
            mTv4 = itemView.findViewById(R.id.text4);
            mTv5 = itemView.findViewById(R.id.text5);
        }
    }
}
