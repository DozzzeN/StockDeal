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
        logger.debug(uname + "发起登录请求");
        User u = ud.checkUserLoginDao(uname, pwd, role);
        if (u != null) {
            logger.debug(uname + "登录成功");
        } else {
            logger.debug(uname + "登录失败");
        }
        return u;
    }

    @Override
    public int userChangePwdService(String newPwd, int uid) {
        logger.debug(uid + "：发起密码修改请求");
        int index = ud.userChangePwdDao(newPwd, uid);
        if (index > 0) {
            logger.debug(uid + "密码修改成功");
        } else {
            logger.debug(uid + "密码修改失败");
        }
        return index;
    }

    @Override
    public int userRegService(User u) {
        logger.debug("游客发起注册请求");
        return ud.userRegDao(u);
    }

    @Override
    public int userAmountService() {
        logger.debug("游客发起查询用户个数请求");
        return ud.userAmountsDao();
    }

    @Override
    public List<User> userShowService() {
        logger.debug("用户发起查询用户信息请求");
        return ud.userShowDao();
    }

    @Override
    public String userAddressService(String uname) {
        logger.debug("用户发起查询用户地址请求");
        return ud.userAddressDao(uname);
    }

    @Override
    public int userIdService(String uname) {
        logger.debug("用户发起查询用户ID请求");
        return ud.userIdDao(uname);
    }

    @Override
    public String userUnameService(String address) {
        logger.debug("根据用户地址查询用户名");
        return ud.userUnameDao(address);
    }
}
