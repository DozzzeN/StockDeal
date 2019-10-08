package dao.impl;

import dao.StockDao;
import pojo.Stock;
import utils.CloseJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StockDaoImpl implements StockDao {
    //´´½¨¹ÉÆ±
    @Override
    public int createStockDao(Stock s) {
        Connection conn = null;
        PreparedStatement ps = null;

        int index = -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "insert into t_stock values(default,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, s.getSname());
            ps.setString(2, s.getIssuer());
            ps.setInt(3, s.getAmount());
            index = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, null);
        }
        return index;
    }

    @Override
    public int maxIdDao() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int maxId = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "select max(sid) from t_stock";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                maxId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, rs);
        }
        return maxId;
    }

    @Override
    public int selectBysnameDao(int sname) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int index = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "select count(*) from t_stock where sname=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,sname);
            rs = ps.executeQuery();
            while (rs.next()) {
                index = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, rs);
        }
        return index;
    }

    @Override
    public int selectSidBySnameDao(int sname) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int sid = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "select sid from t_stock where sname=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,sname);
            rs = ps.executeQuery();
            while (rs.next()) {
                sid = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, rs);
        }
        return sid;
    }

    @Override
    public String selectIssuerBySnameDao(int sname) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String issuer = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "select issuer from t_stock where sname=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,sname);
            rs = ps.executeQuery();
            while (rs.next()) {
                issuer = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, rs);
        }
        return issuer;
    }


}
