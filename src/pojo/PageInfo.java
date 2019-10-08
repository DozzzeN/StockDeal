package pojo;

import java.util.List;

public class PageInfo {
    private int pageSize; //ÿҳ��ʾ����
    private int pageNumber; //��ǰ�ڼ�ҳ
    private long total; //��ҳ��
    private List<?> list; //��ǰҳ��ʾ������

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
