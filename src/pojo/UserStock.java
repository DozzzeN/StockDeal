package pojo;

import java.util.Objects;

public class UserStock {
    private int usid;
    private int uid;
    private int sid;
    private int amount;

    public UserStock() {
    }

    public UserStock(int usid, int uid, int sid, int amount) {
        this.usid = usid;
        this.uid = uid;
        this.sid = sid;
        this.amount = amount;
    }

    public int getUsid() {
        return usid;
    }

    public void setUsid(int usid) {
        this.usid = usid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UserStock userStock = (UserStock) object;
        return usid == userStock.usid &&
                uid == userStock.uid &&
                sid == userStock.sid &&
                amount == userStock.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usid, uid, sid, amount);
    }

    @Override
    public String toString() {
        return "UserStock{" +
                "usid=" + usid +
                ", uid=" + uid +
                ", sid=" + sid +
                ", amount=" + amount +
                '}';
    }
}
