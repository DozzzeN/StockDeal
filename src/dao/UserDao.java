package dao;

import pojo.User;

import java.util.List;

public interface UserDao {

    /**
     * �����û����������ѯ�û���Ϣ
     * @param uname �û���
     * @param pwd ����
     * @param role �û����
     * @return ��ѯ�����û���Ϣ
     */
    User checkUserLoginDao(String uname, String pwd, String role);

    /**
     * �����û�id�޸�����
     * @param newPwd ������
     * @param uid �û���
     * @return �Ƿ��޸�
     */
    int userChangePwdDao(String newPwd, int uid);

    /**
     * ע���û�
     * @param u
     * @return
     */
    int userRegDao(User u);

    /**
     * �����û���
     * @return
     */
    int userAmountsDao();

    /**
     * �����û���Ϣ
     * @return
     */
    List<User> userShowDao();

    /**
     * �����û�����ѯ�û���ַ
     * @return
     */
    String userAddressDao(String uname);

    /**
     * �����û�����ѯ�û�ID
     * @param uname
     * @return
     */
    int userIdDao(String uname);

    /**
     * �����û���ַ��ѯ�û���
     * @param address
     * @return
     */
    String userUnameDao(String address);
}
