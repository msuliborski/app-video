package com.suliborski.appvideo.controller;

import com.suliborski.appvideo.model.UserModel;
import com.suliborski.appvideo.model.dao.UserDAO;
import com.suliborski.appvideo.model.models.User;
import com.suliborski.appvideo.viev.View;

public class UserController {

    public static User loggedUser = null;

    private View view;
    private UserModel userModel;

    public UserController(View view, UserModel userModel) {
        this.view = view;
        this.userModel = userModel;

        this.view.getLoginPanelLoginButton().addActionListener(e -> onLogin());
        this.view.getRegisterPanelRegisterButton().addActionListener(e -> onRegister());
        this.view.getLogoutAuthButton().addActionListener(e -> onLogout());
        this.view.getPremiumAuthButton().addActionListener(e -> onPremium());

    }

    private void onLogin() {
        loggedUser = userModel.verifyLogin(view.getLoginPanelLoginField().getText(), view.getLoginPanelPasswordField().getText());
        if (loggedUser != null) {
            updateGreetingLabel();
            updatePremiumButton();
            view.displayInformationMessage("You've been logged in successfully!");
            NavigationController.showPanel(view.getRecentPanel());
        } else {
            view.displayErrorMessage("Wrong username or password!");
        }
    }

    private void onRegister() {

        if (view.getRegisterPanelNameField().getText() == null || view.getRegisterPanelNameField().getText().isEmpty()) {
            view.displayErrorMessage("Name field is empty!"); return; }

        if (view.getRegisterPanelBirthdayField().getText() == null || view.getRegisterPanelBirthdayField().getText().isEmpty()) {
            view.displayErrorMessage("Birthday field is empty!"); return; }

        if (view.getRegisterPanelUsernameField().getText() == null || view.getRegisterPanelUsernameField().getText().isEmpty()) {
            view.displayErrorMessage("Username field is empty!"); return; }

        if (view.getRegisterPanelPasswordField().getText() == null || view.getRegisterPanelPasswordField().getText().isEmpty()) {
            view.displayErrorMessage("Password field is empty!"); return; }

        if (view.getRegisterPanelRepeatPasswordField().getText() == null || view.getRegisterPanelRepeatPasswordField().getText().isEmpty()) {
            view.displayErrorMessage("Repeat password field is empty!"); return; }

        if (!view.getRegisterPanelPasswordField().getText().equals(view.getRegisterPanelRepeatPasswordField().getText())) {
            view.displayErrorMessage("Passwords do not match!"); return; }

        if (UserDAO.getUserByUsername(view.getRegisterPanelUsernameField().getText()) != null) {
            view.displayErrorMessage("Username already exists!"); return; }

        loggedUser = UserDAO.registerUser(view.getRegisterPanelNameField().getText(), view.getRegisterPanelSurnameField().getText(),
                view.getRegisterPanelEmailField().getText(), view.getRegisterPanelUsernameField().getText(),
                view.getRegisterPanelPasswordField().getText(), view.getRegisterPanelBirthdayField().getText());
        view.displayInformationMessage("You've been registered successfully and automatically logged in!");
        updateGreetingLabel();
        updatePremiumButton();
        NavigationController.showPanel(view.getRecentPanel());
    }

    private void onLogout() {
        loggedUser = null;
        updateGreetingLabel();
        NavigationController.showPanel(view.getLoginPanel());
    }

    private void onPremium() {
        if (loggedUser != null) {
            UserDAO.setUserPremium(loggedUser.getId(), !loggedUser.isPremium());
            updateLoggedUser();
            updatePremiumButton();
        }
    }

    private void updateLoggedUser() {
        loggedUser = UserDAO.getUserById(loggedUser.getId());
    }

    private void updatePremiumButton() {
        if (loggedUser != null) {
            if (loggedUser.isPremium()) view.getPremiumAuthButton().setText("Cancel Premium");
            else view.getPremiumAuthButton().setText("Become Premium");
        }
    }

    private void updateGreetingLabel() {
        if (loggedUser != null) view.getGreetingLabel().setText("Hello, " + loggedUser.getName() + " " + loggedUser.getSurname() + "!");
        else view.getGreetingLabel().setText("Log in to use the app!");
    }


}
