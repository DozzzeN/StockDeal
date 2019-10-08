package dao;

import pojo.Stock;

public interface StockDao {
    /**
     * 创建股票，写入股票初始信息
     * @param s
     * @return
     */
    int createStockDao(Stock s);

    /**
     * 查询最大id，即新加入的股票id
     * @return
     */
    int maxIdDao();

    /**
     * 根据股票名查询是否有同名股票
     * @param sname
     * @return
     */
    int selectBysnameDao(int sname);

    /**
     * 根据股票名查询股票ID
     * @param sname
     * @return
     */
    int selectSidBySnameDao(int sname);

    /**
     * 根据股票名查询股票发行者地址
     * @param sname
     * @return
     */
    String selectIssuerBySnameDao(int sname);
}
