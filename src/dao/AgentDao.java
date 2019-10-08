package dao;

import pojo.Agent;

public interface AgentDao {
    /**
     * ����agent����д������
     * @param agent
     * @return
     */
    int createAgentDao(Agent agent);

    /**
     * ����oid tid sid����agent����
     * @param oid
     * @param tid
     * @param sid
     * @return
     */
    Agent selectByOidTidSid(int oid, int tid, int sid);


    /**
     * �ı������
     * @param samount
     * @param oid
     * @param tid
     * @param sid
     * @return
     */
    int changeSamountByOidTidSid(int samount, int oid, int tid, int sid);
}
