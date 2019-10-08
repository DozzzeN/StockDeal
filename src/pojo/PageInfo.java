package pojo;

import java.util.List;

public class PageInfo {
    private int pageSize; //每页显示个数
    private int pageNumber; //当前第几页
    private long total; //总页数
    private List<?> list; //当前页显示的数据

    public PageInfo() {
    }

    public PageInfo(int pageSize, int pageNumber, long total, List<?> list) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.total = total;
        this.list = list;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
