package dao;

import pojo.User;

import java.util.List;

public interface UserDao {

    /**
     * 根据用户名和密码查询用户信息
     * @param uname 用户名
     * @param pwd 密码
     * @param role 用户身份
     * @return 查询到的用户信息
     */
    User checkUserLoginDao(String uname, String pwd, String role);

    /**
     * 根据用户id修改密码
     * @param newPwd 新密码
     * @param uid 用户名
     * @return 是否修改
     */
    int userChangePwdDao(String newPwd, int uid);

    /**
     * 注册用户
     * @param u
     * @return
     */
    int userRegDao(User u);

    /**
     * 返回用户数
     * @return
     */
    int userAmountsDao();

    /**
     * 返回用户信息
     * @return
     */
    List<User> userShowDao();

    /**
     * 根据用户名查询用户地址
     * @return
     */
    String userAddressDao(String uname);

    /**
     * 根据用户名查询用户ID
     * @param uname
     * @return
     */
    int userIdDao(String uname);

    /**
     * 根据用户地址查询用户名
     * @param address
     * @return
     */
    String userUnameDao(String address);
}
