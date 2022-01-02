package com.suliborski.appvideo.viev;

import com.toedter.calendar.JCalendar;

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
    private JButton button4;
    private JList explorePanelAllTagsList;
    private JList explorePanelChosenTagsList;
    private JButton explorePanelSearchButton;

    private JPanel recentPanel;
    private JPanel myPlaylistsPanel;
    private JPanel newPlaylistPanel;
    private JLabel logoLabel;
    private JTextField explorePanelSearchField;
    private JButton explorePanelClearButton;


    public View() {
        setName("AppVideo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setContentPane(rootPanel);
        setVisible(true);

        JCalendar calendar;

        calendar = new JCalendar();


        //panel.add(calendar);

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

    public JButton getButton4() {
        return button4;
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
}
