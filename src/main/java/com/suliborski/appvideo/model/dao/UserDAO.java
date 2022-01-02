package com.suliborski.appvideo.model.dao;

import com.suliborski.appvideo.model.utils.MySQLHandler;
import com.suliborski.appvideo.model.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static User verifyLogin(String username, String password){
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select * from users where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
                        rs.getString("email"), rs.getString("username"), rs.getString("password"),
                        rs.getString("birthday"), rs.getBoolean("premium"));
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static User registerUser(String name, String surname, String email, String username, String password, String birthday) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "INSERT INTO users SET name=?, surname=?, email=?, username=?, password=?, birthday=?, premium=false");
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, email);
            ps.setString(4, username);
            ps.setString(5, password);
            ps.setString(6, birthday);
            ps.execute();

            return verifyLogin(username, password);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static User getUserById(int userId){
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select * from users where id=?");
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            return handleUserResult(resultSet);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static User getUserByUsername(String username){
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "select * from users where username=?");
            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();
            return handleUserResult(resultSet);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static boolean setUserPremium(int userId, boolean premium) {
        try {
            PreparedStatement ps = MySQLHandler.getConnection().prepareStatement(
                    "UPDATE users SET premium=? WHERE id=?");
            ps.setBoolean(1, premium);
            ps.setInt(2, userId);
            ps.executeUpdate();

            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }


    private static User handleUserResult(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname"),
                    resultSet.getString("email"), resultSet.getString("username"), resultSet.getString("password"),
                    resultSet.getString("birthday"), resultSet.getBoolean("premium"));
        } else {
            return null;
        }
    }
}
