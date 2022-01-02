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
    public static boolean addTag(String name) {
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

    public static boolean removeTag(int tagId) {
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
