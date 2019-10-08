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

    //ֻ�ܶ����������ˣ�
    private List<User> users = null;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String oper = req.getParameter("oper");
        //ǰ�����ģʽ ֮����restful
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
            logger.debug("δ�ҵ�������" + oper);
        }
    }


    //��������
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

    //��ѯ�û���Ϣ
    private void userShow(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //���ֻ����һ�����ݿ�
        users = us.userShowService();
//        if (users.size() > 0) {
//            //���÷�ҳ����
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
//            //��ҳ��
//            int total = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
//            List<User> partOfUsers = new ArrayList<>();
//            Map<Integer, List<User>> map = new HashMap<>();//�����ڼ�ҳ ֵ����һҳ�����û���list
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
        //��ȡsession
        System.out.println(users);
        HttpSession hs = req.getSession();
        hs.setAttribute("users", users);
//            HttpSession hs = req.getSession();
//            hs.setAttribute("map", map);
    }


    //�û���¼
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

    //��Ʊ����
    private void createStock(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //��ȡ������Ϣ
        int suname = req.getParameter("sname").trim() != null ? Integer.parseInt(req.getParameter("sname").trim()) : 0;
        User user = (User) req.getSession().getAttribute("user");
        String issuer = user.getAddress();
        int amount = req.getParameter("amount").trim() != null ? Integer.parseInt(req.getParameter("amount").trim()) : 0;
        //��ѯ�Ƿ��ǹ���Ա
        if (user.getRole() == "user") {
            //ʧ����Ϣд�ظ�ǰ��js
            resp.getWriter().write(String.valueOf(-2));
        }
        //��ѯ�Ƿ���ͬ����Ʊ��
        int data = ss.selectBysnameService(suname);
        if (data == 1) {
            //ʧ����Ϣд�ظ�ǰ��js
            resp.getWriter().write(String.valueOf(-1));
        } else if (data == 0) {
            Stock s = new Stock(0, suname, issuer, amount);//�˴�id����ָ������Ϊdao���е�sql��default
            System.out.println(s);
            ss.createStockService(s);

            int maxId = ss.maxIdService();
            System.out.println(maxId);

            User u = (User) req.getSession().getAttribute("user");
            int uid = u.getUid();
            UserStock us = new UserStock(0, uid, maxId, amount);
            System.out.println(us);
            uss.createUserStockService(us);
            //�ɹ����û���ַд�ظ�ǰ��js
            resp.getWriter().write(String.valueOf(issuer));
        }
    }


    //�����߷����Ʊ
    private void distributeStock(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //�����Ʊ��
        int sname = req.getParameter("sname").trim() != null ? Integer.parseInt(req.getParameter("sname".trim())) : 0;
        //������
        User user = (User) req.getSession().getAttribute("user");
        //����Ŀ���ַ
        String targetAddress = req.getParameter("target").trim() != null ? req.getParameter("target".trim()) : "";
        //����Ŀ��ID
        int targetID = us.userIdService(us.userUnameService(targetAddress));
        //�����Ʊ��
        int amount = req.getParameter("amount").trim() != null ? Integer.parseInt(req.getParameter("amount".trim())) : 0;
        //�����Ʊid
        int sid = ss.selectSidBySnameService(sname);
        int data;
        //�������û���Ʊ�˻�
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
        //���з���
        UserStock userStockTarget = uss.selectUserStockByUidandSidService(targetID, sid);
        System.out.println("userStockTarget" + userStockTarget);
        if (userStockTarget.getUsid() == 0) {
            //Ŀ���˻�û�й��ڴ˹�Ʊ�ļ�¼��Ϣ
            userStockTarget.setUid(targetID);
            userStockTarget.setSid(sid);
            userStockTarget.setAmount(amount);
            //�ɹ�Ϊ1
            data = uss.createUserStockService(userStockTarget);
        } else {
            //Ŀ���˻����д˹�Ʊ�ļ�¼��Ϣ
            data = -3;
            resp.getWriter().write(String.valueOf(data));
            return;
        }
        if (data == 1) {
            data = uss.changeAmountByUidandSidService(userStock.getAmount() - amount, userStock.getUid(), sid);
            System.out.println(userStock.getAmount() - amount + " " + targetID + " " + sid);
            System.out.println(data);
            //���ص����ߵ�ַ���Ա�ǰ��ʹ�ô˵�ַ���ͽ���
            resp.getWriter().write(String.valueOf(user.getAddress()));
            System.out.println(user.getAddress());
        }
    }


    //��Ʊת��
    private void transferStock(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //�����Ʊ��
        int sname = req.getParameter("sname").trim() != null ? Integer.parseInt(req.getParameter("sname".trim())) : 0;
        //������
        User user = (User) req.getSession().getAttribute("user");
        //����Ŀ���ַ
        String targetAddress = req.getParameter("target").trim() != null ? req.getParameter("target".trim()) : "";
        //����Ŀ��ID
        int targetID = us.userIdService(us.userUnameService(targetAddress));
        //�����Ʊ��
        int amount = req.getParameter("amount").trim() != null ? Integer.parseInt(req.getParameter("amount".trim())) : 0;
        //�����Ʊid
        int sid = ss.selectSidBySnameService(sname);
        int data;
        //�������û���Ʊ�˻�
        UserStock userStock = uss.selectUserStockByUidandSidService(user.getUid(), sid);
        System.out.println("userStock" + userStock);
        if (amount > userStock.getAmount()) {
            data = -1;
            resp.getWriter().write(String.valueOf(data));
            return;
        }
        //���з���
        UserStock userStockTarget = uss.selectUserStockByUidandSidService(targetID, sid);
        System.out.println("userStockTarget" + userStockTarget);
        if (userStockTarget.getUsid() == 0) {
            //Ŀ���˻�û�й��ڴ˹�Ʊ�ļ�¼��Ϣ
            userStockTarget.setUid(targetID);
            userStockTarget.setSid(sid);
            userStockTarget.setAmount(amount);
            //�ɹ�Ϊ1
            data = uss.createUserStockService(userStockTarget);
        } else {
            //Ŀ���˻����д˹�Ʊ�ļ�¼��Ϣ
            data = uss.changeAmountByUidandSidService(userStockTarget.getAmount() + amount, targetID, sid);
        }
        if (data == 1) {
            data = uss.changeAmountByUidandSidService(userStock.getAmount() - amount, userStock.getUid(), sid);
            System.out.println(userStock.getAmount() - amount + " " + targetID + " " + sid);
            System.out.println(data);
            //���ص����ߵ�ַ���Ա�ǰ��ʹ�ô˵�ַ���ͽ���
            resp.getWriter().write(String.valueOf(user.getAddress()));
            System.out.println(user.getAddress());
        }
    }


    //��Ʊ����
    private void delegateStock(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //�����Ʊ��
        int sname = req.getParameter("sname").trim() != null ? Integer.parseInt(req.getParameter("sname".trim())) : 0;
        //��������
        User user = (User) req.getSession().getAttribute("user");
        //�����ߵ�ַ
        String targetAddress = req.getParameter("target").trim() != null ? req.getParameter("target".trim()) : "";
        //������ID
        int targetID = us.userIdService(us.userUnameService(targetAddress));
        //�����Ʊ��
        int amount = req.getParameter("amount").trim() != null ? Integer.parseInt(req.getParameter("amount".trim())) : 0;
        //�����Ʊid
        int sid = ss.selectSidBySnameService(sname);
        //�����Ʊ������
        String sissuer = ss.selectIssuerBySnameService(sname);
        //�������
        int MyErrorFlag;
        //�Ƿ���ڴ����˻���Ϣ
        int isExistDelegate;
        //json�ַ���
        String json;
        Map<String, Object> map = new HashMap<>();
        //���������û���Ʊ�˻�
        UserStock userStock = uss.selectUserStockByUidandSidService(user.getUid(), sid);
        System.out.println("userStock" + userStock);
        if (amount > userStock.getAmount()) {
            //����������
            MyErrorFlag = -1;
            map.put("MyErrorFlag", MyErrorFlag);
            json = new Gson().toJson(map);
            resp.getWriter().write(json);
            return;
        }
        //���д���
        Agent agent = as.selectByOidTidSidService(user.getUid(), targetID, sid);
        if (agent.getDid() == 0) {
            //û�д����¼��Ϣ
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
                    //�״δ���
                    isExistDelegate = 0;
                    map.put("isExistDelegate", isExistDelegate);
                    map.put("issuerAddress", user.getAddress());
                    json = new Gson().toJson(map);
                    resp.getWriter().write(json);
                }
            }
        } else {
            //���д����¼��Ϣ
            if (as.changeSamountByOidTidSidService(agent.getSamount() + amount, user.getUid(), targetID, sid) == 1
            ) {
                if (uss.changeAmountByUidandSidService(userStock.getAmount() - amount, userStock.getUid(), sid) == 1) {
                    //����׷��
                    isExistDelegate = 1;
                    map.put("isExistDelegate", isExistDelegate);
                    map.put("issuerAddress", user.getAddress());
                    json = new Gson().toJson(map);
                    resp.getWriter().write(json);
                }
            }
        }
    }

    //�û�ע��
    private void userReg(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //��ȡ������Ϣ
        String uname = req.getParameter("uname") != null ? req.getParameter("uname") : null;
        String pwd = req.getParameter("pwd") != null ? req.getParameter("pwd") : null;
        String role = req.getParameter("role") != null ? req.getParameter("role") : null;
        String address = req.getParameter("address") != null ? req.getParameter("address") : null;

//        String privateKey = utils.SHA256.getSHA256Str(pwd);
//        resp.getWriter().write(privateKey);

        User u = new User(0, uname, pwd, role, address);
        System.out.println(u);
        //����������Ϣ
        int index = us.userRegService(u);
        //��Ӧ������
        if (index > 0) {
            //��ȡsession
            HttpSession hs = req.getSession();
            hs.setAttribute("flag", 2);
        }
    }

    //�����û�����
    private void userAmount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //ajax�������ͱ�����String��������Ϊ��
        String amount = String.valueOf(us.userAmountService());
        System.out.println(amount);
        //д�ظ�ǰ��js
        resp.getWriter().write(amount);
    }
}
