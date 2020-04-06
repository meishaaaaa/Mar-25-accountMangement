package com.thoughtworks.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static Connection con = null;
    private static final String url = "jdbc:mysql://localhost:3306/mydatabase1?serverTimezone=UTC&characterEncoding=utf-8&useSSL=false";
    private static final String user = "root";
    private static final String password = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (
                ClassNotFoundException e) {
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection(url, user, password);

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
