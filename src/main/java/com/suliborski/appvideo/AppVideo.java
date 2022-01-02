package com.suliborski.appvideo;

import com.suliborski.appvideo.controller.NavigationController;
import com.suliborski.appvideo.controller.UserController;
import com.suliborski.appvideo.model.UserModel;
import com.suliborski.appvideo.viev.View;

public class AppVideo {

    public static void main(String[] args) {
        View view = new View();

        NavigationController navigationController = new NavigationController(view);

        UserModel userModel = new UserModel();
        UserController userController = new UserController(view, userModel);

//        UserModel userModel = new UserModel();
//        UserController userController = new UserController(view, userModel);

        view.setVisible(true);

    }
}
