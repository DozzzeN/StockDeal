package service;

import pojo.Stock;

public interface StockService {

    /**
     * 创建股票，写入股票初始信息
     * @param s 股票对象
     * @return
     */
    int createStockService(Stock s);

    /**
     * 查询最大股票ID
     * @return
     */
    int maxIdService();

    /**
     * 查询是否有同名股票名
     * @param sname
     * @return
     */
    int selectBysnameService(int sname);

    /**
     * 根据股票名查询股票ID
     * @param sname
     * @return
     */
    int selectSidBySnameService(int sname);

    /**
     * 根据股票名查询股票发行者地址
     * @param sname
     * @return
     */
    String selectIssuerBySnameService(int sname);
}
