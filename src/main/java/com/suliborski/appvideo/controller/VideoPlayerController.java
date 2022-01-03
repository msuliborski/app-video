package com.suliborski.appvideo.controller;

import com.suliborski.appvideo.model.dao.TagDAO;
import com.suliborski.appvideo.model.dao.VideoDAO;
import com.suliborski.appvideo.model.models.Video;
import com.suliborski.appvideo.view.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URL;
import java.util.Vector;

public class VideoPlayerController {

    private final View view;
    private final VideoDAO videoModel;
    private final TagDAO tagModel;
    public Video playingVideo;


    public VideoPlayerController(View view, VideoDAO videoModel, TagDAO tagModel) {
        this.view = view;
        this.videoModel = videoModel;
        this.tagModel = tagModel;

        this.view.getVideoPlayerPanelPlayButton().addActionListener(e -> onPlayVideo());
        this.view.getVideoPlayerPanelAddTagButton().addActionListener(e -> onAddTag());
    }

    private void onPlayVideo() {
        videoModel.addViewToVideo(playingVideo.getId());
        videoModel.addVideoToUserHistory(playingVideo.getId(), UserController.loggedUser.getId());
        updateInterface();
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(URI.create(playingVideo.getFullUrl()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void onAddTag() {
        String tagName = view.getVideoPlayerPanelAddTagField().getText();
        if (tagName.isEmpty()) {
            return;
        }

        tagModel.addTagToVideo(tagName, playingVideo.getId());
        updateInterface();
    }


    public void playVideo(Video video) {
        playingVideo = video;

        view.getVideoPlayerVideoImageLabel().setIcon(new ImageIcon(getImage("http://i3.ytimg.com/vi/" + playingVideo.getUrl() + "/maxresdefault.jpg").getScaledInstance(320, 180, Image.SCALE_DEFAULT)));
        view.getVideoPlayerVideoImageLabel().setPreferredSize(new Dimension(320, 240));

        updateInterface();
    }

    private void updateInterface() {
        playingVideo = videoModel.getVideoById(playingVideo.getId());
        view.getVideoPlayerPanelTitleLabel().setText(playingVideo.getTitle());
        view.getVideoPlayerPanelViewsLabel().setText("Views: " + playingVideo.getViews());
        view.getVideoPlayerPanelTagsList().setListData(new Vector<>(tagModel.getVideoTags(playingVideo.getId())));
    }

    public static BufferedImage getImage(String imageURL) {
        try { return ImageIO.read(new URL(imageURL));
        } catch (Exception e) {
            return null;
        }
    }
}
