package com.dao;

import com.utils.ConnectionUtil;
import com.models.User;
import com.utils.CustomLogger;
import com.utils.LogLevel;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoPostgres implements UserDAO {
    private String logstring;

    @Override
    public User createUser(User user) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "insert into app_users (first_name, last_name, username, password, role_id) values(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRoleID());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedID = rs.getInt("id");

            user.setId(generatedID);
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try(Connection connection = ConnectionUtil.getConnection()){
            logstring="Attempting to get user by username - ";
            CustomLogger.log(logstring, LogLevel.INFO);

            String sql = "select * from foundation_project_app.app_users where username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            rs.next();

            User user = new User();
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRoleID(rs.getInt("role_id"));
            return user;


        } catch (SQLException e) {
            logstring = String.format("Something went wrong trying to get user by username. More Info: %s", ExceptionUtils.getMessage(e));
            CustomLogger.log(logstring, LogLevel.ERROR);
            CustomLogger.parser();
            throw new RuntimeException(e);
        }
    }

     @Override
    public User getUserById(int id) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "select * from foundation_project_app.app_users where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            User user = new User();
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRoleID(rs.getInt("role_id"));
            return user;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "select * from foundation_project_app.app_users";
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<User> userList = new ArrayList<>();

            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRoleID(rs.getInt("role_id"));
                userList.add(user);
            }
            return userList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User updateUser(User user) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "update from foundation_project_app.app_users set first_name = ?, last_name = ?, username = ?, password = ?, role_id = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRoleID());
            ps.setInt(6, user.getId());

            ps.execute();
            return user;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUserById(int id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "delete from foundation_project_app.app_users where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
