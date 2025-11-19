package com.frauddetection.dao;

import com.frauddetection.model.User;
import com.frauddetection.exception.DatabaseException;
import java.util.List;

public interface UserDAOOperations {
    boolean saveUser(User u);
    List<User> getAllUsers() throws DatabaseException;
}
