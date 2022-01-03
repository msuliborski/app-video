package com.suliborski.appvideo.model.dao;

import com.suliborski.appvideo.model.models.Tag;
import com.suliborski.appvideo.model.models.User;
import com.suliborski.appvideo.model.utils.MySQLHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDAO {

    public List<Tag> getAllTags(){
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select * from tags");
            ResultSet resultSet = ps.executeQuery();
            return handleTagsResult(resultSet);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Tag getTagById(int id) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select * from tags where id=?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            return handleTagsResult(resultSet).get(0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public List<Tag> getVideoTags(int videoId) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select * from tags where id in (select tagId from tagsToVideos where videoId=?)");
            ps.setInt(1, videoId);
            ResultSet resultSet = ps.executeQuery();
            return handleTagsResult(resultSet);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    public void addTagToVideo(String name, int videoId) {
        try {

            // check if tag exists
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select * from tags where name=?");
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                int tagId = resultSet.getInt("id");

                // check if tag is already assigned to video
                ps = MySQLHandler.getConnection().prepareStatement(
                        "select * from tagsToVideos where tagId=? and videoId=?");
                ps.setInt(1, tagId);
                ps.setInt(2, videoId);
                resultSet = ps.executeQuery();
                if(!resultSet.next()) {
                    // add tag to video
                    ps = MySQLHandler.getConnection().prepareStatement(
                            "INSERT INTO tagsToVideos (tagId, videoId) VALUES (?, ?)");
                    ps.setInt(1, tagId);
                    ps.setInt(2, videoId);
                    ps.executeUpdate();
                } else {
                    System.out.println("Tag is already assigned to video");
                }
            } else {
                // add tag to database
                ps = MySQLHandler.getConnection().prepareStatement(
                        "INSERT INTO tags (name) VALUES (?)");
                ps.setString(1, name);
                ps.executeUpdate();

                // get tag id
                ps = MySQLHandler.getConnection().prepareStatement(
                        "select * from tags where name=?");
                ps.setString(1, name);
                resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    int tagId = resultSet.getInt("id");

                    // add tag to video
                    ps = MySQLHandler.getConnection().prepareStatement(
                            "INSERT INTO tagsToVideos (tagId, videoId) VALUES (?, ?)");
                    ps.setInt(1, tagId);
                    ps.setInt(2, videoId);
                    ps.executeUpdate();
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean addTag(String name) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "INSERT INTO tags (name) VALUES (?)");
            ps.setString(1, name);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean removeTag(int tagId) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "DELETE FROM tags WHERE id=?");
            ps.setInt(1, tagId);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    private List<Tag> handleTagsResult(ResultSet resultSet) throws SQLException {
        List<Tag> tags = new ArrayList<>();
        while(resultSet.next()) {
            tags.add(new Tag(resultSet.getInt("id"), resultSet.getString("name")));
        }
        return tags;
    }
}
