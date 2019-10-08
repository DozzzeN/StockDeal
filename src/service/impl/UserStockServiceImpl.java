package service.impl;

import dao.UserStockDao;
import dao.impl.UserStockDaoImpl;
import org.apache.log4j.Logger;
import pojo.UserStock;
import service.UserService;
import service.UserStockService;

public class UserStockServiceImpl implements UserStockService {
    Logger logger = Logger.getLogger(UserStockServiceImpl.class);
    UserStockDao usd = new UserStockDaoImpl();

    @Override
    public int createUserStockService(UserStock us) {
        logger.debug("用户" + us.getUid() + "发起创建股票账户请求");
        int index = usd.createUserStock(us);
        if (index > 0) {
            logger.debug("用户" + us.getUid() + "创建股票账户成功");
        } else {
            logger.debug("用户" + us.getUid() + "创建股票账户失败");
        }
        return index;
    }

    @Override
    public UserStock selectUserStockByUidandSidService(int uid, int sid) {
        logger.debug("根据uid和sid查询用户股票");
        UserStock userStock = usd.selectUserStockByUidandSid(uid, sid);
        if (userStock != null) {
            logger.debug("根据uid和sid查询用户股票成功");
        } else {
            logger.debug("根据uid和sid查询用户股票失败");
        }
        return userStock;
    }

    @Override
    public int changeAmountByUidandSidService(int amount, int uid, int sid) {
        logger.debug("根据uid和sid更改用户股票余额");
        int index = usd.changeAmountByUidandSid(amount, uid, sid);
        if (index != 0) {
            logger.debug("更改用户股票余额成功");
        } else {
            logger.debug("更改用户股票余额失败");
        }
        return index;
    }
}
