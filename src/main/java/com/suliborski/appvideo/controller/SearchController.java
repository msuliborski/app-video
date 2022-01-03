package com.suliborski.appvideo.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.suliborski.appvideo.model.dao.FilterDAO;
import com.suliborski.appvideo.model.dao.PlaylistDAO;
import com.suliborski.appvideo.model.dao.TagDAO;
import com.suliborski.appvideo.model.dao.VideoDAO;
import com.suliborski.appvideo.model.models.Playlist;
import com.suliborski.appvideo.model.models.Tag;
import com.suliborski.appvideo.model.models.Video;
import com.suliborski.appvideo.view.View;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SearchController {

    private final View view;
    private final VideoDAO videoModel;
    private final TagDAO tagModel;
    private final FilterDAO filterModel;
    private final PlaylistDAO playlistModel;
    private final VideoPlayerController videoPlayerController;

    private List<Playlist> playlists = new ArrayList<>();
    private List<Video> playlistVideos = new ArrayList<>();
    private List<Video> foundVideos = new ArrayList<>();
    private List<Tag> availableTags = new ArrayList<>();
    private List<Tag> chosenTags = new ArrayList<>();

    private final MouseListener playVideoListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() == 2) {
                int index = view.getSearchPanelVideosResultList().locationToIndex(evt.getPoint());
                videoPlayerController.playVideo(foundVideos.get(index));
                NavigationController.showVideoPlayerPanel();
            }
        }
    };
    private final MouseListener addVideoToPlaylistListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
            System.out.println("addVideoToPlaylistListener");
            if (playlists.size() >= 1) {
                System.out.println("wieksze");
                if (evt.getClickCount() == 2) {
                    int index = view.getSearchPanelVideosResultList().locationToIndex(evt.getPoint());
                    playlistModel.addVideoToPlaylist(foundVideos.get(index).getId(), ((Playlist) view.getSearchPanelPlaylistsComboBox().getSelectedItem()).getId());
                    playlistVideos = videoModel.getVideosFromPlaylist(((Playlist) view.getSearchPanelPlaylistsComboBox().getSelectedItem()).getId(), UserController.activeFilter);
                    view.getSearchPanelPlaylistVideosList().setListData(new Vector<>(playlistVideos));
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
                playlistModel.removeVideoFromPlaylist(playlistVideos.get(index).getId(), ((Playlist)view.getSearchPanelPlaylistsComboBox().getSelectedItem()).getId());
                playlistVideos = videoModel.getVideosFromPlaylist(((Playlist)view.getSearchPanelPlaylistsComboBox().getSelectedItem()).getId(), UserController.activeFilter);
                view.getSearchPanelPlaylistVideosList().setListData(new Vector<>(playlistVideos));
            }
        }
    };
    private final MouseListener addToChosenTagsListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() == 2) {
                int index = view.getSearchPanelAllTagsList().locationToIndex(evt.getPoint());
                chosenTags.add(availableTags.get(index));
                availableTags.remove(index);
                updateTagsView();
            }
        }
    };
    private final MouseListener removeFromChosenTagsListener = new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() == 2) {
                int index = view.getSearchPanelChosenTagsList().locationToIndex(evt.getPoint());
                availableTags.add(chosenTags.get(index));
                chosenTags.remove(index);
                updateTagsView();
            }
        }
    };

    public SearchController(View view, VideoDAO videoModel, TagDAO tagModel, FilterDAO filterModel, PlaylistDAO playlistModel, VideoPlayerController videoPlayerController) {
        this.view = view;
        this.videoModel = videoModel;
        this.tagModel = tagModel;
        this.filterModel = filterModel;
        this.playlistModel = playlistModel;
        this.videoPlayerController = videoPlayerController;

        view.getSearchPanelPlaylistsComboBox().addActionListener(e -> {
            playlistVideos = videoModel.getVideosFromPlaylist(((Playlist)view.getSearchPanelPlaylistsComboBox().getSelectedItem()).getId(), UserController.activeFilter);
            view.getSearchPanelPlaylistVideosList().setListData(new Vector<>(playlistVideos));
        });
        this.view.getSearchPanelPrintPlaylistButton().addActionListener(e -> onPrintPlaylist());
        this.view.getSearchPanelAddPlaylistButton().addActionListener(e -> onAddPlaylist());
        this.view.getSearchPanelSearchButton().addActionListener(e -> onSearch());
        this.view.getSearchPanelClearButton().addActionListener(e -> onClear());
    }

    private void onPrintPlaylist() {
        if (playlists.size() >= 1) {
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(((Playlist)view.getSearchPanelPlaylistsComboBox().getSelectedItem()).getName() + " Playlist.pdf"));
                document.open();
                document.add(new Paragraph("Playlist: " + ((Playlist)view.getSearchPanelPlaylistsComboBox().getSelectedItem()).getName()));

                com.itextpdf.text.List list = new com.itextpdf.text.List(true, false, 10);
                for (Video video : playlistVideos) {
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

    private void onSearch() {
        foundVideos = videoModel.getVideos(view.getSearchPanelSearchField().getText(), chosenTags, filterModel.getUserFilter(UserController.loggedUser.getId()));
        view.getSearchPanelVideosResultList().setListData(new Vector<>(foundVideos));
    }

    private void onAddPlaylist() {
        if(!view.getSearchPanelAddPlaylistField().getText().equals("")) {
            playlistModel.addPlaylist(view.getSearchPanelAddPlaylistField().getText(), UserController.loggedUser.getId());
            playlists = playlistModel.getUserPlaylists(UserController.loggedUser.getId());
            view.getSearchPanelPlaylistsComboBox().setModel(new DefaultComboBoxModel(new Vector(playlists)));
            if(playlists.size() >= 1) view.getSearchPanelPlaylistsComboBox().setSelectedIndex(0);
            view.getSearchPanelSearchField().setText("");
            view.getSearchPanelAddPlaylistField().setText("");
        } else {
            view.displayErrorMessage("Playlist name cannot be empty!");
        }
    }

    private void onClear() {
        view.getSearchPanelSearchField().setText("");
        availableTags.addAll(chosenTags);
        chosenTags.clear();
        updateTagsView();
        foundVideos.clear();
        view.getSearchPanelVideosResultList().setListData(new Vector<>(foundVideos));
    }


    private void updateTagsView() {
        view.getSearchPanelAllTagsList().setListData(new Vector<>(availableTags));
        view.getSearchPanelChosenTagsList().setListData(new Vector<>(chosenTags));
    }

    public void setExploreMode() {
        view.getSearchPanelPlaylistPanel().setVisible(false);

        view.getSearchPanelSearchPanel().setVisible(true);
        view.getSearchPanelVideosLabel().setText("Find by title");
        view.getSearchPanelSearchField().setText("");
        view.getSearchPanelSearchField().setVisible(true);
        view.getSearchPanelSearchButton().setVisible(true);
        view.getSearchPanelClearButton().setVisible(true);
        view.getSearchPanelVideosResultList().setListData(new Vector<>());
        view.getSearchPanelVideosResultList().removeMouseListener(playVideoListener);
        view.getSearchPanelVideosResultList().removeMouseListener(addVideoToPlaylistListener);
        view.getSearchPanelVideosResultList().addMouseListener(playVideoListener);

        view.getSearchPanelTagsPanel().setVisible(true);
        availableTags = tagModel.getAllTags();
        chosenTags = new ArrayList<>();
        updateTagsView();
        view.getSearchPanelChosenTagsList().removeMouseListener(removeFromChosenTagsListener);
        view.getSearchPanelChosenTagsList().addMouseListener(removeFromChosenTagsListener);
        view.getSearchPanelAllTagsList().removeMouseListener(addToChosenTagsListener);
        view.getSearchPanelAllTagsList().addMouseListener(addToChosenTagsListener);
    }

    public void setRecentVideosMode() {
        view.getSearchPanelPlaylistPanel().setVisible(false);

        view.getSearchPanelSearchPanel().setVisible(true);
        view.getSearchPanelVideosLabel().setText("Recent videos");
        view.getSearchPanelSearchField().setVisible(false);
        view.getSearchPanelSearchButton().setVisible(false);
        view.getSearchPanelClearButton().setVisible(false);
        foundVideos = videoModel.getRecentVideos(UserController.loggedUser.getId(), UserController.activeFilter);
        view.getSearchPanelVideosResultList().setListData(new Vector<>(foundVideos));
        view.getSearchPanelVideosResultList().removeMouseListener(addVideoToPlaylistListener);
        view.getSearchPanelVideosResultList().removeMouseListener(playVideoListener);
        view.getSearchPanelVideosResultList().addMouseListener(playVideoListener);

        view.getSearchPanelTagsPanel().setVisible(false);

    }

    public void setMostPopularVideosMode() {
        view.getSearchPanelPlaylistPanel().setVisible(false);

        view.getSearchPanelSearchPanel().setVisible(true);
        view.getSearchPanelVideosLabel().setText("Popular videos");
        view.getSearchPanelSearchField().setVisible(false);
        view.getSearchPanelSearchButton().setVisible(false);
        view.getSearchPanelClearButton().setVisible(false);
        foundVideos = videoModel.getMostPopularVideos(UserController.activeFilter);
        view.getSearchPanelVideosResultList().setListData(new Vector<>(foundVideos));
        view.getSearchPanelVideosResultList().removeMouseListener(addVideoToPlaylistListener);
        view.getSearchPanelVideosResultList().removeMouseListener(playVideoListener);
        view.getSearchPanelVideosResultList().addMouseListener(playVideoListener);

        view.getSearchPanelTagsPanel().setVisible(false);
    }

    public void setMyPlaylistsMode() {
        view.getSearchPanelPlaylistPanel().setVisible(true);
        playlists = playlistModel.getUserPlaylists(UserController.loggedUser.getId());
        view.getSearchPanelPlaylistsComboBox().setModel(new DefaultComboBoxModel(new Vector(playlists)));
        if(playlists.size() >= 1) view.getSearchPanelPlaylistsComboBox().setSelectedIndex(0);
        view.getSearchPanelPlaylistVideosList().removeMouseListener(playVideoListener);
        view.getSearchPanelPlaylistVideosList().removeMouseListener(removeVideoFromPlaylistListener);
        view.getSearchPanelPlaylistVideosList().addMouseListener(playVideoListener);

        view.getSearchPanelSearchPanel().setVisible(false);

        view.getSearchPanelTagsPanel().setVisible(false);
    }

    public void setNewPlaylistMode() {
        view.getSearchPanelPlaylistPanel().setVisible(true);
        playlists = playlistModel.getUserPlaylists(UserController.loggedUser.getId());
        view.getSearchPanelPlaylistsComboBox().setModel(new DefaultComboBoxModel(new Vector(playlists)));
        if(playlists.size() >= 1) view.getSearchPanelPlaylistsComboBox().setSelectedIndex(0);
        view.getSearchPanelPlaylistVideosList().removeMouseListener(playVideoListener);
        view.getSearchPanelPlaylistVideosList().removeMouseListener(removeVideoFromPlaylistListener);
        view.getSearchPanelPlaylistVideosList().addMouseListener(removeVideoFromPlaylistListener);

        view.getSearchPanelSearchPanel().setVisible(true);
        view.getSearchPanelVideosLabel().setText("Find by title");
        view.getSearchPanelSearchField().setText("");
        view.getSearchPanelSearchField().setVisible(true);
        view.getSearchPanelSearchButton().setVisible(true);
        view.getSearchPanelClearButton().setVisible(true);
        view.getSearchPanelVideosResultList().setListData(new Vector<>());
        view.getSearchPanelVideosResultList().removeMouseListener(playVideoListener);
        view.getSearchPanelVideosResultList().removeMouseListener(addVideoToPlaylistListener);
        view.getSearchPanelVideosResultList().addMouseListener(addVideoToPlaylistListener);
        view.getSearchPanelAddPlaylistField().setText("");

        view.getSearchPanelTagsPanel().setVisible(false);
    }
}
