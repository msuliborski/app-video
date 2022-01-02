package com.suliborski.appvideo.controller;

import com.suliborski.appvideo.viev.View;

import javax.swing.*;

public class NavigationController {

    private static View view;

    public NavigationController(View view) {
        NavigationController.view = view;

        showPanel(view.getLoginPanel());

        NavigationController.view.getRegisterAuthButton().addActionListener(e -> {showPanel(view.getRegisterPanel());});
        NavigationController.view.getLoginAuthButton().addActionListener(e -> {showPanel(view.getLoginPanel());});
        NavigationController.view.getExploreMenuButton().addActionListener(e -> {showPanel(view.getExplorePanel());});
        NavigationController.view.getMyPlaylistsMenuButton().addActionListener(e -> {showPanel(view.getMyPlaylistsPanel());});
        NavigationController.view.getRecentMenuButton().addActionListener(e -> {showPanel(view.getRecentPanel());});
        NavigationController.view.getNewPlaylistMenuButton().addActionListener(e -> {showPanel(view.getNewPlaylistPanel());});
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
        view.getMyPlaylistsPanel().setVisible(false);
        view.getRecentPanel().setVisible(false);
        view.getNewPlaylistPanel().setVisible(false);
    }
}
