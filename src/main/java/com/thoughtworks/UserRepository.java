package com.thoughtworks;

import java.sql.*;

public class UserRepository {

    public void save(UserInfo user) {
        DbUtil db = new DbUtil();
        if (!isExist(user.getAccount())) {
            try (Connection con = db.getConnection();
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
        DbUtil db = new DbUtil();
        Connection con = db.getConnection();

        PreparedStatement ps = null;
        try {
            String sql = "select count(*) from user where id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            int count = 0;

            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count > 0) {
                flg = true;
            }

            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, ps);
        }

        return flg;
    }

    private void close(Connection con, PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
        }
    }


    public String getPassword(String id) {
        DbUtil db = new DbUtil();
        Connection con = db.getConnection();

        PreparedStatement ps = null;
        try {
            if (isExist(id)) {
                String sql = "select password from user where id=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getString("password");
                }

                if (rs != null) {
                    rs.close();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, ps);
        }

        return null;
    }

    public UserInfo getInfo(String id) {
        DbUtil db = new DbUtil();
        Connection con = db.getConnection();

        PreparedStatement ps = null;

        UserInfo user = new UserInfo();
        try {
            if (isExist(id)) {
                String sql = String.format("select id,mobile,email,password from user where id='%s'", id);
                ps = con.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();


                while (rs.next()) {
                    user.setAccount(rs.getString("id"));
                    user.setMobile(rs.getString("mobile"));
                    user.setEmail(rs.getString("email"));
                    user.setPassWord(rs.getString("password"));
                }

                if (rs != null) {
                    rs.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, ps);
        }

        return user;
    }
}
