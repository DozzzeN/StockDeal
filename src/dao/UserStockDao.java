package dao;

import pojo.UserStock;

public interface UserStockDao {

    /**
     * 创建用户股票账户
     * @param us
     * @return
     */
    int createUserStock(UserStock us);

    /**
     * 根据uid和sid查询用户股票信息
     * @param uid
     * @param sid
     * @return
     */
    UserStock selectUserStockByUidandSid(int uid, int sid);

    /**
     * 根据uid和sid改变用户股票数
     * @param amount
     * @param uid
     * @param sid
     * @return
     */
    int changeAmountByUidandSid(int amount, int uid, int sid);
}
