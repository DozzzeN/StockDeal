package pojo;

import java.util.Objects;

public class Stock {
    private int sid;
    private int sname;
    private String issuer;
    private int amount;

    public Stock() {
    }

    public Stock(int sid, int sname, String issuer, int amount) {
        this.sid = sid;
        this.sname = sname;
        this.issuer = issuer;
        this.amount = amount;
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

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
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
        Stock stock = (Stock) object;
        return sid == stock.sid &&
                sname == stock.sname &&
                amount == stock.amount &&
                issuer.equals(stock.issuer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, sname, issuer, amount);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "sid=" + sid +
                ", sname=" + sname +
                ", issuer='" + issuer + '\'' +
                ", amount=" + amount +
                '}';
    }
}
