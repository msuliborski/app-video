package com.suliborski.appvideo.model;

import com.suliborski.appvideo.model.dao.*;
import com.suliborski.appvideo.model.models.*;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private final UserDAO userDAO;
    private final VideoDAO videoModel;
    private final TagDAO tagModel;
    private final FilterDAO filterModel;
    private final PlaylistDAO playlistModel;


    private User loggedUser = null;
    private Filter activeFilter = null;

    private final List<Filter> filters = new ArrayList<>();

    private final List<Playlist> playlists = new ArrayList<>();
    private final List<Video> playlistVideos = new ArrayList<>();

    private final List<Video> videos = new ArrayList<>();

    private final List<Tag> tags = new ArrayList<>();
    private final List<Tag> videoTags = new ArrayList<>();
    private final List<Tag> availableTags = new ArrayList<>();
    private final List<Tag> chosenTags = new ArrayList<>();

    public Video playingVideo = null;

    public Model() {
        userDAO = new UserDAO();
        videoModel = new VideoDAO();
        tagModel = new TagDAO();
        filterModel = new FilterDAO();
        playlistModel = new PlaylistDAO();
    }

    public User getLoggedUser() {
        if (loggedUser != null) loggedUser = userDAO.getUserById(loggedUser.getId());
        return loggedUser;
    }

    public void verifyLogin(String username, String password) {
        loggedUser = userDAO.verifyLogin(username, password);
    }

    public void registerUser(String name, String surname, String email, String username, String password, String birthday) {
        userDAO.registerUser(name, surname, email, username, password, birthday);
    }

    public void logout() {
        loggedUser = null;
    }

    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    public void setUserPremium() {
        userDAO.setUserPremium(loggedUser.getId(), !loggedUser.isPremium());
    }

    public List<Tag> getAvailableTags() {
        return availableTags;
    }

    public List<Tag> getChosenTags() {
        return chosenTags;
    }


    public void updateVideosWithSearch(String text) {
        videos.clear();
        videos.addAll(videoModel.getVideosWithSearch(text, chosenTags, activeFilter));
    }
    public void updateVideosWithRecent() {
        videos.clear();
        videos.addAll(videoModel.getRecentVideos(loggedUser.getId(), filterModel.getUserFilter(loggedUser.getId())));
    }
    public void updateVideosWithMostPopular() {
        videos.clear();
        videos.addAll(videoModel.getMostPopularVideos(filterModel.getUserFilter(loggedUser.getId())));
    }
    public void updateVideosClear() {
        videos.clear();
    }
    public List<Video> getVideos() {
        return videos;
    }

    public void updatePlaylists() {
        playlists.clear();
        playlists.addAll(playlistModel.getUserPlaylists(loggedUser.getId()));
    }
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void updatePlaylistVideos(int playlistId) {
        playlistVideos.clear();
        playlistVideos.addAll(videoModel.getVideosFromPlaylist(playlistId, filterModel.getUserFilter(loggedUser.getId())));
    }
    public List<Video> getPlaylistVideos() {
        return playlistVideos;
    }

    public void updateFilters() {
        filters.clear();
        filters.addAll(filterModel.getAllFilters());
    }
    public List<Filter> getFilters() {
        return filters;
    }


    public Video getPlayingVideo() {
        return playingVideo;
    }
    public void setPlayingVideo(Video video) {
        playingVideo = video;
    }

    public void updateTags() {
        tags.clear();
        tags.addAll(tagModel.getAllTags());
    }
    public List<Tag> getTags() {
        return tags;
    }

    public void addTagToVideo(String tagName) {
        tagModel.addTagToVideo(tagName, playingVideo.getId());
    }

    public void updateVideoTags() {
        videoTags.clear();
        videoTags.addAll(tagModel.getVideoTags(playingVideo.getId()));
    }

    public List<Tag> getVideoTags() {
        return videoTags;
    }

    public void setUserFilter(int filterId) {
        filterModel.setUserFilter(loggedUser.getId(), filterId);
        activeFilter = filterModel.getUserFilter(loggedUser.getId());
    }

    public void addViewToVideo() {
        videoModel.addViewToVideo(playingVideo.getId());
    }

    public void addVideoToUserHistory() {
        videoModel.addVideoToUserHistory(playingVideo.getId(), loggedUser.getId());
    }

    public void addVideoToPlaylist(int videoId, int playlistId) {
        playlistModel.addVideoToPlaylist(videoId, playlistId);
    }

    public void removeVideoFromPlaylist(int videoId, int playlistId) {
        playlistModel.removeVideoFromPlaylist(videoId, playlistId);
    }

    public void addPlaylist(String playlistName) {
        playlistModel.addPlaylist(playlistName, loggedUser.getId());
    }
}
