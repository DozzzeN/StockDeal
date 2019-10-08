package service;

import pojo.User;

import java.util.List;

public interface UserService {
    /**
     * У���û���¼
     * @param uname �û���
     * @param pwd ����
     * @param role �û����
     * @return ��ѯ�����û���Ϣ
     */
    User checkUserLoginService(String uname, String pwd, String role);

    /**
     * �޸��û�����
     * @param newPwd
     * @param uid
     * @return
     */
    int userChangePwdService(String newPwd, int uid);

    /**
     * ��ȡ�����û���Ϣ
     * @return
     */
    //List<User> userShowService();

    /**
     * �û�ע��
     * @param u
     * @return
     */
    int userRegService(User u);

    /**
     * �û�����
     * @return �û�����
     */
    int userAmountService();

    /**
     * ���������û���Ϣ
     * @return ���������û���Ϣ
     */
    List<User> userShowService();

    /**
     * �����û�����ѯ�û���ַ
     * @return
     */
    String userAddressService(String uname);

    /**
     * �����û�����ѯ�û�ID
     * @param
     * @return
     */
    int userIdService(String uname);

    /**
     * �����û���ַ��ѯ�û���
     * @param address
     * @return
     */
    String userUnameService(String address);
}
