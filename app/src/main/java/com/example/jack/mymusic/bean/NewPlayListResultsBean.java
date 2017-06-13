package com.example.jack.mymusic.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 位置：
 * 作用：
 * 时间：2017/6/11
 */

public class NewPlayListResultsBean implements Parcelable {
    private FileUrlBean fileUrl;
    private int albumId;
    private String displayName;
    private String objectId;
    private int duration;
    private String artist;
    private int size;
    private String title;
    private int id;
    private String album;


    //播放状态  true 播放   false 未播放
    private boolean playStatus = false;


    protected NewPlayListResultsBean(Parcel in) {
        fileUrl = in.readParcelable(FileUrlBean.class.getClassLoader());
        albumId = in.readInt();
        displayName = in.readString();
        objectId = in.readString();
        duration = in.readInt();
        artist = in.readString();
        size = in.readInt();
        title = in.readString();
        id = in.readInt();
        album = in.readString();
        playStatus = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(fileUrl, flags);
        dest.writeInt(albumId);
        dest.writeString(displayName);
        dest.writeString(objectId);
        dest.writeInt(duration);
        dest.writeString(artist);
        dest.writeInt(size);
        dest.writeString(title);
        dest.writeInt(id);
        dest.writeString(album);
        dest.writeByte((byte) (playStatus ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewPlayListResultsBean> CREATOR = new Creator<NewPlayListResultsBean>() {
        @Override
        public NewPlayListResultsBean createFromParcel(Parcel in) {
            return new NewPlayListResultsBean(in);
        }

        @Override
        public NewPlayListResultsBean[] newArray(int size) {
            return new NewPlayListResultsBean[size];
        }
    };

    public boolean isPlayStatus() {
        return playStatus;
    }

    public void setPlayStatus(boolean playStatus) {
        this.playStatus = playStatus;
    }

    public FileUrlBean getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(FileUrlBean fileUrl) {
        this.fileUrl = fileUrl;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public static class FileUrlBean implements Parcelable {
        /**
         * name : 张惠妹 - Bad Boy.mp3
         * url : http://ac-kCFRDdr9.clouddn.com/ZJwI6Ywp55v0VdGYw71w7KQiB4VCMm8XeIfsDmri.mp3
         * objectId : 59383870a0bb9f00580d452d
         * __type : File
         * provider : qiniu
         */

        private String name;
        private String url;
        private String objectId;
        private String __type;
        private String provider;

        protected FileUrlBean(Parcel in) {
            name = in.readString();
            url = in.readString();
            objectId = in.readString();
            __type = in.readString();
            provider = in.readString();
        }

        public static final Creator<FileUrlBean> CREATOR = new Creator<FileUrlBean>() {
            @Override
            public FileUrlBean createFromParcel(Parcel in) {
                return new FileUrlBean(in);
            }

            @Override
            public FileUrlBean[] newArray(int size) {
                return new FileUrlBean[size];
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(url);
            dest.writeString(objectId);
            dest.writeString(__type);
            dest.writeString(provider);
        }
    }

    @Override
    public String toString() {
        return "NewPlayListResultsBean{" +
                "fileUrl=" + fileUrl +
                ", albumId=" + albumId +
                ", displayName='" + displayName + '\'' +
                ", objectId='" + objectId + '\'' +
                ", duration=" + duration +
                ", artist='" + artist + '\'' +
                ", size=" + size +
                ", title='" + title + '\'' +
                ", id=" + id +
                ", album='" + album + '\'' +
                ", playStatus=" + playStatus +
                '}';
    }
}