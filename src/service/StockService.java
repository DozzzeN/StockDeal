package service;

import pojo.Stock;

public interface StockService {

    /**
     * ������Ʊ��д���Ʊ��ʼ��Ϣ
     * @param s ��Ʊ����
     * @return
     */
    int createStockService(Stock s);

    /**
     * ��ѯ����ƱID
     * @return
     */
    int maxIdService();

    /**
     * ��ѯ�Ƿ���ͬ����Ʊ��
     * @param sname
     * @return
     */
    int selectBysnameService(int sname);

    /**
     * ���ݹ�Ʊ����ѯ��ƱID
     * @param sname
     * @return
     */
    int selectSidBySnameService(int sname);

    /**
     * ���ݹ�Ʊ����ѯ��Ʊ�����ߵ�ַ
     * @param sname
     * @return
     */
    String selectIssuerBySnameService(int sname);
}
