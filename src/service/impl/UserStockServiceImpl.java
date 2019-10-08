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
        logger.debug("�û�" + us.getUid() + "���𴴽���Ʊ�˻�����");
        int index = usd.createUserStock(us);
        if (index > 0) {
            logger.debug("�û�" + us.getUid() + "������Ʊ�˻��ɹ�");
        } else {
            logger.debug("�û�" + us.getUid() + "������Ʊ�˻�ʧ��");
        }
        return index;
    }

    @Override
    public UserStock selectUserStockByUidandSidService(int uid, int sid) {
        logger.debug("����uid��sid��ѯ�û���Ʊ");
        UserStock userStock = usd.selectUserStockByUidandSid(uid, sid);
        if (userStock != null) {
            logger.debug("����uid��sid��ѯ�û���Ʊ�ɹ�");
        } else {
            logger.debug("����uid��sid��ѯ�û���Ʊʧ��");
        }
        return userStock;
    }

    @Override
    public int changeAmountByUidandSidService(int amount, int uid, int sid) {
        logger.debug("����uid��sid�����û���Ʊ���");
        int index = usd.changeAmountByUidandSid(amount, uid, sid);
        if (index != 0) {
            logger.debug("�����û���Ʊ���ɹ�");
        } else {
            logger.debug("�����û���Ʊ���ʧ��");
        }
        return index;
    }
}
