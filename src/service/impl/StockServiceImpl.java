package service.impl;

import dao.StockDao;
import dao.impl.StockDaoImpl;
import org.apache.log4j.Logger;
import pojo.Stock;
import service.StockService;

public class StockServiceImpl implements StockService {
    //��־�������
    Logger logger = Logger.getLogger(StockServiceImpl.class);
    //dao�����
    StockDao sd = new StockDaoImpl();

    //������Ʊ
    @Override
    public int createStockService(Stock s) {
        logger.debug(s.getIssuer() + "�����𴴽���Ʊ����");
        int index = sd.createStockDao(s);
        if (index > 0) {
            logger.debug(s.getIssuer() + "������Ʊ�ɹ�");
        } else {
            logger.debug(s.getIssuer() + "������Ʊʧ��");
        }
        return index;
    }

    @Override
    public int maxIdService() {
        logger.debug("�����ѯ��ƱID����");
        int maxId = sd.maxIdDao();
        if (maxId > 0) {
            logger.debug("��ѯ��ƱID�ɹ�");
        } else {
            logger.debug("��ѯ��ƱIDʧ��");
        }
        return maxId;
    }

    @Override
    public int selectBysnameService(int sname) {
        logger.debug("�����ѯ��Ʊ�Ƿ�ͬ������");
        int index = sd.selectBysnameDao(sname);
        if (index > 0) {
            logger.debug("��ѯ��Ʊ�Ƿ�ͬ���ɹ�");
        } else {
            logger.debug("��ѯ��Ʊ�Ƿ�ͬ��ʧ��");
        }
        return index;
    }

    @Override
    public int selectSidBySnameService(int sname) {
        logger.debug("������ݹ�Ʊ����ѯ��ƱID����");
        int sid = sd.selectSidBySnameDao(sname);
        if (sid > 0) {
            logger.debug("��ѯ��ƱID�ɹ�");
        } else {
            logger.debug("��ѯ��ƱIDʧ��");
        }
        return sid;
    }

    @Override
    public String selectIssuerBySnameService(int sname) {
        logger.debug("���ݹ�Ʊ����ѯ��Ʊ�����ߵ�ַ����");
        String issuer = sd.selectIssuerBySnameDao(sname);
        if (issuer != null) {
            logger.debug("���ݹ�Ʊ����ѯ��Ʊ�����ߵ�ַ�ɹ�");
        } else {
            logger.debug("���ݹ�Ʊ����ѯ��Ʊ�����ߵ�ַʧ��");
        }
        return issuer;
    }
}
