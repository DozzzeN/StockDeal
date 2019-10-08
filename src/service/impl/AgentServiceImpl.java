package service.impl;

import dao.AgentDao;
import dao.impl.AgentDaoImpl;
import org.apache.log4j.Logger;
import pojo.Agent;
import service.AgentService;

public class AgentServiceImpl implements AgentService {
    //日志处理对象
    Logger logger = Logger.getLogger(StockServiceImpl.class);
    //dao层对象
    AgentDao ad = new AgentDaoImpl();

    @Override
    public int createAgentService(Agent agent) {
        logger.debug("发起创建代理账户请求");
        int index = ad.createAgentDao(agent);
        if (index == 1) {
            logger.debug("创建代理账户请求成功");
        } else {
            logger.debug("创建代理账户请求失败");
        }
        return index;
    }

    @Override
    public Agent selectByOidTidSidService(int oid, int tid, int sid) {
        logger.debug("发起查询代理账户请求");
        Agent agent = ad.selectByOidTidSid(oid, tid, sid);
        if (agent != null) {
            logger.debug("创建代理账户请求成功");
        } else {
            logger.debug("创建代理账户请求失败");
        }
        return agent;
    }

    @Override
    public int changeSamountByOidTidSidService(int samount, int oid, int tid, int sid) {
        logger.debug("发起更改代理量请求");
        int index = ad.changeSamountByOidTidSid(samount, oid, tid, sid);
        if (index == 1) {
            logger.debug("更改代理量请求成功");
        } else {
            logger.debug("更改代理量请求失败");
        }
        return index;
    }
}
