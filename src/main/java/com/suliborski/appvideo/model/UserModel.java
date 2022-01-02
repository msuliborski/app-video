package com.suliborski.appvideo.model;

import com.suliborski.appvideo.model.dao.UserDAO;
import com.suliborski.appvideo.model.models.User;

public class UserModel {

    UserDAO userDAO = new UserDAO();

    public User verifyLogin(String username, String password){
        return userDAO.verifyLogin(username, password);
    }

    public User registerUser(String name, String surname, String email, String username, String password, String birthday) {
        return userDAO.registerUser(name, surname, email, username, password, birthday);
    }

    public User getUserById(int userId){
        return userDAO.getUserById(userId);
    }

    public User getUserByUsername(String username){
        return userDAO.getUserByUsername(username);
    }

    public boolean setUserPremium(int userId, boolean premium) {
        return userDAO.setUserPremium(userId, premium);
    }
}
