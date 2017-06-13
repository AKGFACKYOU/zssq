package com.example.jack.mymusic.utils;

import android.content.Context;

/**
 * 位置：
 * 作用：
 * 时间：2017/6/7
 */

public class AppConfigUtils {
    static String GUIDE = "guide";

    private AppConfigUtils(){}

    private static AppConfigUtils mConfigUtils;

    public static final AppConfigUtils getInstance(){
        if(mConfigUtils == null){
            mConfigUtils = new AppConfigUtils();
        }
        return mConfigUtils;
    }




    public boolean getGuide(Context context) {
        return SpUtils.getBoolean(context, GUIDE);
    }


    public void setGuide(Context context, boolean guide) {
        SpUtils.putBoolean(context, GUIDE, guide);
    }


}
