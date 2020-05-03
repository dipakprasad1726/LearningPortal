package com.learning.portal.model.db;

import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Singleton
@Component
public class DbConnection {
    public Statement getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/learning_portal?autoReconnect=true&useSSL=false";
        String user = "root";
        String password = "root";
        Connection con = DriverManager.getConnection(url,user,password);
        return con.createStatement();
    }
}
