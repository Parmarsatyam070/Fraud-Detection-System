package com.frauddetection;

import com.frauddetection.dao.UserDAO;
import com.frauddetection.model.User;

// This class is for testing your database connection and DAO insert logic.
public class TestInsert {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        // Create a User object with unique values
        User user = new User("u202", "newuser202", "newuser202@gmail.com", "Pass@123");
        boolean ok = dao.saveUser(user);
        System.out.println("Insert successful? " + ok);
    }
}
