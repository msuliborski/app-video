package com.suliborski.appvideo.model;

import com.suliborski.appvideo.model.dao.UserDAO;
import com.suliborski.appvideo.model.models.User;

public class UserModel {

    public User verifyLogin(String email, String password) {
        return UserDAO.verifyLogin(email, password);
    }

    public User register(String name, String surname, String email, String username, String password, String birthday) {
        return UserDAO.registerUser(name, surname, email, username, password, birthday);
    }
}
