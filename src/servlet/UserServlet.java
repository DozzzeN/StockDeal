package servlet;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import pojo.Agent;
import pojo.Stock;
import pojo.User;
import pojo.UserStock;
import service.AgentService;
import service.StockService;
import service.UserService;
import service.UserStockService;
import service.impl.AgentServiceImpl;
import service.impl.StockServiceImpl;
import service.impl.UserServiceImpl;
import service.impl.UserStockServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(UserServlet.class);
    private UserService us = new UserServiceImpl();
    private StockService ss = new StockServiceImpl();
    private UserStockService uss = new UserStockServiceImpl();
    private AgentService as = new AgentServiceImpl();

    //只能定义在这里了？
    private List<User> users = null;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String oper = req.getParameter("oper");
        //前端设计模式 之后用restful
        if ("login".equals(oper)) {
            checkUserLogin(req, resp);
        } else if ("create".equals(oper)) {
            createStock(req, resp);
        } else if ("distribute".equals(oper)) {
            distributeStock(req, resp);
        } else if ("transfer".equals(oper)) {
            transferStock(req, resp);
        } else if ("delegate".equals(oper)) {
            delegateStock(req, resp);
        } else if ("register".equals(oper)) {
            userReg(req, resp);
        } else if ("amount".equals(oper)) {
            userAmount(req, resp);
        } else if ("show".equals(oper)) {
            userShow(req, resp);
        } else if ("change".equals(oper)) {
            changePwd(req, resp);
        } else {
            logger.debug("未找到操作符" + oper);
        }
    }


    //更改密码
    private void changePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uid = req.getParameter("uid");
        String newPassword = req.getParameter("pwd");
        int index = us.userChangePwdService(newPassword, Integer.parseInt(uid));
        if (index > 0) {
            HttpSession hs = req.getSession();
            hs.setAttribute("flag", 1);
            resp.sendRedirect("/myproject/login.jsp");
        }
    }

    //查询用户信息
    private void userShow(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //最好只请求一次数据库
        users = us.userShowService();
//        if (users.size() > 0) {
//            //设置分页属性
//            String pageSizeStr = req.getParameter("pageSize");
//            int pageSize = 7;
//            if (pageSizeStr != null && !pageSizeStr.equals("")) {
//                pageSize = Integer.parseInt(pageSizeStr);
//            }
//
//            String pageNumberStr = req.getParameter("pageNumber");
//            int pageNumber = 1;
//            if (pageNumberStr != null && !pageNumberStr.equals("")) {
//                pageNumber = Integer.parseInt(pageNumberStr);
//            }
//
//            int count = us.userAmountService();
//            //总页数
//            int total = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
//            List<User> partOfUsers = new ArrayList<>();
//            Map<Integer, List<User>> map = new HashMap<>();//键：第几页 值：这一页所有用户的list
//            for (int i = 0; i < total; i++) {
//                partOfUsers.clear();
//                for (User user : users) {
//                    partOfUsers.add(user);
//                }
//                map.put(i, partOfUsers);
//            }
//            System.out.println(map);

//            PageInfo pageInfo = new PageInfo(pageSize, pageNumber, total, map.get(pageNumber));
//            req.setAttribute("PageInfo", pageInfo);
        //获取session
        System.out.println(users);
        HttpSession hs = req.getSession();
        hs.setAttribute("users", users);
//            HttpSession hs = req.getSession();
//            hs.setAttribute("map", map);
    }


    //用户登录
    private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        String role = req.getParameter("role");
        User u = us.checkUserLoginService(uname, pwd, role);
        if (u != null) {
            HttpSession hs = req.getSession();
            hs.setAttribute("user", u);
            resp.sendRedirect("/myproject/main.jsp");
        } else {
            req.setAttribute("flag", 0);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    //股票创建
    private void createStock(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //获取请求信息
        int suname = req.getParameter("sname").trim() != null ? Integer.parseInt(req.getParameter("sname").trim()) : 0;
        User user = (User) req.getSession().getAttribute("user");
        String issuer = user.getAddress();
        int amount = req.getParameter("amount").trim() != null ? Integer.parseInt(req.getParameter("amount").trim()) : 0;
        //查询是否是管理员
        if (user.getRole() == "user") {
            //失败信息写回给前端js
            resp.getWriter().write(String.valueOf(-2));
        }
        //查询是否有同名股票名
        int data = ss.selectBysnameService(suname);
        if (data == 1) {
            //失败信息写回给前端js
            resp.getWriter().write(String.valueOf(-1));
        } else if (data == 0) {
            Stock s = new Stock(0, suname, issuer, amount);//此处id随意指定，因为dao层中的sql是default
            System.out.println(s);
            ss.createStockService(s);

            int maxId = ss.maxIdService();
            System.out.println(maxId);

            User u = (User) req.getSession().getAttribute("user");
            int uid = u.getUid();
            UserStock us = new UserStock(0, uid, maxId, amount);
            System.out.println(us);
            uss.createUserStockService(us);
            //成功：用户地址写回给前端js
            resp.getWriter().write(String.valueOf(issuer));
        }
    }


    //发行者分配股票
    private void distributeStock(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //分配股票名
        int sname = req.getParameter("sname").trim() != null ? Integer.parseInt(req.getParameter("sname".trim())) : 0;
        //分配者
        User user = (User) req.getSession().getAttribute("user");
        //分配目标地址
        String targetAddress = req.getParameter("target").trim() != null ? req.getParameter("target".trim()) : "";
        //分配目标ID
        int targetID = us.userIdService(us.userUnameService(targetAddress));
        //分配股票数
        int amount = req.getParameter("amount").trim() != null ? Integer.parseInt(req.getParameter("amount".trim())) : 0;
        //分配股票id
        int sid = ss.selectSidBySnameService(sname);
        int data;
        //分配者用户股票账户
        UserStock userStock = uss.selectUserStockByUidandSidService(user.getUid(), sid);
        System.out.println("userStock" + userStock);
        String issuer = ss.selectIssuerBySnameService(sname);
        System.out.println(issuer);
        System.out.println(user.getAddress());
        if (!issuer.equals(user.getAddress())) {
            data = -2;
            resp.getWriter().write(String.valueOf(data));
            return;
        }
        if (amount > userStock.getAmount()) {
            data = -1;
            resp.getWriter().write(String.valueOf(data));
            return;
        }
        //进行分配
        UserStock userStockTarget = uss.selectUserStockByUidandSidService(targetID, sid);
        System.out.println("userStockTarget" + userStockTarget);
        if (userStockTarget.getUsid() == 0) {
            //目标账户没有关于此股票的记录信息
            userStockTarget.setUid(targetID);
            userStockTarget.setSid(sid);
            userStockTarget.setAmount(amount);
            //成功为1
            data = uss.createUserStockService(userStockTarget);
        } else {
            //目标账户已有此股票的记录信息
            data = -3;
            resp.getWriter().write(String.valueOf(data));
            return;
        }
        if (data == 1) {
            data = uss.changeAmountByUidandSidService(userStock.getAmount() - amount, userStock.getUid(), sid);
            System.out.println(userStock.getAmount() - amount + " " + targetID + " " + sid);
            System.out.println(data);
            //返回调用者地址，以便前端使用此地址发送交易
            resp.getWriter().write(String.valueOf(user.getAddress()));
            System.out.println(user.getAddress());
        }
    }


    //股票转让
    private void transferStock(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //分配股票名
        int sname = req.getParameter("sname").trim() != null ? Integer.parseInt(req.getParameter("sname".trim())) : 0;
        //分配者
        User user = (User) req.getSession().getAttribute("user");
        //分配目标地址
        String targetAddress = req.getParameter("target").trim() != null ? req.getParameter("target".trim()) : "";
        //分配目标ID
        int targetID = us.userIdService(us.userUnameService(targetAddress));
        //分配股票数
        int amount = req.getParameter("amount").trim() != null ? Integer.parseInt(req.getParameter("amount".trim())) : 0;
        //分配股票id
        int sid = ss.selectSidBySnameService(sname);
        int data;
        //分配者用户股票账户
        UserStock userStock = uss.selectUserStockByUidandSidService(user.getUid(), sid);
        System.out.println("userStock" + userStock);
        if (amount > userStock.getAmount()) {
            data = -1;
            resp.getWriter().write(String.valueOf(data));
            return;
        }
        //进行分配
        UserStock userStockTarget = uss.selectUserStockByUidandSidService(targetID, sid);
        System.out.println("userStockTarget" + userStockTarget);
        if (userStockTarget.getUsid() == 0) {
            //目标账户没有关于此股票的记录信息
            userStockTarget.setUid(targetID);
            userStockTarget.setSid(sid);
            userStockTarget.setAmount(amount);
            //成功为1
            data = uss.createUserStockService(userStockTarget);
        } else {
            //目标账户已有此股票的记录信息
            data = uss.changeAmountByUidandSidService(userStockTarget.getAmount() + amount, targetID, sid);
        }
        if (data == 1) {
            data = uss.changeAmountByUidandSidService(userStock.getAmount() - amount, userStock.getUid(), sid);
            System.out.println(userStock.getAmount() - amount + " " + targetID + " " + sid);
            System.out.println(data);
            //返回调用者地址，以便前端使用此地址发送交易
            resp.getWriter().write(String.valueOf(user.getAddress()));
            System.out.println(user.getAddress());
        }
    }


    //股票代理
    private void delegateStock(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //代理股票名
        int sname = req.getParameter("sname").trim() != null ? Integer.parseInt(req.getParameter("sname".trim())) : 0;
        //被代理者
        User user = (User) req.getSession().getAttribute("user");
        //代理者地址
        String targetAddress = req.getParameter("target").trim() != null ? req.getParameter("target".trim()) : "";
        //代理者ID
        int targetID = us.userIdService(us.userUnameService(targetAddress));
        //代理股票数
        int amount = req.getParameter("amount").trim() != null ? Integer.parseInt(req.getParameter("amount".trim())) : 0;
        //代理股票id
        int sid = ss.selectSidBySnameService(sname);
        //代理股票发行者
        String sissuer = ss.selectIssuerBySnameService(sname);
        //错误代码
        int MyErrorFlag;
        //是否存在代理账户信息
        int isExistDelegate;
        //json字符串
        String json;
        Map<String, Object> map = new HashMap<>();
        //被代理者用户股票账户
        UserStock userStock = uss.selectUserStockByUidandSidService(user.getUid(), sid);
        System.out.println("userStock" + userStock);
        if (amount > userStock.getAmount()) {
            //代理者余额不足
            MyErrorFlag = -1;
            map.put("MyErrorFlag", MyErrorFlag);
            json = new Gson().toJson(map);
            resp.getWriter().write(json);
            return;
        }
        //进行代理
        Agent agent = as.selectByOidTidSidService(user.getUid(), targetID, sid);
        if (agent.getDid() == 0) {
            //没有代理记录信息
            agent.setOid(user.getUid());
            agent.setOaddress(user.getAddress());
            agent.setTid(targetID);
            agent.setTaddress(targetAddress);
            agent.setSid(sid);
            agent.setSname(sname);
            agent.setSissuer(sissuer);
            agent.setSamount(amount);
            System.out.println("agent" + agent);
            if (as.createAgentService(agent) == 1) {
                if (uss.changeAmountByUidandSidService(userStock.getAmount() - amount, userStock.getUid(), sid) == 1) {
                    //首次代理
                    isExistDelegate = 0;
                    map.put("isExistDelegate", isExistDelegate);
                    map.put("issuerAddress", user.getAddress());
                    json = new Gson().toJson(map);
                    resp.getWriter().write(json);
                }
            }
        } else {
            //已有代理记录信息
            if (as.changeSamountByOidTidSidService(agent.getSamount() + amount, user.getUid(), targetID, sid) == 1
            ) {
                if (uss.changeAmountByUidandSidService(userStock.getAmount() - amount, userStock.getUid(), sid) == 1) {
                    //代理追加
                    isExistDelegate = 1;
                    map.put("isExistDelegate", isExistDelegate);
                    map.put("issuerAddress", user.getAddress());
                    json = new Gson().toJson(map);
                    resp.getWriter().write(json);
                }
            }
        }
    }

    //用户注册
    private void userReg(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //获取请求信息
        String uname = req.getParameter("uname") != null ? req.getParameter("uname") : null;
        String pwd = req.getParameter("pwd") != null ? req.getParameter("pwd") : null;
        String role = req.getParameter("role") != null ? req.getParameter("role") : null;
        String address = req.getParameter("address") != null ? req.getParameter("address") : null;

//        String privateKey = utils.SHA256.getSHA256Str(pwd);
//        resp.getWriter().write(privateKey);

        User u = new User(0, uname, pwd, role, address);
        System.out.println(u);
        //处理请求信息
        int index = us.userRegService(u);
        //响应处理结果
        if (index > 0) {
            //获取session
            HttpSession hs = req.getSession();
            hs.setAttribute("flag", 2);
        }
    }

    //返回用户总数
    private void userAmount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //ajax返回类型必须是String否则乱码为空
        String amount = String.valueOf(us.userAmountService());
        System.out.println(amount);
        //写回给前端js
        resp.getWriter().write(amount);
    }
}
