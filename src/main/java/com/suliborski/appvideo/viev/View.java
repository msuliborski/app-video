package com.suliborski.appvideo.viev;

import tds.video.VideoWeb;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

public class View extends JFrame {
    private JPanel rootPanel;

    private JPanel authPanel;
    private JLabel greetingLabel;
    private JButton loginAuthButton;
    private JButton registerAuthButton;
    private JButton logoutAuthButton;
    private JButton premiumAuthButton;

    private JPanel menuPanel;
    private JButton exploreMenuButton;
    private JButton myPlaylistsMenuButton;
    private JButton recentMenuButton;
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

    private JPanel recentPanel;
    private JPanel myPlaylistsPanel;
    private JPanel newPlaylistPanel;
    private JLabel logoLabel;


    public View() {
        setName("AppVideo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setContentPane(rootPanel);
        setVisible(true);
////
//        VideoWeb videoWeb = new VideoWeb();
////        JCalendar calendar = new JCalendar();
////        explorePanelVideosResultPanel.add(videoWeb);
//////        videoWeb.playVideo("https://www.youtube.com/watch?v=xbev7gEdnNA");
////        //jcalendar
////        //video playback
    }

    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void displayInformationMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public void playVideo(int videoId) {
//        EventQueue.invokeLater(() -> {
//            try {
//                videoWeb = new VideoWeb();
//                PruebaVideoUI frame = new PruebaVideoUI();
//                frame.setVisible(true);
//                videoWeb.playVideo("https://www.youtube.com/watch?v=1VDaOPzQ1sk ");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
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
        return recentPanel;
    }

    public JPanel getMyPlaylistsPanel() {
        return myPlaylistsPanel;
    }

    public JPanel getNewPlaylistPanel() {
        return newPlaylistPanel;
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
}
