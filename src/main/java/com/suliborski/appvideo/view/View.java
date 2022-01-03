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

    private JPanel explorePanel;
    private JTextField explorePanelSearchField;
    private JButton explorePanelSearchButton;
    private JButton explorePanelClearButton;
    private JPanel explorePanelVideosResultPanel;
    private JList explorePanelVideosResultList;
    private JList explorePanelAllTagsList;
    private JList explorePanelChosenTagsList;


    private JPanel videoListPanel;
    private JList videoListPanelVideosList;
    private JComboBox videoListPanelPlaylistComboBox;
    private JLabel videoListPanelLabel;

    private JPanel playlistPanel;
    private JComboBox playlistPanelPlaylistsComboBox;
    private JList playlistPanelPlaylistsList;
    private JTextField playlistPanelAddPlaylistField;
    private JButton playlistPanelAddPlaylistButton;
    private JTextField playlistPanelSearchField;
    private JButton playlistPanelSearchButton;
    private JButton playlistPanelClearButton;
    private JList playlistPanelVideosResultList;


    private JPanel videoPlayerPanel;
    private JLabel videoPlayerPanelTitleLabel;
    private JLabel videoPlayerPanelViewsLabel;
    private JButton videoPlayerPanelPlayButton;
    private JList videoPlayerPanelTagsList;
    private JTextField videoPlayerPanelAddTagField;
    private JButton videoPlayerPanelAddTagButton;


    public View() {
        setName("AppVideo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setContentPane(rootPanel);
        setVisible(true);

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

    public JButton getMyPlaylistsMenuButton() {
        return myPlaylistsMenuButton;
    }

    public JButton getRecentMenuButton() {
        return recentMenuButton;
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

    public JTextField getExplorePanelSearchField() {
        return explorePanelSearchField;
    }

    public JButton getExplorePanelSearchButton() {
        return explorePanelSearchButton;
    }

    public JButton getExplorePanelClearButton() {
        return explorePanelClearButton;
    }

    public JList getExplorePanelAllTagsList() {
        return explorePanelAllTagsList;
    }

    public JList getExplorePanelChosenTagsList() {
        return explorePanelChosenTagsList;
    }

    public JLabel getLogoLabel() {
        return logoLabel;
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

    public JTextField getRegisterPanelSurnameField() {
        return registerPanelSurnameField;
    }

    public JButton getMostPopularMenuButton() {
        return mostPopularMenuButton;
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

    public JPanel getExplorePanel() {
        return explorePanel;
    }

    public JList getList1() {
        return explorePanelAllTagsList;
    }

    public JList getList2() {
        return explorePanelChosenTagsList;
    }

    public JButton getButton6() {
        return explorePanelSearchButton;
    }

    public JPanel getRecentPanel() {
        return videoListPanel;
    }

    public JPanel getMyPlaylistsPanel() {
        return playlistPanel;
    }

    public JTextField getRegisterPanelEmailField() {
        return registerPanelEmailField;
    }

    public JLabel getGreetingLabel() {
        return greetingLabel;
    }

    public JPanel getExplorePanelVideosResultPanel() {
        return explorePanelVideosResultPanel;
    }

    public JList getExplorePanelVideosResultList() {
        return explorePanelVideosResultList;
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

    public JPanel getVideoListPanel() {
        return videoListPanel;
    }

    public JList getVideoListPanelVideosList() {
        return videoListPanelVideosList;
    }

    public JComboBox getVideoListPanelPlaylistComboBox() {
        return videoListPanelPlaylistComboBox;
    }

    public JLabel getVideoListPanelLabel() {
        return videoListPanelLabel;
    }

    public JPanel getPlaylistPanel() {
        return playlistPanel;
    }

    public JComboBox getPlaylistPanelPlaylistsComboBox() {
        return playlistPanelPlaylistsComboBox;
    }

    public JList getPlaylistPanelPlaylistsList() {
        return playlistPanelPlaylistsList;
    }

    public JTextField getPlaylistPanelAddPlaylistField() {
        return playlistPanelAddPlaylistField;
    }

    public JButton getPlaylistPanelAddPlaylistButton() {
        return playlistPanelAddPlaylistButton;
    }

    public JTextField getPlaylistPanelSearchField() {
        return playlistPanelSearchField;
    }

    public JButton getPlaylistPanelSearchButton() {
        return playlistPanelSearchButton;
    }

    public JButton getPlaylistPanelClearButton() {
        return playlistPanelClearButton;
    }

    public JList getPlaylistPanelVideosResultList() {
        return playlistPanelVideosResultList;
    }
}
