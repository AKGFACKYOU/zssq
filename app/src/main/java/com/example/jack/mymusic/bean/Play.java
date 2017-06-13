package com.example.jack.mymusic.bean;

/**
 * 位置：
 * 作用：
 * 时间：2017/6/13
 */

public class Play {

    private  String  name;
    private String url;
    private String provider;

    public Play(String name, String url, String provider) {
        this.name = name;
        this.url = url;
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
