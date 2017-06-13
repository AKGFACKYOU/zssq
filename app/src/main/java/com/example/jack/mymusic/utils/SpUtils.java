package com.example.jack.mymusic.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 位置：
 * 作用：
 * 时间：2017/6/7
 */

public class SpUtils {
    static String SP_NAME = "config";

    /**
     * 使用sp存储一个布尔类型的数据
     * @param context 上下文
     * @param value   值
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.putBoolean(key, value);
        et.commit();
    }

    /**
     * 获取布尔类型的数据
     * @param  key 取指定key的值
     * @return 取到的值, 默认值为true
     */
    public static boolean getBoolean(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        boolean value = sp.getBoolean(key,true);
        return value;
    }


}
