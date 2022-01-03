package com.suliborski.appvideo.controller;

import com.suliborski.appvideo.view.View;

import javax.swing.*;

public class NavigationController {

    private static View view;
    private VideoListController videoListController;

    public NavigationController(View view, VideoListController videoListController) {
        NavigationController.view = view;
        this.videoListController = videoListController;

        view.getRegisterAuthButton().addActionListener(e -> {showPanel(view.getRegisterPanel());});
        view.getLoginAuthButton().addActionListener(e -> {showPanel(view.getLoginPanel());});
        view.getExploreMenuButton().addActionListener(e -> {showPanel(view.getExplorePanel());});
        view.getRecentMenuButton().addActionListener(e -> {showPanel(view.getVideoListPanel()); videoListController.setRecentVideosMode();});
        view.getMostPopularMenuButton().addActionListener(e -> {showPanel(view.getVideoListPanel()); videoListController.setMostPopularVideosMode();});
        view.getMyPlaylistsMenuButton().addActionListener(e -> {showPanel(view.getVideoListPanel()); videoListController.setMyPlaylistsMode();});
        view.getNewPlaylistMenuButton().addActionListener(e -> {showPanel(view.getPlaylistPanel());});
    }

    public static void showPanel(JPanel panel) {
        if (UserController.loggedUser == null && (panel != view.getLoginPanel() && panel != view.getRegisterPanel())) {
            showPanel(view.getLoginPanel());
        } else if (UserController.loggedUser == null || panel != view.getLoginPanel() && panel != view.getRegisterPanel()) {
            hideAllPanels();
            panel.setVisible(true);
        }
    }

    private static void hideAllPanels() {
        view.getRegisterPanel().setVisible(false);
        view.getLoginPanel().setVisible(false);
        view.getExplorePanel().setVisible(false);
        view.getVideoListPanel().setVisible(false);
        view.getPlaylistPanel().setVisible(false);
        view.getVideoPlayerPanel().setVisible(false);
    }
}
