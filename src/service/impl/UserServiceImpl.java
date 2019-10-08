package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.apache.log4j.Logger;
import pojo.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    Logger logger = Logger.getLogger(UserServiceImpl.class);
    UserDao ud = new UserDaoImpl();

    @Override
    public User checkUserLoginService(String uname, String pwd, String role) {
        logger.debug(uname + "�����¼����");
        User u = ud.checkUserLoginDao(uname, pwd, role);
        if (u != null) {
            logger.debug(uname + "��¼�ɹ�");
        } else {
            logger.debug(uname + "��¼ʧ��");
        }
        return u;
    }

    @Override
    public int userChangePwdService(String newPwd, int uid) {
        logger.debug(uid + "�����������޸�����");
        int index = ud.userChangePwdDao(newPwd, uid);
        if (index > 0) {
            logger.debug(uid + "�����޸ĳɹ�");
        } else {
            logger.debug(uid + "�����޸�ʧ��");
        }
        return index;
    }

    @Override
    public int userRegService(User u) {
        logger.debug("�οͷ���ע������");
        return ud.userRegDao(u);
    }

    @Override
    public int userAmountService() {
        logger.debug("�οͷ����ѯ�û���������");
        return ud.userAmountsDao();
    }

    @Override
    public List<User> userShowService() {
        logger.debug("�û������ѯ�û���Ϣ����");
        return ud.userShowDao();
    }

    @Override
    public String userAddressService(String uname) {
        logger.debug("�û������ѯ�û���ַ����");
        return ud.userAddressDao(uname);
    }

    @Override
    public int userIdService(String uname) {
        logger.debug("�û������ѯ�û�ID����");
        return ud.userIdDao(uname);
    }

    @Override
    public String userUnameService(String address) {
        logger.debug("�����û���ַ��ѯ�û���");
        return ud.userUnameDao(address);
    }
}
