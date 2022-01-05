package com.suliborski.appvideo.model.dao;

import com.suliborski.appvideo.model.models.Filter;
import com.suliborski.appvideo.model.utils.MySQLHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilterDAO {

    public List<Filter> getAllFilters(){
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select * from filters");
            ResultSet resultSet = ps.executeQuery();
            return handleFiltersResult(resultSet);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Filter getUserFilter(int userId) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select * from filters WHERE id=(select filterId from users WHERE id=?)");
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            return handleFiltersResult(resultSet).get(0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Filter getFilterById(int filterId) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select * from filters WHERE id=?");
            ps.setInt(1, filterId);
            ResultSet resultSet = ps.executeQuery();
            return handleFiltersResult(resultSet).get(0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void setUserFilter(int userId, int filterId) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "UPDATE users SET filterId=? WHERE id=?");
            ps.setInt(1, filterId);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean addFilter(String name, int tagToRemoveId, int minViews, int maxTitleLength) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "INSERT INTO filters (name, tagToRemoveId, minViews, maxTitleLength) VALUES (?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setInt(2, tagToRemoveId);
            ps.setInt(3, minViews);
            ps.setInt(4, maxTitleLength);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean deleteFilter(int id) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "DELETE FROM filters WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    private List<Filter> handleFiltersResult(ResultSet resultSet) throws SQLException {
        List<Filter> filters = new ArrayList<>();
        while(resultSet.next()) {
            filters.add(new Filter(resultSet.getInt("id"), resultSet.getString("name"),
                    resultSet.getInt("tagToRemoveId"), resultSet.getInt("minViews"),
                    resultSet.getInt("maxTitleLength")));
        }
        return filters;
    }
}
