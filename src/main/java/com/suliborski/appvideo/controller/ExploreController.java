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

public class ExploreController {

    private final View view;
    private final VideoDAO videoModel;
    private final TagDAO tagModel;
    private final FilterDAO filterModel;
    private final VideoPlayerController videoPlayerController;

    private List<Tag> availableTags;
    private List<Tag> chosenTags;
    private List<Video> foundVideos;

    public ExploreController(View view, VideoDAO videoModel, TagDAO tagModel, FilterDAO filterModel, VideoPlayerController videoPlayerController) {
        this.view = view;
        this.videoModel = videoModel;
        this.tagModel = tagModel;
        this.filterModel = filterModel;
        this.videoPlayerController = videoPlayerController;

        availableTags = tagModel.getAllTags();
        chosenTags = new ArrayList<>();
        foundVideos = new ArrayList<>();
        updateInterface();

        addDoubleClickOnAllTagsElement();
        addDoubleClickOnChosenTagsElement();
        addDoubleClickOnResultVideoElement();
        this.view.getExplorePanelSearchButton().addActionListener(e -> onSearch());
        this.view.getExplorePanelClearButton().addActionListener(e -> onClear());
    }

    private void onSearch() {
        foundVideos = videoModel.getVideos(view.getExplorePanelSearchField().getText(), chosenTags, filterModel.getUserFilter(UserController.loggedUser.getId()));
        view.getExplorePanelVideosResultList().setListData(new Vector<>(foundVideos));
    }

    private void onClear() {
        view.getExplorePanelSearchField().setText("");
        availableTags.addAll(chosenTags);
        chosenTags.clear();
        foundVideos.clear();
        updateInterface();
    }

    private void addDoubleClickOnAllTagsElement() {
        view.getExplorePanelAllTagsList().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = view.getExplorePanelAllTagsList().locationToIndex(evt.getPoint());
                    chosenTags.add(availableTags.get(index));
                    availableTags.remove(index);
                    updateInterface();
                }
            }
        });
    }

    private void addDoubleClickOnChosenTagsElement() {
        view.getExplorePanelChosenTagsList().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = view.getExplorePanelChosenTagsList().locationToIndex(evt.getPoint());
                    availableTags.add(chosenTags.get(index));
                    chosenTags.remove(index);
                    updateInterface();
                }
            }
        });
    }

    private void addDoubleClickOnResultVideoElement() {
        view.getExplorePanelVideosResultList().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = view.getExplorePanelVideosResultList().locationToIndex(evt.getPoint());
                    videoPlayerController.playVideo(foundVideos.get(index));
                    NavigationController.showPanel(view.getVideoPlayerPanel());
                }
            }
        });
    }

    private void updateInterface() {
        view.getExplorePanelAllTagsList().setListData(new Vector<>(availableTags));
        view.getExplorePanelChosenTagsList().setListData(new Vector<>(chosenTags));
        view.getExplorePanelVideosResultList().setListData(new Vector<>(foundVideos));
    }

}
