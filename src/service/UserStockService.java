package service;

import pojo.UserStock;

public interface UserStockService {
    /**
     * 创建用户股票账户
     * @param us
     * @return
     */
    int createUserStockService(UserStock us);

    /**
     * 根据uid和sid查询用户股票
     * @param uid
     * @param sid
     * @return
     */
    UserStock selectUserStockByUidandSidService(int uid, int sid);

    /**
     * 根据uid和sid改变用户股票数
     * @param amount
     * @param uid
     * @param sid
     * @return
     */
    int changeAmountByUidandSidService(int amount, int uid, int sid);
}
