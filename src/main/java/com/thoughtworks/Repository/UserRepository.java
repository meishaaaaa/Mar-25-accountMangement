package com.thoughtworks.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    public static void save(User user) {
        {
            try (Connection con = DbUtil.getConnection();
                 PreparedStatement ps = con.prepareStatement("insert into user(id,mobile,email,password) values(?,?,?,?)")) {
                ps.setString(1, user.getAccount());
                ps.setString(2, user.getMobile());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassWord());

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static boolean isExist(String id) {
        boolean flg = false;
        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(String.format("select count(*) from user where id='%s'", id));
             ResultSet rs = ps.executeQuery()
        ) {
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            if (count > 0) {
                flg = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flg;
    }

    public static User getInfo(String id) {
        User user = new User();
        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(String.format("select id,mobile,email,password from user where id='%s'", id))) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.setAccount(rs.getString("id"));
                user.setMobile(rs.getString("mobile"));
                user.setEmail(rs.getString("email"));
                user.setPassWord(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    //在数据库中，如果是errornumber默认是0，是1， 就是被锁了
    public static boolean isLocked(String id) {
        int errorNum = 0;

        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(String.format("select errornumber from user where id='%s'", id));
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                errorNum = rs.getInt("errornumber");
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return errorNum == 1;
    }

    public static void lockAccount(String id) {
        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(String.format("update user set errornumber= 1 where id='%s'", id))) {
            ps.execute();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

}

