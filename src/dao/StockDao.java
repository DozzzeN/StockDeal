package dao;

import pojo.Stock;

public interface StockDao {
    /**
     * ������Ʊ��д���Ʊ��ʼ��Ϣ
     * @param s
     * @return
     */
    int createStockDao(Stock s);

    /**
     * ��ѯ���id�����¼���Ĺ�Ʊid
     * @return
     */
    int maxIdDao();

    /**
     * ���ݹ�Ʊ����ѯ�Ƿ���ͬ����Ʊ
     * @param sname
     * @return
     */
    int selectBysnameDao(int sname);

    /**
     * ���ݹ�Ʊ����ѯ��ƱID
     * @param sname
     * @return
     */
    int selectSidBySnameDao(int sname);

    /**
     * ���ݹ�Ʊ����ѯ��Ʊ�����ߵ�ַ
     * @param sname
     * @return
     */
    String selectIssuerBySnameDao(int sname);
}
