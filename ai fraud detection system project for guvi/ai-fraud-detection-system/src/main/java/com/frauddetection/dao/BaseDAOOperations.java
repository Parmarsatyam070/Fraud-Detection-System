package com.frauddetection.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface BaseDAOOperations {
    Connection getConnection() throws SQLException;
}
