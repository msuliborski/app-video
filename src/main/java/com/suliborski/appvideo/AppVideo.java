package com.suliborski.appvideo;

import com.suliborski.appvideo.controller.ExploreController;
import com.suliborski.appvideo.controller.NavigationController;
import com.suliborski.appvideo.controller.UserController;
import com.suliborski.appvideo.model.dao.FilterDAO;
import com.suliborski.appvideo.model.dao.TagDAO;
import com.suliborski.appvideo.model.dao.UserDAO;
import com.suliborski.appvideo.model.dao.VideoDAO;
import com.suliborski.appvideo.viev.View;

public class AppVideo {

    public static void main(String[] args) {
        View view = new View();

        UserDAO userModel = new UserDAO();
        VideoDAO videoModel = new VideoDAO();
        TagDAO tagModel = new TagDAO();
        FilterDAO filterModel = new FilterDAO();

        UserController userController = new UserController(view, userModel);
        NavigationController navigationController = new NavigationController(view);
        ExploreController exploreController = new ExploreController(view, videoModel, tagModel, filterModel);

        view.setVisible(true);
    }
}
