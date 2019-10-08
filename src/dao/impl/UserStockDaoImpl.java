package dao.impl;

import dao.UserStockDao;
import pojo.UserStock;
import utils.CloseJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserStockDaoImpl implements UserStockDao {

    @Override
    public int createUserStock(UserStock us) {
        Connection conn = null;
        PreparedStatement ps = null;

        int index = -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "insert into t_user_stock values(default,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, us.getUid());
            ps.setInt(2, us.getSid());
            ps.setInt(3, us.getAmount());
            index = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, null);
        }
        return index;
    }

    @Override
    public UserStock selectUserStockByUidandSid(int uid, int sid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserStock userStock = new UserStock();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "select * from t_user_stock where uid=? and sid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            ps.setInt(2, sid);
            rs = ps.executeQuery();
            while (rs.next()) {
                userStock.setUsid(rs.getInt("usid"));
                userStock.setUid(rs.getInt("uid"));
                userStock.setSid(rs.getInt("sid"));
                userStock.setAmount(rs.getInt("amount"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, rs);
        }
        return userStock;
    }

    @Override
    public int changeAmountByUidandSid(int amount, int uid, int sid) {
        Connection conn = null;
        PreparedStatement ps = null;

        int index = -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "update t_user_stock set amount = ? where uid=? and sid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, amount);
            ps.setInt(2, uid);
            ps.setInt(3, sid);
            index = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, null);
        }
        return index;
    }
}
