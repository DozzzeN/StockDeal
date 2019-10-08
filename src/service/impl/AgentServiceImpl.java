package service.impl;

import dao.AgentDao;
import dao.impl.AgentDaoImpl;
import org.apache.log4j.Logger;
import pojo.Agent;
import service.AgentService;

public class AgentServiceImpl implements AgentService {
    //��־�������
    Logger logger = Logger.getLogger(StockServiceImpl.class);
    //dao�����
    AgentDao ad = new AgentDaoImpl();

    @Override
    public int createAgentService(Agent agent) {
        logger.debug("���𴴽������˻�����");
        int index = ad.createAgentDao(agent);
        if (index == 1) {
            logger.debug("���������˻�����ɹ�");
        } else {
            logger.debug("���������˻�����ʧ��");
        }
        return index;
    }

    @Override
    public Agent selectByOidTidSidService(int oid, int tid, int sid) {
        logger.debug("�����ѯ�����˻�����");
        Agent agent = ad.selectByOidTidSid(oid, tid, sid);
        if (agent != null) {
            logger.debug("���������˻�����ɹ�");
        } else {
            logger.debug("���������˻�����ʧ��");
        }
        return agent;
    }

    @Override
    public int changeSamountByOidTidSidService(int samount, int oid, int tid, int sid) {
        logger.debug("������Ĵ���������");
        int index = ad.changeSamountByOidTidSid(samount, oid, tid, sid);
        if (index == 1) {
            logger.debug("���Ĵ���������ɹ�");
        } else {
            logger.debug("���Ĵ���������ʧ��");
        }
        return index;
    }
}
