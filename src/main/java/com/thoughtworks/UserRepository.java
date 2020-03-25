package com.thoughtworks;

import java.sql.*;

public class UserRepository {

    public void save(UserInfo user) {
        if (!isExist(user.getAccount())) {
            try (Connection con = DbUtil.getConnection();
                 PreparedStatement ps = con.prepareStatement("insert into user(id,mobile,email,password) values(?,?,?,?)")) {
                ps.setString(1, user.getAccount());
                ps.setString(2, user.getMobile());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassWord());

                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private boolean isExist(String id) {
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


    public String getPassword(String id) {
        String password = null;

        if (isExist(id)) {
            try (Connection con = DbUtil.getConnection();
                 PreparedStatement ps = con.prepareStatement(String.format("select password from user where id='%s'", id));
                 ResultSet rs = ps.executeQuery()
            ) {
                if (rs.next()) {
                    password = rs.getString("password");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return password;
    }

    public UserInfo getInfo(String id) {
        UserInfo user = new UserInfo();
        if (isExist(id)) {
            try (Connection con = DbUtil.getConnection();
                 PreparedStatement ps = con.prepareStatement(String.format("select id,mobile,email,password from user where id='%s'", id))) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    user.setAccount(rs.getString("id"));
                    user.setMobile(rs.getString("mobile"));
                    user.setEmail(rs.getString("email"));
                    user.setPassWord(rs.getString("password"));
                }
            } catch (
                    SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public void updateError(String id) {
        if (isExist(id)) {
            try (Connection con = DbUtil.getConnection();
                 PreparedStatement ps = con.prepareStatement(String.format("update user set errornum= errornum+1 where id='%s'", id))) {
                ps.execute();
            } catch (
                    SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getErrorNum(String id){
        int error = 0;
        if (isExist(id)) {
            try (Connection con = DbUtil.getConnection();
                 PreparedStatement ps = con.prepareStatement(String.format("select errornum from user where id='%s'", id))) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    error=rs.getInt("errornum");
                }
            } catch (
                    SQLException e) {
                e.printStackTrace();
            }
        }
        return error;
    }
}
