package com.thoughtworks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static Connection con = null;

    public DbUtil() {
        try {
            if (con==null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/mydatabase1?serverTimezone" +
                                "=UTC&characterEncoding=utf-8&useSSL=false", "root", "root");
            }
        } catch (
                ClassNotFoundException e) {
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return con;
    }

}


