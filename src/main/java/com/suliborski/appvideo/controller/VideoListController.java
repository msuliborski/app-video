package com.suliborski.appvideo.controller;

import com.suliborski.appvideo.model.dao.FilterDAO;
import com.suliborski.appvideo.model.dao.TagDAO;
import com.suliborski.appvideo.model.dao.VideoDAO;
import com.suliborski.appvideo.model.models.Tag;
import com.suliborski.appvideo.model.models.Video;
import com.suliborski.appvideo.view.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class VideoListController {

    private final View view;
    private final VideoDAO videoModel;
    private final VideoPlayerController videoPlayerController;

    private String viewMode = "recent";
    private List<Video> videos = new ArrayList<>();

    public VideoListController(View view, VideoDAO videoModel, TagDAO tagModel, FilterDAO filterModel, VideoPlayerController videoPlayerController) {
        this.view = view;
        this.videoModel = videoModel;
        this.videoPlayerController = videoPlayerController;


        //updateInterface();

        addDoubleClickOnResultVideoElement();
    }

    private void addDoubleClickOnResultVideoElement() {
        view.getVideoListPanelVideosList().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = view.getVideoListPanelVideosList().locationToIndex(evt.getPoint());
                    videoPlayerController.playVideo(videos.get(index));
                    NavigationController.showPanel(view.getVideoPlayerPanel());
                }
            }
        });
    }

    private void updateInterface() {

        switch (viewMode) {
            case "recent" :
                view.getVideoListPanelLabel().setText("Recent videos");
                view.getVideoListPanelPlaylistComboBox().setVisible(false);
                break;
            case "popular" :
                view.getVideoListPanelLabel().setText("Most popular videos");
                view.getVideoListPanelPlaylistComboBox().setVisible(false);
                break;
            case "playlists" :
                view.getVideoListPanelLabel().setText("Choose playlist");
                view.getVideoListPanelPlaylistComboBox().setVisible(true);
                break;
        }

        this.view.getVideoListPanelVideosList().setListData(new Vector<>(videos));
    }

    public void setRecentVideosMode() {
        this.viewMode = "recent";
        this.videos = videoModel.getRecentVideos();
        updateInterface();
    }

    public void setMostPopularVideosMode() {
        this.viewMode = "popular";
        this.videos = videoModel.getMostPopularVideos();
        updateInterface();
    }

    public void setMyPlaylistsMode() {
        this.viewMode = "playlists";
        this.videos = new ArrayList<>();
        updateInterface();
    }
}
