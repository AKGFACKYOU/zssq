package com.example.jack.mymusic.fragment.found;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jack.mymusic.R;
import com.example.jack.mymusic.adapter.BannerAdapter;
import com.example.jack.mymusic.adapter.HomeHeadFooterAdapter;
import com.example.jack.mymusic.adapter.PlayListAdapter;
import com.example.jack.mymusic.adapter.classPlayListAdapter;
import com.example.jack.mymusic.bean.Home;
import com.example.jack.mymusic.bean.HomeResponse;
import com.example.jack.mymusic.bean.Play;
import com.example.jack.mymusic.bean.PlayListResponse;
import com.example.jack.mymusic.bean.PlaylistBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 位置：
 * 作用：
 * 时间：2017/6/12
 */

public class PlayListFragment extends Fragment {
    private static final String URL_KEY = "https://leancloud.cn:443/1.1/classes/PlayList?limit=20";
    private static final String TAG = "PlayListFragment";



private List<Play> mPlays=new ArrayList<>();

    @BindView(R.id.rl_MusicList)
    RecyclerView rlMusicList;

    private classPlayListAdapter playListAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, null);

       ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//
//
//        playListAdapter = new classPlayListAdapter(playListResponse);
//
        rlMusicList.setLayoutManager(new LinearLayoutManager(getActivity()));
        playListAdapter = new classPlayListAdapter(mPlays);
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.item_head_playlist, null);
        playListAdapter.setHeaderView(headView);


        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rlMusicList.setLayoutManager(layoutManager);
        rlMusicList.setAdapter(playListAdapter);


        getMusicList(URL_KEY);
    }

    public void getMusicList(String url) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("X-LC-Id", "kCFRDdr9tqej8FRLoqopkuXl-gzGzoHsz")
                .addHeader("X-LC-Key", "bmEeEjcgvKIq0FRaPl8jV2Um")
                .addHeader("Content-Type", "application/json")
                .url(url)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                PlayListResponse pl = gson.fromJson(result, PlayListResponse.class);
                Log.e(TAG, "这是PlayListResponse的结果 " + pl.getResults().get(1));

                    for(int i=0;i<18;i++) {


                        Play play=new Play(pl.getResults().get(i).getName(),pl.getResults().get(i).getPicUrl().getUrl(),pl.getResults().get(i).getPicUrl().getProvider());

                        Log.e(TAG, "onResponse:输出的结果 "+pl.getResults().get(i).getName()+pl.getResults().get(i).getPicUrl().getProvider()+pl.getResults().get(i).getPicUrl().getUrl());


                        mPlays.add(play);



                    }
                    getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        playListAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }


}
