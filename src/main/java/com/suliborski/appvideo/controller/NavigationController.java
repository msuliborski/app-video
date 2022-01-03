package com.suliborski.appvideo.controller;

import com.suliborski.appvideo.view.View;

import javax.swing.*;

public class NavigationController {

    private static View view;
    private static SearchController searchController;

    public NavigationController(View view, SearchController searchController) {
        NavigationController.view = view;
        NavigationController.searchController = searchController;

        view.getRegisterAuthButton().addActionListener(e -> showRegisterPanel());
        view.getLoginAuthButton().addActionListener(e -> showLoginPanel());
        view.getExploreMenuButton().addActionListener(e -> showExplorePanel());
        view.getRecentMenuButton().addActionListener(e -> showRecentPanel());
        view.getMostPopularMenuButton().addActionListener(e -> showMostPopularPanel());
        view.getMyPlaylistsMenuButton().addActionListener(e -> showMyPlaylistsPanel());
        view.getNewPlaylistMenuButton().addActionListener(e -> showNewPlaylistPanel());
    }

    public static void showRegisterPanel() {
        showPanel(view.getRegisterPanel());
    }
    public static void showLoginPanel() {
        showPanel(view.getLoginPanel());
    }
    public static void showExplorePanel() {
        if (showPanel(view.getSearchPanel()))
            searchController.setExploreMode();
    }
    public static void showRecentPanel() {
        if (showPanel(view.getSearchPanel()))
            searchController.setRecentVideosMode();
    }
    public static void showMostPopularPanel() {
        if (showPanel(view.getSearchPanel()))
            searchController.setMostPopularVideosMode();
    }
    public static void showMyPlaylistsPanel() {
        if (showPanel(view.getSearchPanel()))
            searchController.setMyPlaylistsMode();
    }
    public static void showNewPlaylistPanel() {
        if (showPanel(view.getSearchPanel()))
            searchController.setNewPlaylistMode();
    }
    public static void showVideoPlayerPanel() {
        showPanel(view.getVideoPlayerPanel());
    }

    private static boolean showPanel(JPanel panel) {
        if (UserController.loggedUser == null && (panel != view.getLoginPanel() && panel != view.getRegisterPanel())) {
            showPanel(view.getLoginPanel());
        } else if (UserController.loggedUser == null || panel != view.getLoginPanel() && panel != view.getRegisterPanel()) {
            hideAllPanels();
            panel.setVisible(true);
            return true;
        }
        return false;
    }

    private static void hideAllPanels() {
        view.getRegisterPanel().setVisible(false);
        view.getLoginPanel().setVisible(false);
        view.getSearchPanel().setVisible(false);
        view.getVideoPlayerPanel().setVisible(false);
    }

}
