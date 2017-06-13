package com.example.jack.mymusic.utils;

import okhttp3.Request;

/**
 * 位置：
 * 作用：
 * 时间：2017/6/10
 */

public class HttpUtils {

    public static Request.Builder getBuilder() {
        Request.Builder builder = new Request.Builder()
                .addHeader("X-LC-Id", "kCFRDdr9tqej8FRLoqopkuXl-gzGzoHsz")
                .addHeader("X-LC-Key", "bmEeEjcgvKIq0FRaPl8jV2Um")
                .addHeader("Content-Type", "application/json");
        return builder;
    }

    /**
     * 获取一个GET请求
     * @param url 请求地址
     * @return Request
     */
    public static Request requestGET(String url){
        Request request = getBuilder().url(url).get().build();
        return request;
    }



}
