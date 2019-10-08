package dao.impl;

import dao.UserDao;
import pojo.User;
import utils.CloseJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User checkUserLoginDao(String uname, String pwd, String role) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "select * from t_user where uname=? and pwd=? and role=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, uname);
            ps.setString(2, pwd);
            ps.setString(3, role);
            rs = ps.executeQuery();
            while (rs.next()) {
                u = new User();
                u.setUid(rs.getInt("uid"));
                u.setUname(rs.getString("uname"));
                u.setPwd(rs.getString("pwd"));
                u.setRole(rs.getString("role"));
                u.setAddress(rs.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, rs);
        }
        return u;
    }

    @Override
    public int userChangePwdDao(String newPwd, int uid) {
        Connection conn = null;
        PreparedStatement ps = null;
        int index = -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "user", "");
            String sql = "update t_user set pwd=? where uid=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, newPwd);
            ps.setInt(2, uid);
            index = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, null);
        }

        return index;
    }

    @Override
    public int userRegDao(User u) {
        Connection conn = null;
        PreparedStatement ps = null;
        int index = -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "insert into t_user values(default,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, u.getUname());
            ps.setString(2, u.getPwd());
            ps.setString(3, u.getRole());
            ps.setString(4, u.getAddress());
            index = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, null);
        }
        return index;
    }

    @Override
    public int userAmountsDao() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int amount = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "select count(*) from t_user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                amount = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, rs);
        }
        return amount;
    }

    @Override
    public List<User> userShowDao() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        //优化：一次数据库获取，分批分页读取集合list
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "select uid, uname, role, address from t_user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUid(rs.getInt("uid"));
                u.setUname(rs.getString("uname"));
                u.setRole(rs.getString("role"));
                u.setAddress(rs.getString("address"));
                userList.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, rs);
        }
        return userList;
    }

    @Override
    public String userAddressDao(String uname) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String address = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "select address from t_user where uname=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, uname);
            rs = ps.executeQuery();
            while (rs.next()) {
                address = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, rs);
        }
        return address;
    }

    @Override
    public int userIdDao(String uname) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "select uid from t_user where uname=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, uname);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, rs);
        }
        return id;
    }

    @Override
    public String userUnameDao(String address) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String uname = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "select uname from t_user where address=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, address);
            rs = ps.executeQuery();
            while (rs.next()) {
                uname = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, rs);
        }
        return uname;
    }
}
