package com.suliborski.appvideo.model.models;

public class Video {

    int id;
    private String title;
    private String url;
    private int views;

    public Video(int id, String title, String url, int views) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.views = views;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }
    public String getFullUrl() {
        return "https://www.youtube.com/watch?v=" + url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return title;
    }

}
