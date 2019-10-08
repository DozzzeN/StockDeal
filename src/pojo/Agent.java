package pojo;

import java.util.Objects;

public class Agent {
    private int did;
    private int oid;
    private String oaddress;
    private int tid;
    private String taddress;
    private int sid;
    private int sname;
    private String sissuer;
    private int samount;

    public Agent() {
    }

    public Agent(int did, int oid, String oaddress, int tid, String taddress, int sid, int sname, String sissuer, int samount) {
        this.did = did;
        this.oid = oid;
        this.oaddress = oaddress;
        this.tid = tid;
        this.taddress = taddress;
        this.sid = sid;
        this.sname = sname;
        this.sissuer = sissuer;
        this.samount = samount;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getOaddress() {
        return oaddress;
    }

    public void setOaddress(String oaddress) {
        this.oaddress = oaddress;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTaddress() {
        return taddress;
    }

    public void setTaddress(String taddress) {
        this.taddress = taddress;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getSname() {
        return sname;
    }

    public void setSname(int sname) {
        this.sname = sname;
    }

    public String getSissuer() {
        return sissuer;
    }

    public void setSissuer(String sissuer) {
        this.sissuer = sissuer;
    }

    public int getSamount() {
        return samount;
    }

    public void setSamount(int samount) {
        this.samount = samount;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "did=" + did +
                ", oid=" + oid +
                ", oaddress='" + oaddress + '\'' +
                ", tid=" + tid +
                ", taddress='" + taddress + '\'' +
                ", sid=" + sid +
                ", sname=" + sname +
                ", sissuer='" + sissuer + '\'' +
                ", samount=" + samount +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Agent agent = (Agent) object;
        return did == agent.did &&
                oid == agent.oid &&
                tid == agent.tid &&
                sid == agent.sid &&
                sname == agent.sname &&
                samount == agent.samount &&
                Objects.equals(oaddress, agent.oaddress) &&
                Objects.equals(taddress, agent.taddress) &&
                Objects.equals(sissuer, agent.sissuer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(did, oid, oaddress, tid, taddress, sid, sname, sissuer, samount);
    }
}
