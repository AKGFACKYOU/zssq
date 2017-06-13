package com.example.jack.mymusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jack.mymusic.R;
import com.example.jack.mymusic.fragment.found.PlayListFragment;
import com.example.jack.mymusic.fragment.found.RecommendedFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 位置：
 * 作用：
 * 时间：2017/6/12
 */

public class FoundFragment extends Fragment {
    @BindView(R.id.tl)
    TabLayout tl;

    @BindView(R.id.vp_found)
    ViewPager vp_found;

    ArrayList<FoundItem> foundItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_found, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        foundItems = new ArrayList<>();
        foundItems.add(new FoundItem("个性推荐", new RecommendedFragment()));
        foundItems.add(new FoundItem("歌单",new PlayListFragment()));

        FoundAdapter foundAdapter = new FoundAdapter(getChildFragmentManager());
        vp_found.setAdapter(foundAdapter);
        tl.setupWithViewPager(vp_found);
    }





    class FoundAdapter extends FragmentPagerAdapter {

        public FoundAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return foundItems.get(position).getTitle();
        }

        @Override
        public Fragment getItem(int position) {
            return foundItems.get(position).getF();
        }

        @Override
        public int getCount() {
            return foundItems.size();
        }
    }


    /**
     * 封装每个item的数据
     */
    class FoundItem{
        String title;
        Fragment f;

        public FoundItem(String title, Fragment f) {
            this.title = title;
            this.f = f;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Fragment getF() {
            return f;
        }

        public void setF(Fragment f) {
            this.f = f;
        }
    }
}
