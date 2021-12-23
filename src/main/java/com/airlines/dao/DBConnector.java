package com.airlines.dao;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnector {

    private MysqlDataSource dataSource = new MysqlDataSource();

    public DBConnector() {
        ResourceBundle appResource = ResourceBundle.getBundle("application");
        dataSource.setUser(appResource.getString("jdbcUsername"));
        dataSource.setPassword(appResource.getString("jdbcPassword"));
        dataSource.setUrl(appResource.getString("jdbcURL"));
        dataSource.setDatabaseName("airlines");
    }

    public Connection getConnection() {

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Can not obtain database connection");
        }
    }
}