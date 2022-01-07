package com.suliborski.appvideo.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.suliborski.appvideo.model.Model;
import com.suliborski.appvideo.model.models.Playlist;
import com.suliborski.appvideo.model.models.Video;
import com.suliborski.appvideo.view.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URL;
import java.util.Vector;

public class Controller {

    private final View view;
    private final Model model;

    private final MouseListener addVideoToPlaylistListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
            if (model.getPlaylists().size() >= 1) {
                if (evt.getClickCount() == 2) {
                    int index = view.getSearchPanelVideosResultList().locationToIndex(evt.getPoint());
                    Playlist playlist = (Playlist) view.getSearchPanelPlaylistsComboBox().getSelectedItem();
                    model.addVideoToPlaylist(model.getVideos().get(index).getId(), playlist.getId());
                    model.updatePlaylistVideos(playlist.getId());
                    view.getSearchPanelPlaylistVideosList().setListData(new Vector<>(model.getPlaylistVideos()));
                }
            } else {
                view.displayErrorMessage("You have to select a playlist first!");
            }

        }
    };
    private final MouseListener removeVideoFromPlaylistListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() == 2) {
                int index = view.getSearchPanelPlaylistVideosList().locationToIndex(evt.getPoint());
                model.updatePlaylistVideos(((Playlist) view.getSearchPanelPlaylistsComboBox().getSelectedItem()).getId());
                model.removeVideoFromPlaylist(model.getPlaylistVideos().get(index).getId(), ((Playlist)view.getSearchPanelPlaylistsComboBox().getSelectedItem()).getId());
                view.getSearchPanelPlaylistVideosList().setListData(new Vector<>(model.getPlaylistVideos()));
            }
        }
    };
    private final MouseListener playVideoListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() == 2) {
                int index = view.getSearchPanelVideosResultList().locationToIndex(evt.getPoint());
                playVideo(model.getVideos().get(index));
                showVideoPlayerPanel();
            }
        }
    };
    private final MouseListener playVideoFromPlaylistListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() == 2) {
                int index = view.getSearchPanelPlaylistVideosList().locationToIndex(evt.getPoint());
                playVideo(model.getPlaylistVideos().get(index));
                showVideoPlayerPanel();
            }
        }
    };
    private final MouseListener addToChosenTagsListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() == 2) {
                int index = view.getSearchPanelAllTagsList().locationToIndex(evt.getPoint());
                model.getChosenTags().add(model.getAvailableTags().get(index));
                model.getAvailableTags().remove(index);
                updateTagsView();
            }
        }
    };
    private final MouseListener removeFromChosenTagsListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() == 2) {
                int index = view.getSearchPanelChosenTagsList().locationToIndex(evt.getPoint());
                model.getAvailableTags().add(model.getChosenTags().get(index));
                model.getChosenTags().remove(index);
                updateTagsView();
            }
        }
    };


    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;

        // Update model
        model.updateFilters();
        model.updateTags();

        // Auth panel listeners
        this.view.getRegisterAuthButton().addActionListener(e -> showRegisterPanel());
        this.view.getLoginAuthButton().addActionListener(e -> showLoginPanel());
        this.view.getLogoutAuthButton().addActionListener(e -> onLogout());
        this.view.getPremiumAuthButton().addActionListener(e -> onPremium());

        this.view.getFilterAuthComboBox().addActionListener(e -> {
            model.setUserFilter(model.getFilters().get(view.getFilterAuthComboBox().getSelectedIndex()).getId());
            updateAuthInterface();
        });
        this.view.getFilterAuthComboBox().setModel(new DefaultComboBoxModel(new Vector(model.getFilters())));


        // Menu panel listeners
        view.getExploreMenuButton().addActionListener(e -> showExplorePanel());
        view.getRecentMenuButton().addActionListener(e -> showRecentPanel());
        view.getMostPopularMenuButton().addActionListener(e -> showMostPopularPanel());
        view.getMyPlaylistsMenuButton().addActionListener(e -> showMyPlaylistsPanel());
        view.getNewPlaylistMenuButton().addActionListener(e -> showNewPlaylistPanel());

        // Login panel listeners
        this.view.getLoginPanelLoginButton().addActionListener(e -> onLogin());

        // Register panel listeners
        this.view.getRegisterPanelRegisterButton().addActionListener(e -> onRegister());

        // Search panel listeners
        this.view.getSearchPanelSearchButton().addActionListener(e -> onSearch());
        this.view.getSearchPanelClearButton().addActionListener(e -> onClear());
        this.view.getSearchPanelPrintPlaylistButton().addActionListener(e -> onPrintPlaylist());
        this.view.getSearchPanelAddPlaylistButton().addActionListener(e -> onAddPlaylist());
        view.getSearchPanelPlaylistsComboBox().addActionListener(e -> {
            model.updatePlaylistVideos(model.getPlaylists().get(view.getSearchPanelPlaylistsComboBox().getSelectedIndex()).getId());
            view.getSearchPanelPlaylistVideosList().setListData(new Vector<>(model.getPlaylistVideos()));
        });

        // Video player panel listeners
        this.view.getVideoPlayerPanelPlayButton().addActionListener(e -> onPlayVideoInBrowser());
        this.view.getVideoPlayerPanelAddTagButton().addActionListener(e -> onAddTag());
    }

    // Auth methods
    private void onLogin() {
        model.verifyLogin(view.getLoginPanelLoginField().getText(), view.getLoginPanelPasswordField().getText());
        if (model.getLoggedUser() != null) {
            view.displayInformationMessage("You've been logged in successfully!");
            updateAuthInterface();
            showRecentPanel();
        } else {
            view.displayErrorMessage("Wrong username or password!");
        }
    }
    private void onRegister() {
        if (view.getRegisterPanelNameField().getText() == null || view.getRegisterPanelNameField().getText().isEmpty())
            view.displayErrorMessage("Name field is empty!");
        else if (view.getRegisterPanelBirthdayField().getText() == null || view.getRegisterPanelBirthdayField().getText().isEmpty())
            view.displayErrorMessage("Birthday field is empty!");
        else if (view.getRegisterPanelUsernameField().getText() == null || view.getRegisterPanelUsernameField().getText().isEmpty())
            view.displayErrorMessage("Username field is empty!");
        else if (view.getRegisterPanelPasswordField().getText() == null || view.getRegisterPanelPasswordField().getText().isEmpty())
            view.displayErrorMessage("Password field is empty!");
        else if (view.getRegisterPanelRepeatPasswordField().getText() == null || view.getRegisterPanelRepeatPasswordField().getText().isEmpty())
            view.displayErrorMessage("Repeat password field is empty!");
        else if (!view.getRegisterPanelPasswordField().getText().equals(view.getRegisterPanelRepeatPasswordField().getText()))
            view.displayErrorMessage("Passwords do not match!");
        else if (model.getUserByUsername(view.getRegisterPanelUsernameField().getText()) != null)
            view.displayErrorMessage("Username already exists!");
        else {
            model.registerUser(view.getRegisterPanelNameField().getText(), view.getRegisterPanelSurnameField().getText(),
                    view.getRegisterPanelEmailField().getText(), view.getRegisterPanelUsernameField().getText(),
                    view.getRegisterPanelPasswordField().getText(), view.getRegisterPanelBirthdayField().getText());

            model.verifyLogin(view.getRegisterPanelUsernameField().getText(), view.getRegisterPanelPasswordField().getText());
            if (model.getLoggedUser() != null) {
                view.displayInformationMessage("You've been registered successfully and automatically logged in!");
                updateAuthInterface();
                showRecentPanel();
            } else {
                view.displayErrorMessage("Something went wrong!");
            }
        }

    }
    private void onLogout() {
        model.logout();
        updateAuthInterface();
        showLoginPanel();
    }
    private void onPremium() {
        if (model.getLoggedUser() != null) {
            model.setUserPremium();
            updateAuthInterface();
        }
    }
    private void updateAuthInterface() {
        if (model.getLoggedUser() != null) {

            view.getLoginPanelLoginField().setText("");
            view.getLoginPanelPasswordField().setText("");

            view.getRegisterPanelNameField().setText("");
            view.getRegisterPanelSurnameField().setText("");
            view.getRegisterPanelEmailField().setText("");
            view.getRegisterPanelUsernameField().setText("");
            view.getRegisterPanelPasswordField().setText("");
            view.getRegisterPanelRepeatPasswordField().setText("");
            view.getRegisterPanelBirthdayField().setText("");

            if(model.getFilters().size() >= 1) view.getFilterAuthComboBox().setSelectedIndex(model.getLoggedUser().getFilterId()-1);
            view.getGreetingLabel().setText("Hello, " + model.getLoggedUser().getName() + " " + model.getLoggedUser().getSurname() + "!");

            view.getLogoutAuthButton().setVisible(true);
            view.getLoginAuthButton().setVisible(false);
            view.getRegisterAuthButton().setVisible(false);
            view.getPremiumAuthButton().setVisible(true);
            if (model.getLoggedUser().isPremium()) {
                view.getPremiumAuthButton().setText("Cancel Premium");
                view.getFilterAuthComboBox().setVisible(true);
                view.getSearchPanelPrintPlaylistButton().setVisible(true);
                view.getMostPopularMenuButton().setVisible(true);
                if(model.getFilters().size() >= 1) view.getFilterAuthComboBox().setSelectedIndex(model.getLoggedUser().getFilterId()-1);
            }
            else {
                view.getPremiumAuthButton().setText("Become Premium");
                view.getFilterAuthComboBox().setVisible(false);
                view.getSearchPanelPrintPlaylistButton().setVisible(false);
                view.getMostPopularMenuButton().setVisible(false);
                model.setUserFilter(1);
            }
        } else {
            view.getGreetingLabel().setText("Log in to use the app!");
            view.getLogoutAuthButton().setVisible(false);
            view.getFilterAuthComboBox().setVisible(false);
            view.getLoginAuthButton().setVisible(true);
            view.getRegisterAuthButton().setVisible(true);
            view.getPremiumAuthButton().setVisible(false);
            view.getMostPopularMenuButton().setVisible(false);
        }
    }

    // Navigation methods
    public void showRegisterPanel() {
        showPanel(view.getRegisterPanel());
    }
    public void showLoginPanel() {
        showPanel(view.getLoginPanel());
    }
    public void showExplorePanel() {
        if (showPanel(view.getSearchPanel())) {
            view.getSearchPanelPlaylistPanel().setVisible(false);
            model.updateVideosClear();
            setupSearchPanel(playVideoListener, true, "Find by title");
            setupTagsPanel();
        }
    }
    public void showRecentPanel() {
        if (showPanel(view.getSearchPanel())) {
            view.getSearchPanelPlaylistPanel().setVisible(false);
            model.updateVideosWithRecent();
            setupSearchPanel(playVideoListener, false, "Recent videos");
            view.getSearchPanelTagsPanel().setVisible(false);
        }
    }
    public void showMostPopularPanel() {
        if (showPanel(view.getSearchPanel())) {
            view.getSearchPanelPlaylistPanel().setVisible(false);
            model.updateVideosWithMostPopular();
            setupSearchPanel(playVideoListener, false, "Popular videos");
            view.getSearchPanelTagsPanel().setVisible(false);
        }
    }
    public void showMyPlaylistsPanel() {
        if (showPanel(view.getSearchPanel())) {
            setupPlaylistPanel(playVideoFromPlaylistListener, false);
            view.getSearchPanelSearchPanel().setVisible(false);
            view.getSearchPanelTagsPanel().setVisible(false);
        }
    }
    public void showNewPlaylistPanel() {
        if (showPanel(view.getSearchPanel())) {
            setupPlaylistPanel(removeVideoFromPlaylistListener, true);
            model.updateVideosClear();
            setupSearchPanel(addVideoToPlaylistListener, true, "Find by title");
            view.getSearchPanelTagsPanel().setVisible(false);
        }
    }
    public void showVideoPlayerPanel() {
        showPanel(view.getVideoPlayerPanel());
    }
    private boolean showPanel(JPanel panel) {
        if (model.getLoggedUser() == null && (panel != view.getLoginPanel() && panel != view.getRegisterPanel())) {
            showPanel(view.getLoginPanel());
        } else if (model.getLoggedUser() == null || panel != view.getLoginPanel() && panel != view.getRegisterPanel()) {
            hideAllPanels();
            panel.setVisible(true);
            return true;
        }
        return false;
    }
    private void hideAllPanels() {
        view.getRegisterPanel().setVisible(false);
        view.getLoginPanel().setVisible(false);
        view.getSearchPanel().setVisible(false);
        view.getVideoPlayerPanel().setVisible(false);
    }
    private void setupSearchPanel(MouseListener mouseListener, boolean search, String labelText) {
        view.getSearchPanelSearchPanel().setVisible(true);

        view.getSearchPanelVideosLabel().setText(labelText);
        view.getSearchPanelSearchField().setText("");
        view.getSearchPanelAddPlaylistField().setText("");
        view.getSearchPanelSearchField().setVisible(search);
        view.getSearchPanelSearchButton().setVisible(search);
        view.getSearchPanelClearButton().setVisible(search);

        view.getSearchPanelVideosResultList().setListData(new Vector<>(model.getVideos()));
        model.getAvailableTags().clear();
        model.getAvailableTags().addAll(model.getTags());
        model.getChosenTags().clear();

        view.getSearchPanelVideosResultList().removeMouseListener(playVideoListener);
        view.getSearchPanelVideosResultList().removeMouseListener(addVideoToPlaylistListener);
        view.getSearchPanelVideosResultList().addMouseListener(mouseListener);
    }
    private void setupPlaylistPanel(MouseListener mouseListener, boolean showAddPlaylist) {
        view.getSearchPanelPlaylistPanel().setVisible(true);
        view.getSearchPanelAddPlaylistButton().setVisible(showAddPlaylist);
        view.getSearchPanelAddPlaylistField().setVisible(showAddPlaylist);
        model.updatePlaylists();
        view.getSearchPanelPlaylistsComboBox().setModel(new DefaultComboBoxModel(new Vector(model.getPlaylists())));
        if(model.getPlaylists().size() >= 1) view.getSearchPanelPlaylistsComboBox().setSelectedIndex(0);
        view.getSearchPanelPlaylistVideosList().removeMouseListener(playVideoFromPlaylistListener);
        view.getSearchPanelPlaylistVideosList().removeMouseListener(removeVideoFromPlaylistListener);
        view.getSearchPanelPlaylistVideosList().addMouseListener(mouseListener);
    }
    private void setupTagsPanel() {
        view.getSearchPanelTagsPanel().setVisible(true);
        model.getAvailableTags().clear();
        model.getAvailableTags().addAll(model.getTags());
        model.getChosenTags().clear();
        updateTagsView();
        view.getSearchPanelChosenTagsList().removeMouseListener(removeFromChosenTagsListener);
        view.getSearchPanelChosenTagsList().addMouseListener(removeFromChosenTagsListener);
        view.getSearchPanelAllTagsList().removeMouseListener(addToChosenTagsListener);
        view.getSearchPanelAllTagsList().addMouseListener(addToChosenTagsListener);
    }

    // Search panel methods
    private void onSearch() {
        model.updateVideosWithSearch(view.getSearchPanelSearchField().getText());
        view.getSearchPanelVideosResultList().setListData(new Vector<>(model.getVideos()));
    }
    private void onClear() {
        view.getSearchPanelSearchField().setText("");
        model.getAvailableTags().addAll(model.getChosenTags());
        model.getChosenTags().clear();
        updateTagsView();
        model.getVideos().clear();
        view.getSearchPanelVideosResultList().setListData(new Vector<>(model.getVideos()));
    }
    private void onPrintPlaylist() {
        if (model.getPlaylists().size() >= 1) {
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(((Playlist)view.getSearchPanelPlaylistsComboBox().getSelectedItem()).getName() + " playlist.pdf"));
                document.open();
                document.add(new Paragraph("Playlist: " + ((Playlist)view.getSearchPanelPlaylistsComboBox().getSelectedItem()).getName()));

                com.itextpdf.text.List list = new com.itextpdf.text.List(true, false, 10);
                for (Video video : model.getPlaylistVideos()) {
                    list.add(video.getTitle() + " (" + video.getFullUrl() + ")");
                }
                document.add(list);
                document.close();
                view.displayInformationMessage("Playlist printed successfully!");
            } catch (DocumentException | FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            view.displayErrorMessage("You must create and select a playlist first!");
        }
    }
    private void onAddPlaylist() {
        if(!view.getSearchPanelAddPlaylistField().getText().equals("")) {
            model.addPlaylist(view.getSearchPanelAddPlaylistField().getText());
            model.updatePlaylists();
            view.getSearchPanelPlaylistsComboBox().setModel(new DefaultComboBoxModel(new Vector(model.getPlaylists())));
            if(model.getPlaylists().size() >= 1) view.getSearchPanelPlaylistsComboBox().setSelectedIndex(model.getPlaylists().size()-1);
            view.getSearchPanelSearchField().setText("");
            view.getSearchPanelAddPlaylistField().setText("");
            view.displayInformationMessage("Playlist added successfully!");
        } else {
            view.displayErrorMessage("Playlist name cannot be empty!");
        }
    }
    private void updateTagsView() {
        view.getSearchPanelAllTagsList().setListData(new Vector<>(model.getAvailableTags()));
        view.getSearchPanelChosenTagsList().setListData(new Vector<>(model.getChosenTags()));
    }

    // Video player methods
    public void playVideo(Video video) {
        model.setPlayingVideo(video);
        model.updateVideoTags();
        updateVideoPlayerView();
    }
    private void onPlayVideoInBrowser() {
        model.addViewToVideo();
        model.addVideoToUserHistory();
        updateVideoPlayerView();
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(URI.create(model.getPlayingVideo().getFullUrl()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void onAddTag() {
        if(!view.getVideoPlayerPanelAddTagField().getText().equals("")) {
            model.addTagToVideo(view.getVideoPlayerPanelAddTagField().getText());
            view.getVideoPlayerPanelAddTagField().setText("");
            model.updateTags();
            model.updateVideoTags();
            updateVideoPlayerView();
            view.displayInformationMessage("Tag added successfully!");
        } else {
            view.displayErrorMessage("Tag name cannot be empty!");
        }
    }
    private BufferedImage getImage(String imageURL) {
        try { return ImageIO.read(new URL(imageURL));
        } catch (Exception e) {
            return null;
        }
    }
    private void updateVideoPlayerView() {
        view.getVideoPlayerPanelTitleLabel().setText(model.getPlayingVideo().getTitle());
        view.getVideoPlayerVideoImageLabel().setIcon(new ImageIcon(getImage("http://i3.ytimg.com/vi/" + model.getPlayingVideo().getUrl() + "/maxresdefault.jpg").getScaledInstance(320, 180, Image.SCALE_DEFAULT)));
        view.getVideoPlayerVideoImageLabel().setPreferredSize(new Dimension(320, 240));
        view.getVideoPlayerPanelViewsLabel().setText("Views: " + model.getPlayingVideo().getViews());
        view.getVideoPlayerPanelTagsList().setListData(new Vector<>(model.getVideoTags()));
    }
}
