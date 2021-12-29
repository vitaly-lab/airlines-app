package com.airlines.dao;

import com.airlines.exception.DatabaseConnectivityException;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnector {

    private final MysqlDataSource dataSource = new MysqlDataSource();

    public DBConnector() {
        ResourceBundle resource = ResourceBundle.getBundle("application");
        dataSource.setUser(resource.getString("jdbcUsername"));
        dataSource.setPassword(resource.getString("jdbcPassword"));
        dataSource.setUrl(resource.getString("jdbcURL"));
        dataSource.setDatabaseName("airlines");
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DatabaseConnectivityException("Database connection could not be obtained", e);
        }
    }
}