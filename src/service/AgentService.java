package service;

import pojo.Agent;

public interface AgentService {
    /**
     * 写入agent
     * @param agent
     * @return
     */
    int createAgentService(Agent agent);

    /**
     * 根据oid tid sid返回agent对象
     * @param oid
     * @param tid
     * @param sid
     * @return
     */
    Agent selectByOidTidSidService(int oid, int tid, int sid);

    /**
     * 改变代理量
     * @param samount
     * @param oid
     * @param tid
     * @param sid
     * @return
     */
    int changeSamountByOidTidSidService(int samount, int oid, int tid, int sid);
}
