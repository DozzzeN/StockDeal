package service;

import pojo.UserStock;

public interface UserStockService {
    /**
     * �����û���Ʊ�˻�
     * @param us
     * @return
     */
    int createUserStockService(UserStock us);

    /**
     * ����uid��sid��ѯ�û���Ʊ
     * @param uid
     * @param sid
     * @return
     */
    UserStock selectUserStockByUidandSidService(int uid, int sid);

    /**
     * ����uid��sid�ı��û���Ʊ��
     * @param amount
     * @param uid
     * @param sid
     * @return
     */
    int changeAmountByUidandSidService(int amount, int uid, int sid);
}
