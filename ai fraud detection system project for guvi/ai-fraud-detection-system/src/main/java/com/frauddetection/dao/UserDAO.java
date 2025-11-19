package com.frauddetection.dao;

import com.frauddetection.model.User;
import com.frauddetection.exception.DatabaseException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO implements UserDAOOperations {

    // Save a User to MySQL
    @Override
    public boolean saveUser(User u) {
        String sql = "INSERT INTO users (user_id, username, email, password_hash, registration_date) VALUES (?, ?, ?, ?, NOW())";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getUserId());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPasswordHash());
            int result = ps.executeUpdate();
            System.out.println("User Insert result: " + result);
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve Users from MySQL
    @Override
    public List<User> getAllUsers() throws DatabaseException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, email, password_hash FROM users";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getString("user_id"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setPasswordHash(rs.getString("password_hash"));
                users.add(u);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error fetching users", e);
        }
        return users;
    }
}
