package com.example.jack.mymusic.fragment.found;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.jack.mymusic.Constant;
import com.example.jack.mymusic.R;
import com.example.jack.mymusic.adapter.BannerAdapter;
import com.example.jack.mymusic.adapter.HomeHeadFooterAdapter;
import com.example.jack.mymusic.bean.Home;
import com.example.jack.mymusic.bean.HomeResponse;
import com.example.jack.mymusic.bean.Result;
import com.example.jack.mymusic.utils.HttpUtils;
import com.google.gson.Gson;
import com.loopj.android.image.SmartImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;


/**
 * 位置：
 * 作用：
 * 时间：2017/6/12
 */

public class RecommendedFragment extends Fragment {
    ArrayList<Result> results = new ArrayList<>();

    @BindView(R.id.rl)
    RecyclerView rl;
    private HomeHeadFooterAdapter homeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommended, null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        homeAdapter = new HomeHeadFooterAdapter(homes);

        rl.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeAdapter = new HomeHeadFooterAdapter(homes);
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_home_header, null);


        View footerView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_home_footer, null);

        homeAdapter.setHeadView(headView);
        homeAdapter.setFooterView(footerView);
        rl.setAdapter(homeAdapter);
        ViewPager vp = (ViewPager) headView.findViewById(R.id.vp);


        bannerAdapter = new BannerAdapter(results);
        vp.setAdapter(bannerAdapter);
        getBanner();
        getHome();
    }


    /**
     * 获取广告数据
     */
    private void getBanner() {

        //https://leancloud.cn:443/1.1/classes/Banner?limit=10&&&&
        String url = "https://leancloud.cn:443/1.1/classes/Banner?limit=6";
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
                Log.i(TAG, "onResponse: " + result);

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String picurl = object.getString("picurl");
                        String desc = object.getString("desc");
                        String createdAt = object.getString("createdAt");
                        String updatedAt = object.getString("updatedAt");
                        String objectId = object.getString("objectId");
                        Result resu = new Result(picurl, desc, createdAt, updatedAt, objectId);


                        SmartImageView smartImageView = new SmartImageView(getActivity());
                        //注意：使用xml的布局可以直接使用 imageView.getLayoutParams()
                        //如果是通过代码new出来的View，不能使用该方法，必须主动创建LayoutParams对象
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        //设置View的参数
                        smartImageView.setLayoutParams(layoutParams);
                        //设置默认图片
                        smartImageView.setImageResource(R.mipmap.ic_launcher);
//                        smartImageViews.add(smartImageView);

                        resu.setSmartImageView(smartImageView);


                        results.add(resu);

                    }


                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bannerAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void getHome(){

        String url = Constant.URL.HOME + "?include=playList%2CplayList.author&";
        OkHttpClient client = new OkHttpClient();
//        Request request = HttpUtils.getBuilder().url(url).get().build();
        Request request = HttpUtils.requestGET(url);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();

                Gson gson = new Gson();
                HomeResponse homeResponse = gson.fromJson(result, HomeResponse.class);
                Log.e(TAG, "onResponse: "+homeResponse);
                //存取有序吗？
                HashMap<String,ArrayList<HomeResponse.ResultsBean.PlayListBean>> hashMap = new HashMap<>();
                //

                //最新音乐
                //11111111111
                //22222222222
                //推荐音乐
                //11111111111
                //22222222222

                for (int i = 0; i < homeResponse.getResults().size(); i++) {

                    HomeResponse.ResultsBean resultsBean = homeResponse.getResults().get(i);
                    HomeResponse.ResultsBean.PlayListBean playListBean = homeResponse.getResults().get(i).getPlayList();

                    String Item = resultsBean.getItem();

                    if(hashMap.containsKey(Item)){
                        ArrayList<HomeResponse.ResultsBean.PlayListBean> resultsBeens = hashMap.get(Item);
                        resultsBeens.add(playListBean);
                    }else{
                        ArrayList<HomeResponse.ResultsBean.PlayListBean> resultsBeens = new ArrayList<HomeResponse.ResultsBean.PlayListBean>();
                        resultsBeens.add(playListBean);
                        hashMap.put(Item,resultsBeens);
                    }
                }

                Log.i(TAG, "onResponse: " + hashMap);
                Set<Map.Entry<String,ArrayList<HomeResponse.ResultsBean.PlayListBean>>> entrySet = hashMap.entrySet();
                for (Map.Entry<String,ArrayList<HomeResponse.ResultsBean.PlayListBean>> entry:entrySet) {
                    String name = entry.getKey();
                    ArrayList<HomeResponse.ResultsBean.PlayListBean> playList = entry.getValue();
                    Home home = new Home();
                    home.setName(name);
                    home.setPlayListBeen(playList);
                    homes.add(home);
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        homeAdapter.notifyDataSetChanged();
                    }
                });
                Log.e(TAG, "onResponse: "+homes);

            }
        });
    }

    private BannerAdapter bannerAdapter;

    ArrayList<Home> homes = new ArrayList<Home>();

}
