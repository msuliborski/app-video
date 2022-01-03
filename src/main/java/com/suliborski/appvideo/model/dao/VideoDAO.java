package com.suliborski.appvideo.model.dao;

import com.mysql.cj.util.StringUtils;
import com.suliborski.appvideo.model.models.Filter;
import com.suliborski.appvideo.model.models.Tag;
import com.suliborski.appvideo.model.models.User;
import com.suliborski.appvideo.model.models.Video;
import com.suliborski.appvideo.model.utils.MySQLHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VideoDAO {

    private String getFilterString(Filter filter) {

        StringBuilder filterStringBuilder = new StringBuilder();
        if (filter.getTagToRemoveId() != 0) {
            filterStringBuilder.append("id not in (select distinct videoId from tagsToVideos where tagId in (").append(filter.getTagToRemoveId()).append(")) and ");
        }
        filterStringBuilder.append(" views >= ").append(filter.getMinViews()).append(" AND ").append(" length(title) <= ").append(filter.getMaxTitleLength());
        return filterStringBuilder.toString();
    }

    public List<Video> getVideos(String title, List<Tag> tags, Filter filter) {
        try {
            StringBuilder tagsToInclude = new StringBuilder();
            if (tags.size() != 0) {
                tagsToInclude.append("id in (SELECT DISTINCT videoId FROM tagsToVideos ");
                for (Tag tag : tags) {
                    tagsToInclude.append("INNER JOIN (select videoId from tagsToVideos where tagId = ")
                            .append(tag.getId())
                            .append(") as x")
                            .append(new Random().nextInt(9999))
                            .append(1)
                            .append(" USING(videoId) ");
                }
                tagsToInclude.append(") AND ");
            }

            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                        "select * from videos WHERE " +
                                "LOWER(title) LIKE ? AND " +
                                tagsToInclude +
                                getFilterString(filter));
            ps.setString(1, "%" + title.toLowerCase() + "%");
            ResultSet resultSet = ps.executeQuery();
            return handleVideosResult(resultSet);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Video getVideoById(int id) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select * from videos where id = ?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            return handleVideosResult(resultSet).get(0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Video> getRecentVideos(int userId, Filter filter) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select videos.* from history join videos on videos.id = history.videoId WHERE userId=? and " + getFilterString(filter) + " ORDER BY history.id DESC LIMIT 5");
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            return handleVideosResult(resultSet);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }



    public List<Video> getMostPopularVideos(Filter filter) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select * from videos WHERE " + getFilterString(filter) + " ORDER BY views DESC LIMIT 10");
            System.out.println(ps.toString());
            ResultSet resultSet = ps.executeQuery();
            return handleVideosResult(resultSet);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Video> getVideosFromPlaylist(int playlistId, Filter filter) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select * from videos where id in (select videoId from videosToPlaylists where playlistId = ?) and " + getFilterString(filter));
            ps.setInt(1, playlistId);
            System.out.println(ps.toString());
            ResultSet resultSet = ps.executeQuery();
            return handleVideosResult(resultSet);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private List<Video> handleVideosResult(ResultSet resultSet) throws SQLException {
        List<Video> videos = new ArrayList<>();
        while(resultSet.next()) {
            videos.add(new Video(resultSet.getInt("id"), resultSet.getString("title"),
                    resultSet.getString("url"), resultSet.getInt("views")));
        }
        return videos;
    }

    public boolean addViewToVideo(int videoId) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "UPDATE videos SET views = views + 1 WHERE id = ?");
            ps.setInt(1, videoId);
            ps.execute();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }


    public void addVideoToUserHistory(int videoId, int userId) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "INSERT INTO history (videoId, userId) VALUES (?, ?)");
            ps.setInt(1, videoId);
            ps.setInt(2, userId);
            ps.execute();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean addVideo(String title, String url) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "INSERT INTO videos (title, url) VALUES (?, ?)");
            ps.setString(1, title);
            ps.setString(2, url);
            ps.execute();

            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

}
