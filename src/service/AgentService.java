package service;

import pojo.Agent;

public interface AgentService {
    /**
     * д��agent
     * @param agent
     * @return
     */
    int createAgentService(Agent agent);

    /**
     * ����oid tid sid����agent����
     * @param oid
     * @param tid
     * @param sid
     * @return
     */
    Agent selectByOidTidSidService(int oid, int tid, int sid);

    /**
     * �ı������
     * @param samount
     * @param oid
     * @param tid
     * @param sid
     * @return
     */
    int changeSamountByOidTidSidService(int samount, int oid, int tid, int sid);
}
