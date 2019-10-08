package dao.impl;

import dao.AgentDao;
import pojo.Agent;
import utils.CloseJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AgentDaoImpl implements AgentDao {

    @Override
    public int createAgentDao(Agent agent) {
        Connection conn = null;
        PreparedStatement ps = null;

        int index = -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "insert into t_agent values(default,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, agent.getOid());
            ps.setString(2, agent.getOaddress());
            ps.setInt(3, agent.getTid());
            ps.setString(4, agent.getTaddress());
            ps.setInt(5, agent.getSid());
            ps.setInt(6, agent.getSname());
            ps.setString(7, agent.getSissuer());
            ps.setInt(8, agent.getSamount());
            index = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, null);
        }
        return index;
    }

    @Override
    public Agent selectByOidTidSid(int oid, int tid, int sid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Agent agent = new Agent();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "select * from t_agent where oid=? and tid=? and sid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, oid);
            ps.setInt(2, tid);
            ps.setInt(3, sid);
            rs = ps.executeQuery();
            while (rs.next()) {
                agent.setDid(rs.getInt("did"));
                agent.setOid(rs.getInt("oid"));
                agent.setOaddress(rs.getString("oaddress"));
                agent.setTid(rs.getInt("tid"));
                agent.setTaddress(rs.getString("taddress"));
                agent.setSid(rs.getInt("sid"));
                agent.setSname(rs.getInt("sname"));
                agent.setSissuer(rs.getString("sissuer"));
                agent.setSamount(rs.getInt("samount"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, rs);
        }
        return agent;
    }

    @Override
    public int changeSamountByOidTidSid(int samount, int oid, int tid, int sid) {
        Connection conn = null;
        PreparedStatement ps = null;

        int index = -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdeal_ms", "root", "");
            String sql = "update t_agent set samount = ? where oid=? and tid=? and sid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, samount);
            ps.setInt(2, oid);
            ps.setInt(3, tid);
            ps.setInt(4, sid);
            index = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseJDBC.close(conn, ps, null);
        }
        return index;
    }
}
