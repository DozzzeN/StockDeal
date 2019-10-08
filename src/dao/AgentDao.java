package dao;

import pojo.Agent;

public interface AgentDao {
    /**
     * 根据agent对象写入数据
     * @param agent
     * @return
     */
    int createAgentDao(Agent agent);

    /**
     * 根据oid tid sid返回agent对象
     * @param oid
     * @param tid
     * @param sid
     * @return
     */
    Agent selectByOidTidSid(int oid, int tid, int sid);


    /**
     * 改变代理量
     * @param samount
     * @param oid
     * @param tid
     * @param sid
     * @return
     */
    int changeSamountByOidTidSid(int samount, int oid, int tid, int sid);
}
