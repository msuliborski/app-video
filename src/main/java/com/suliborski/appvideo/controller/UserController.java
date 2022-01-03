package com.suliborski.appvideo.controller;

import com.suliborski.appvideo.model.dao.FilterDAO;
import com.suliborski.appvideo.model.dao.UserDAO;
import com.suliborski.appvideo.model.models.Filter;
import com.suliborski.appvideo.model.models.Playlist;
import com.suliborski.appvideo.model.models.User;
import com.suliborski.appvideo.view.View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class UserController {

    public static User loggedUser = null;
    public static Filter activeFilter;

    private final View view;
    private final UserDAO userModel;
    private final FilterDAO filterModel;

    private List<Filter> filters;

    public UserController(View view, UserDAO userModel, FilterDAO filterModel) {
        this.view = view;
        this.userModel = userModel;
        this.filterModel = filterModel;

        filters = filterModel.getAllFilters();

        this.view.getLogoutAuthButton().addActionListener(e -> onLogout());
        this.view.getPremiumAuthButton().addActionListener(e -> onPremium());
        view.getFilterAuthComboBox().addActionListener(e -> {
            filterModel.setUserFilter(loggedUser.getId(), filters.get(view.getFilterAuthComboBox().getSelectedIndex()).getId());
            activeFilter = filterModel.getUserFilter(loggedUser.getId());
            updateInterface();
        });
        view.getFilterAuthComboBox().setModel(new DefaultComboBoxModel(new Vector(filters)));
        this.view.getLoginPanelLoginButton().addActionListener(e -> onLogin());
        this.view.getRegisterPanelRegisterButton().addActionListener(e -> onRegister());

    }

    private void onLogin() {
        loggedUser = userModel.verifyLogin(view.getLoginPanelLoginField().getText(), view.getLoginPanelPasswordField().getText());
        if (loggedUser != null) {
            view.displayInformationMessage("You've been logged in successfully!");
            view.getLoginPanelLoginField().setText("");
            view.getLoginPanelPasswordField().setText("");
            updateInterface();
            NavigationController.showRecentPanel();
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

        if (userModel.getUserByUsername(view.getRegisterPanelUsernameField().getText()) != null) {
            view.displayErrorMessage("Username already exists!"); return; }

        loggedUser = userModel.registerUser(view.getRegisterPanelNameField().getText(), view.getRegisterPanelSurnameField().getText(),
                view.getRegisterPanelEmailField().getText(), view.getRegisterPanelUsernameField().getText(),
                view.getRegisterPanelPasswordField().getText(), view.getRegisterPanelBirthdayField().getText());
        view.displayInformationMessage("You've been registered successfully and automatically logged in!");
        view.getRegisterPanelNameField().setText("");
        view.getRegisterPanelSurnameField().setText("");
        view.getRegisterPanelEmailField().setText("");
        view.getRegisterPanelUsernameField().setText("");
        view.getRegisterPanelPasswordField().setText("");
        view.getRegisterPanelRepeatPasswordField().setText("");
        view.getRegisterPanelBirthdayField().setText("");
        updateInterface();
        NavigationController.showRecentPanel();
    }

    private void onLogout() {
        loggedUser = null;
        updateInterface();
        NavigationController.showLoginPanel();
    }

    private void onPremium() {
        if (loggedUser != null) {
            userModel.setUserPremium(loggedUser.getId(), !loggedUser.isPremium());
            updateInterface();
        }
    }


    private void updateInterface() {
        loggedUser = loggedUser != null ? userModel.getUserById(loggedUser.getId()) : null;
        if (loggedUser != null) {
            activeFilter = filterModel.getUserFilter(loggedUser.getId());
            if(filters.size() >= 1) view.getFilterAuthComboBox().setSelectedIndex(loggedUser.getFilterId()-1);
            view.getGreetingLabel().setText("Hello, " + loggedUser.getName() + " " + loggedUser.getSurname() + "!");
            view.getLogoutAuthButton().setVisible(true);
            view.getLoginAuthButton().setVisible(false);
            view.getRegisterAuthButton().setVisible(false);
            view.getPremiumAuthButton().setVisible(true);
            if (loggedUser.isPremium()) {
                view.getPremiumAuthButton().setText("Cancel Premium");
                view.getFilterAuthComboBox().setVisible(true);
                view.getSearchPanelPrintPlaylistButton().setVisible(true);
                view.getMostPopularMenuButton().setVisible(true);
                if(filters.size() >= 1) view.getFilterAuthComboBox().setSelectedIndex(loggedUser.getFilterId()-1);
            }
            else {
                view.getPremiumAuthButton().setText("Become Premium");
                view.getFilterAuthComboBox().setVisible(false);
                view.getSearchPanelPrintPlaylistButton().setVisible(false);
                view.getMostPopularMenuButton().setVisible(false);
                filterModel.setUserFilter(loggedUser.getId(), 1);
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

}
