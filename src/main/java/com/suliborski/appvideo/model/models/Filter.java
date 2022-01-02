package com.suliborski.appvideo.model.models;

import java.util.List;

public class Filter {

    int id;
    String name;
    private int tagToRemoveId;
    private int minViews;
    private int maxTitleLength;

    public Filter(int id, String name, int tagToRemoveId, int minViews, int maxTitleLength) {
        this.id = id;
        this.name = name;
        this.tagToRemoveId = tagToRemoveId;
        this.minViews = minViews;
        this.maxTitleLength = maxTitleLength;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTagToRemoveId() {
        return tagToRemoveId;
    }

    public void setTagToRemoveId(int tagToRemoveId) {
        this.tagToRemoveId = tagToRemoveId;
    }

    public int getMinViews() {
        return minViews;
    }

    public void setMinViews(int minViews) {
        this.minViews = minViews;
    }

    public int getMaxTitleLength() {
        return maxTitleLength;
    }

    public void setMaxTitleLength(int maxTitleLength) {
        this.maxTitleLength = maxTitleLength;
    }
}
