package com.suliborski.appvideo;

import com.suliborski.appvideo.controller.*;
import com.suliborski.appvideo.model.dao.FilterDAO;
import com.suliborski.appvideo.model.dao.TagDAO;
import com.suliborski.appvideo.model.dao.UserDAO;
import com.suliborski.appvideo.model.dao.VideoDAO;
import com.suliborski.appvideo.view.View;

public class AppVideo {

    public static void main(String[] args) {
        View view = new View();

        UserDAO userModel = new UserDAO();
        VideoDAO videoModel = new VideoDAO();
        TagDAO tagModel = new TagDAO();
        FilterDAO filterModel = new FilterDAO();

        VideoPlayerController videoPlayerController = new VideoPlayerController(view, videoModel, tagModel);
        VideoListController videoListController = new VideoListController(view, videoModel, tagModel, filterModel, videoPlayerController);
        NavigationController navigationController = new NavigationController(view, videoListController);
        UserController userController = new UserController(view, userModel);
        ExploreController exploreController = new ExploreController(view, videoModel, tagModel, filterModel, videoPlayerController);

        view.setVisible(true);
    }
}
