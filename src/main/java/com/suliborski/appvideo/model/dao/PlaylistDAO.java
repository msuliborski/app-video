package com.suliborski.appvideo.model.dao;

import com.suliborski.appvideo.model.models.Playlist;
import com.suliborski.appvideo.model.utils.MySQLHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    public Playlist getPlaylistById(int id) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select * from playlists where id=?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            return handlePlaylistsResult(resultSet).get(0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Playlist> getUserPlaylists(int userId) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select * from playlists where userId=?");
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            return handlePlaylistsResult(resultSet);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }


    private List<Playlist> handlePlaylistsResult(ResultSet resultSet) throws SQLException {
        List<Playlist> playlists = new ArrayList<>();
        while(resultSet.next()) {
            playlists.add(new Playlist(resultSet.getInt("id"), resultSet.getString("name"),
                    resultSet.getInt("userId")));
        }
        return playlists;
    }

    public boolean addVideoToPlaylist(int videoId, int playlistId) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select * from videosToPlaylists where videoId=? and playlistId=?");
            ps.setInt(1, videoId);
            ps.setInt(2, playlistId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return false;
            } else {
                ps = MySQLHandler.getConnection().prepareStatement(
                        "INSERT INTO videosToPlaylists (videoId, playlistId) VALUES (?, ?)");
                ps.setInt(1, videoId);
                ps.setInt(2, playlistId);
                ps.executeUpdate();
            }
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean removeVideoFromPlaylist(int videoId, int playlistId) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "DELETE FROM videosToPlaylists WHERE videoId=? AND playlistId=?");
            ps.setInt(1, videoId);
            ps.setInt(2, playlistId);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean addPlaylist(String name, int userId) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "INSERT INTO playlists (name, userId) VALUES (?, ?)");
            ps.setString(1, name);
            ps.setInt(2, userId);
            ps.executeUpdate();

            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public boolean deletePlaylist(int playlistId) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "DELETE FROM playlists WHERE id=?");
            ps.setInt(1, playlistId);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
