package com.suliborski.appvideo.model.dao;

import com.suliborski.appvideo.model.models.Filter;
import com.suliborski.appvideo.model.models.Tag;
import com.suliborski.appvideo.model.utils.MySQLHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilterDAO {

//    public List<Filter> getAllFilters(){
//        try {
//            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
//                    "select * from filters");
//            ResultSet resultSet = ps.executeQuery();
//            return handleFiltersResult(resultSet);
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            return null;
//        }
//    }

    public Filter getUserFilter(int userId) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select * from filters WHERE id=(select filterId from users WHERE id=?)");
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            return handleFilterResult(resultSet);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private Filter handleFilterResult(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Filter(resultSet.getInt("id"), resultSet.getString("name"),
                    resultSet.getInt("tagToRemoveId"), resultSet.getInt("minViews"),
                    resultSet.getInt("maxTitleLength"));
        }
        return null;
    }
}
