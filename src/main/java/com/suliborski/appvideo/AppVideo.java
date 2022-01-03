package com.suliborski.appvideo;

import com.suliborski.appvideo.controller.*;
import com.suliborski.appvideo.model.dao.*;
import com.suliborski.appvideo.view.View;

public class AppVideo {

    public static void main(String[] args) {
        View view = new View();

        UserDAO userModel = new UserDAO();
        VideoDAO videoModel = new VideoDAO();
        TagDAO tagModel = new TagDAO();
        FilterDAO filterModel = new FilterDAO();
        PlaylistDAO playlistModel = new PlaylistDAO();

        VideoPlayerController videoPlayerController = new VideoPlayerController(view, videoModel, tagModel);
        SearchController searchController = new SearchController(view, videoModel, tagModel, filterModel, playlistModel, videoPlayerController);
        NavigationController navigationController = new NavigationController(view, searchController);
        UserController userController = new UserController(view, userModel, filterModel);

        view.setVisible(true);
    }
}
