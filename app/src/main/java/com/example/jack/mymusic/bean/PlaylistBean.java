package com.example.jack.mymusic.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * 位置：
 * 作用：
 * 时间：2017/6/13
 */

public class PlaylistBean implements Parcelable{
        /**
         * desc :
         * audienceSize : 15
         * name : 故人长绝，却余三月樱
         * picUrl : {"name":"1496942418319.jpg","url":"http://p3.music.126.net/2vHrwmMBxM9zmX070dq-2w==/18656513302029883.jpg","objectId":"59398756ac502e006b1d2f2b","__type":"File","provider":"qiniu"}
         * objectId : 593987565c497d006b68d03a
         * songList : {"__type":"Relation","className":"Song"}
         */

        private String desc;
        private String audienceSize;
        private String name;
        private PlayListResponse.ResultsBean.PicUrlBean picUrl;
        private String objectId;
        private PlayListResponse.ResultsBean.SongListBean songList;

    public PlaylistBean(String desc, String audienceSize, String name, PlayListResponse.ResultsBean.PicUrlBean picUrl, String objectId, PlayListResponse.ResultsBean.SongListBean songList, ArrayList<PlayListResponse.ResultsBean> playListBeen) {
        this.desc = desc;
        this.audienceSize = audienceSize;
        this.name = name;
        this.picUrl = picUrl;
        this.objectId = objectId;
        this.songList = songList;
        this.playListBeen = playListBeen;
    }

    public void setPlayListBeen(ArrayList<PlayListResponse.ResultsBean> playListBeen) {
        this.playListBeen = playListBeen;
    }

    ArrayList<PlayListResponse.ResultsBean> playListBeen = new ArrayList<>();
    public PlaylistBean(Parcel in) {
        desc = in.readString();
        audienceSize = in.readString();
        name = in.readString();
        objectId = in.readString();
    }
    public PlaylistBean() {

    }
    public static final Creator<PlaylistBean> CREATOR = new Creator<PlaylistBean>() {
        @Override
        public PlaylistBean createFromParcel(Parcel in) {
            return new PlaylistBean(in);
        }

        @Override
        public PlaylistBean[] newArray(int size) {
            return new PlaylistBean[size];
        }
    };

    public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getAudienceSize() {
            return audienceSize;
        }

        public void setAudienceSize(String audienceSize) {
            this.audienceSize = audienceSize;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public PlayListResponse.ResultsBean.PicUrlBean getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(PlayListResponse.ResultsBean.PicUrlBean picUrl) {
            this.picUrl = picUrl;
        }

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public PlayListResponse.ResultsBean.SongListBean getSongList() {
            return songList;
        }

        public void setSongList(PlayListResponse.ResultsBean.SongListBean songList) {
            this.songList = songList;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(desc);
        parcel.writeString(audienceSize);
        parcel.writeString(name);
        parcel.writeString(objectId);
    }

    public static class PicUrlBean implements Parcelable{
        public static Creator<PicUrlBean> getCREATOR() {
            return CREATOR;
        }

        /**
             * name : 1496942418319.jpg
             * url : http://p3.music.126.net/2vHrwmMBxM9zmX070dq-2w==/18656513302029883.jpg
             * objectId : 59398756ac502e006b1d2f2b
             * __type : File
             * provider : qiniu
             */

            private String name;
            private String url;
            private String objectId;
            private String __type;
            private String provider;

        public PicUrlBean(String name, String url, String objectId, String __type, String provider) {
            this.name = name;
            this.url = url;
            this.objectId = objectId;
            this.__type = __type;
            this.provider = provider;
        }

        protected PicUrlBean(Parcel in) {
            name = in.readString();
            url = in.readString();
            objectId = in.readString();
            __type = in.readString();
            provider = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(url);
            dest.writeString(objectId);
            dest.writeString(__type);
            dest.writeString(provider);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<PicUrlBean> CREATOR = new Creator<PicUrlBean>() {
            @Override
            public PicUrlBean createFromParcel(Parcel in) {
                return new PicUrlBean(in);
            }

            @Override
            public PicUrlBean[] newArray(int size) {
                return new PicUrlBean[size];
            }
        };

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

            public String getObjectId() {
                return objectId;
            }

            public void setObjectId(String objectId) {
                this.objectId = objectId;
            }

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getProvider() {
                return provider;
            }

            public void setProvider(String provider) {
                this.provider = provider;
            }
        }
    public ArrayList<PlayListResponse.ResultsBean> getPlayListBeen() {
        return playListBeen;
    }

        public static class SongListBean {
            /**
             * __type : Relation
             * className : Song
             */

            private String __type;
            private String className;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
            }
        }

}
