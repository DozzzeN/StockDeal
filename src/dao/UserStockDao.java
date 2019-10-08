package dao;

import pojo.UserStock;

public interface UserStockDao {

    /**
     * �����û���Ʊ�˻�
     * @param us
     * @return
     */
    int createUserStock(UserStock us);

    /**
     * ����uid��sid��ѯ�û���Ʊ��Ϣ
     * @param uid
     * @param sid
     * @return
     */
    UserStock selectUserStockByUidandSid(int uid, int sid);

    /**
     * ����uid��sid�ı��û���Ʊ��
     * @param amount
     * @param uid
     * @param sid
     * @return
     */
    int changeAmountByUidandSid(int amount, int uid, int sid);
}
