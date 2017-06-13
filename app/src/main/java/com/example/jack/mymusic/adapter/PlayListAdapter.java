package com.example.jack.mymusic.adapter;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jack.mymusic.Constant;
import com.example.jack.mymusic.R;
import com.example.jack.mymusic.bean.NewPlayListResultsBean;
import com.example.jack.mymusic.bean.PlayListResponse;

import java.util.ArrayList;

/**
 * 位置：
 * 作用：
 * 时间：2017/6/11
 */

public class PlayListAdapter extends RecyclerView.Adapter {
    private static final int TYPE_HEAD = 0;//头布局
    private static final int TYPE_FOOTER = 1;//尾布局
    private static final int TYPE_NORMAL = 2;//默认的布局


    public static final String PLAYDATA_KEY = "playData";

    ArrayList<NewPlayListResultsBean> mResultsBeen;

    private View mHeadView;
    private View mFooterView;

    public PlayListAdapter(ArrayList<NewPlayListResultsBean> resultsBeen) {
        this.mResultsBeen = resultsBeen;

    }



    /**
     * 设置头部的View
     *
     * @param view 想要显示的View
     */
    public void setHeadView(View view) {
        mHeadView = view;
    }

    /**
     * 设置尾部的View
     *
     * @param view 想要显示的View
     */
    public void setFooterView(View view) {
        mFooterView = view;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeadView != null && position == 0) {
            return TYPE_HEAD;
        }
        if (mFooterView != null && position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //判断当前是否应该设置头布局
        if (viewType == TYPE_HEAD) {
            return new HeadViewHolder(mHeadView);
        }
        if (viewType == TYPE_FOOTER) {
            return new FooterViewHolder(mFooterView);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }


    class HeadViewHolder extends RecyclerView.ViewHolder {

        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_number;
        TextView tv_name;
        TextView tv_album;
        ImageView iv_palystatus;


        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_number = (TextView) itemView.findViewById(R.id.tv_number);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_album = (TextView) itemView.findViewById(R.id.tv_album);
            iv_palystatus = (ImageView) itemView.findViewById(R.id.iv_palystatus);
        }
    }


    //记录上一次操作的bean，再次点击，修改该bean播放状态，并且重写赋值
    NewPlayListResultsBean mLastBean;

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        //判断当前是否是头布局，如果是头布局，那么直接return
        if (isHead(position)) {
            return;
        }

        if (isFooter(position)) {
            return;
        }

        //这里需要注意，取值应该是从position-1 开始，因为position ==0 已经被mHeadView占用了
        if (mHeadView != null) {
            position = position - 1;
        }
        final NewPlayListResultsBean bean = mResultsBeen.get(position);


        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;


        itemViewHolder.tv_name.setText(bean.getTitle()+"");
        itemViewHolder.tv_album.setText(bean.getArtist() + " - " + bean.getAlbum() +"");

        if(bean.isPlayStatus()){
            itemViewHolder.iv_palystatus.setVisibility(View.VISIBLE);
            itemViewHolder.tv_number.setVisibility(View.INVISIBLE);
        }else{
            itemViewHolder.iv_palystatus.setVisibility(View.INVISIBLE);

            itemViewHolder.tv_number.setVisibility(View.VISIBLE);
            itemViewHolder.tv_number.setText((position + 1)+"");
        }


        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = bean.getFileUrl().getUrl();


//                for (int i = 0; i < mResultsBeen.size(); i++) {
//                    mResultsBeen.get(i).setPlayStatus(false);
//                }
                if(mLastBean != null){
                    mLastBean.setPlayStatus(false);
                }

                bean.setPlayStatus(true);
                notifyDataSetChanged();
                mLastBean = bean;//重新赋值


                //本地广播
                Intent intent = new Intent(Constant.Action.PLAY);
                intent.putExtra(PLAYDATA_KEY,bean);

                //获取广播管理器
                LocalBroadcastManager manager = LocalBroadcastManager.getInstance(holder.itemView.getContext());
                manager.sendBroadcast(intent);


            }
        });


    }


    private boolean isHead(int position) {
        return mHeadView != null && position == 0;
    }

    private boolean isFooter(int position) {
        //getItemCount 获取item的个数
        return mFooterView != null && position == getItemCount() - 1;
    }

    @Override
    public int getItemCount() {
        return mResultsBeen.size() + (mHeadView != null ? 1 : 0) + (mFooterView != null ? 1 : 0);
    }
}
