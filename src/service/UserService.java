package service;

import pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 校验用户登录
     * @param uname 用户名
     * @param pwd 密码
     * @param role 用户身份
     * @return 查询到的用户信息
     */
    User checkUserLoginService(String uname, String pwd, String role);

    /**
     * 修改用户密码
     * @param newPwd
     * @param uid
     * @return
     */
    int userChangePwdService(String newPwd, int uid);

    /**
     * 获取所有用户信息
     * @return
     */
    //List<User> userShowService();

    /**
     * 用户注册
     * @param u
     * @return
     */
    int userRegService(User u);

    /**
     * 用户总数
     * @return 用户总数
     */
    int userAmountService();

    /**
     * 返回所有用户信息
     * @return 返回所有用户信息
     */
    List<User> userShowService();

    /**
     * 根据用户名查询用户地址
     * @return
     */
    String userAddressService(String uname);

    /**
     * 根据用户名查询用户ID
     * @param
     * @return
     */
    int userIdService(String uname);

    /**
     * 根据用户地址查询用户名
     * @param address
     * @return
     */
    String userUnameService(String address);
}
