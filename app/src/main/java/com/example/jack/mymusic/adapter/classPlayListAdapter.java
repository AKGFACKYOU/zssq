package com.example.jack.mymusic.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jack.mymusic.PlayListActivity;
import com.example.jack.mymusic.R;
import com.example.jack.mymusic.bean.Home;
import com.example.jack.mymusic.bean.HomeResponse;
import com.example.jack.mymusic.bean.Play;
import com.example.jack.mymusic.bean.PlayListResponse;
import com.example.jack.mymusic.bean.PlaylistBean;
import com.loopj.android.image.SmartImage;
import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 位置：
 * 作用：
 * 时间：2017/6/13
 */

public class classPlayListAdapter extends RecyclerView.Adapter<classPlayListAdapter.ViewHolder> {
    private List<Play> mPlay;



    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private View mHeaderView;

    private OnItemClickListener mListener;
    private Play plays;

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }

    @Override
    public int getItemViewType(int position) {
        if(mHeaderView == null) return TYPE_NORMAL;
        if(position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }


    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }


    class Holder extends RecyclerView.ViewHolder {

        TextView text;

        public Holder(View itemView) {
            super(itemView);
            if(itemView == mHeaderView) return;
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }

    interface OnItemClickListener {
        void onItemClick(int position, String data);
    }















    static class ViewHolder extends RecyclerView.ViewHolder
    {
        SmartImageView smi;
        TextView tv_name;
        TextView tv_author;


        public ViewHolder(View itemView) {
            super(itemView);
            smi= (SmartImageView) itemView.findViewById(R.id.siv);
            tv_name= (TextView) itemView.findViewById(R.id.tv_album);
            tv_author= (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public classPlayListAdapter(List<Play> PlayList)
    {
        mPlay=PlayList;
    }

    @Override
    public classPlayListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER) return new ViewHolder(mHeaderView);




        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_child,parent,false);
        RecyclerView rl= (RecyclerView) view.findViewById(R.id.rl_MusicList);
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(classPlayListAdapter.ViewHolder holder, int position) {

        if(getItemViewType(position) == TYPE_HEADER) return;


        plays = mPlay.get(position);
        holder.tv_author.setText(plays.getProvider());
        holder.tv_name.setText(plays.getName());
        holder.smi.setImageUrl(plays.getUrl());
    }

    @Override
    public int getItemCount() {
        return mPlay.size();
    }
}
