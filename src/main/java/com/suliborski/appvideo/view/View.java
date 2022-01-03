package com.suliborski.appvideo.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

public class View extends JFrame {
    private JPanel rootPanel;

    private JPanel authPanel;
    private JLabel logoLabel;
    private JLabel greetingLabel;
    private JButton loginAuthButton;
    private JButton registerAuthButton;
    private JButton logoutAuthButton;
    private JButton premiumAuthButton;
    private JComboBox filterAuthComboBox;

    private JPanel menuPanel;
    private JButton exploreMenuButton;
    private JButton recentMenuButton;
    private JButton mostPopularMenuButton;
    private JButton myPlaylistsMenuButton;
    private JButton newPlaylistMenuButton;

    private JPanel loginPanel;
    private JTextField loginPanelLoginField;
    private JPasswordField loginPanelPasswordField;
    private JButton loginPanelLoginButton;

    private JPanel registerPanel;
    private JTextField registerPanelNameField;
    private JTextField registerPanelEmailField;
    private JTextField registerPanelSurnameField;
    private JTextField registerPanelUsernameField;
    private JPasswordField registerPanelPasswordField;
    private JPasswordField registerPanelRepeatPasswordField;
    private JTextField registerPanelBirthdayField;
    private JButton registerPanelRegisterButton;

    private JPanel searchPanel;
    private JPanel searchPanelPlaylistPanel;
    private JComboBox searchPanelPlaylistsComboBox;
    private JList searchPanelPlaylistVideosList;
    private JButton searchPanelPrintPlaylistButton;
    private JTextField searchPanelAddPlaylistField;
    private JButton searchPanelAddPlaylistButton;
    private JPanel searchPanelSearchPanel;
    private JLabel searchPanelVideosLabel;
    private JTextField searchPanelSearchField;
    private JButton searchPanelSearchButton;
    private JButton searchPanelClearButton;
    private JPanel searchPanelTagsPanel;
    private JList searchPanelVideosResultList;
    private JList searchPanelAllTagsList;
    private JList searchPanelChosenTagsList;


    private JPanel videoPlayerPanel;
    private JLabel videoPlayerPanelTitleLabel;
    private JLabel videoPlayerVideoImageLabel;
    private JLabel videoPlayerPanelViewsLabel;
    private JButton videoPlayerPanelPlayButton;
    private JList videoPlayerPanelTagsList;
    private JTextField videoPlayerPanelAddTagField;
    private JButton videoPlayerPanelAddTagButton;


    public View() {
        setName("AppVideo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setContentPane(rootPanel);
        setVisible(true);

        premiumAuthButton.setVisible(false);
        filterAuthComboBox.setVisible(false);
        mostPopularMenuButton.setVisible(false);
        registerPanel.setVisible(false);
        searchPanel.setVisible(false);
        videoPlayerPanel.setVisible(false);
        logoutAuthButton.setVisible(false);
    }

    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void displayInformationMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JPanel getAuthPanel() {
        return authPanel;
    }

    public JLabel getLogoLabel() {
        return logoLabel;
    }

    public JLabel getGreetingLabel() {
        return greetingLabel;
    }

    public JButton getLoginAuthButton() {
        return loginAuthButton;
    }

    public JButton getRegisterAuthButton() {
        return registerAuthButton;
    }

    public JButton getLogoutAuthButton() {
        return logoutAuthButton;
    }

    public JButton getPremiumAuthButton() {
        return premiumAuthButton;
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }

    public JButton getExploreMenuButton() {
        return exploreMenuButton;
    }

    public JButton getRecentMenuButton() {
        return recentMenuButton;
    }

    public JButton getMostPopularMenuButton() {
        return mostPopularMenuButton;
    }

    public JButton getMyPlaylistsMenuButton() {
        return myPlaylistsMenuButton;
    }

    public JButton getNewPlaylistMenuButton() {
        return newPlaylistMenuButton;
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public JTextField getLoginPanelLoginField() {
        return loginPanelLoginField;
    }

    public JPasswordField getLoginPanelPasswordField() {
        return loginPanelPasswordField;
    }

    public JButton getLoginPanelLoginButton() {
        return loginPanelLoginButton;
    }

    public JPanel getRegisterPanel() {
        return registerPanel;
    }

    public JTextField getRegisterPanelNameField() {
        return registerPanelNameField;
    }

    public JTextField getRegisterPanelEmailField() {
        return registerPanelEmailField;
    }

    public JTextField getRegisterPanelSurnameField() {
        return registerPanelSurnameField;
    }

    public JTextField getRegisterPanelUsernameField() {
        return registerPanelUsernameField;
    }

    public JPasswordField getRegisterPanelPasswordField() {
        return registerPanelPasswordField;
    }

    public JPasswordField getRegisterPanelRepeatPasswordField() {
        return registerPanelRepeatPasswordField;
    }

    public JTextField getRegisterPanelBirthdayField() {
        return registerPanelBirthdayField;
    }

    public JButton getRegisterPanelRegisterButton() {
        return registerPanelRegisterButton;
    }

    public JPanel getSearchPanel() {
        return searchPanel;
    }

    public JPanel getSearchPanelPlaylistPanel() {
        return searchPanelPlaylistPanel;
    }

    public JComboBox getSearchPanelPlaylistsComboBox() {
        return searchPanelPlaylistsComboBox;
    }

    public JList getSearchPanelPlaylistVideosList() {
        return searchPanelPlaylistVideosList;
    }

    public JTextField getSearchPanelAddPlaylistField() {
        return searchPanelAddPlaylistField;
    }

    public JButton getSearchPanelAddPlaylistButton() {
        return searchPanelAddPlaylistButton;
    }

    public JPanel getSearchPanelSearchPanel() {
        return searchPanelSearchPanel;
    }

    public JTextField getSearchPanelSearchField() {
        return searchPanelSearchField;
    }

    public JButton getSearchPanelSearchButton() {
        return searchPanelSearchButton;
    }

    public JButton getSearchPanelClearButton() {
        return searchPanelClearButton;
    }

    public JPanel getSearchPanelTagsPanel() {
        return searchPanelTagsPanel;
    }

    public JList getSearchPanelVideosResultList() {
        return searchPanelVideosResultList;
    }

    public JList getSearchPanelAllTagsList() {
        return searchPanelAllTagsList;
    }

    public JList getSearchPanelChosenTagsList() {
        return searchPanelChosenTagsList;
    }

    public JPanel getVideoPlayerPanel() {
        return videoPlayerPanel;
    }

    public JLabel getVideoPlayerPanelTitleLabel() {
        return videoPlayerPanelTitleLabel;
    }

    public JLabel getVideoPlayerPanelViewsLabel() {
        return videoPlayerPanelViewsLabel;
    }

    public JButton getVideoPlayerPanelPlayButton() {
        return videoPlayerPanelPlayButton;
    }

    public JList getVideoPlayerPanelTagsList() {
        return videoPlayerPanelTagsList;
    }

    public JTextField getVideoPlayerPanelAddTagField() {
        return videoPlayerPanelAddTagField;
    }

    public JButton getVideoPlayerPanelAddTagButton() {
        return videoPlayerPanelAddTagButton;
    }

    public JLabel getSearchPanelVideosLabel() {
        return searchPanelVideosLabel;
    }

    public JComboBox getFilterAuthComboBox() {
        return filterAuthComboBox;
    }

    public JButton getSearchPanelPrintPlaylistButton() {
        return searchPanelPrintPlaylistButton;
    }

    public JLabel getVideoPlayerVideoImageLabel() {
        return videoPlayerVideoImageLabel;
    }
}
