<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>股票合约管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet">
    <script src="js/jquery-3.1.0.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="node_modules/web3/dist/web3.min.js"></script>
    <script src="js/login.js"></script>
</head>

<body style="background-image: url(images/3.jpg); background-repeat: no-repeat; background-size: cover">
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <nav class="navbar navbar-default">
            <div class="navbar-header">
                <a class="navbar-brand" href="login.jsp">欢迎登录股票合约管理系统</a>
            </div>
        </nav>
    </div>
</div>
<div class="row information">
    <div class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1">
        <c:choose>
            <c:when test="${requestScope.flag==0}">
                <span style="font-size: 15px;color: darkred;font-weight: bold">用户名或密码错误</span>
            </c:when>
            <c:when test="${sessionScope.flag==1}">
                <span style="font-size: 15px;color: darkred;font-weight: bold">密码修改成功，请重新登录</span>
            </c:when>
            <c:when test="${sessionScope.flag==2}">
                <span style="font-size: 15px;color: darkred;font-weight: bold">注册成功，请重新登录</span>
            </c:when>
        </c:choose>
        <c:remove var="flag" scope="session"/>
    </div>
</div>
<div class="row form">
    <div class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1">
        <form action="user" method="post">
            <input type="hidden" name="oper" value="login"/>
            <div class="form-group">
                <input type="text" class="form-control" name="uname" placeholder="用户名" required/>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="pwd" placeholder="密码" required/>
            </div>
            <div class="form-group">
                <label class="radio-inline">
                    <input type="radio" name="role" value="publisher" required/>发行者
                </label>
                <label class="radio-inline">
                    <input type="radio" name="role" value="user" required/>普通用户
                </label>
            </div>
            <button type="submit" class="btn btn-md btn-primary" style="width: 46%; float: left;">登录</button>
            <a href="#" data-toggle="modal" data-target="#register" class="btn btn-md btn-primary"
               style="width: 46%; float: right;">注册</a>
        </form>
        <!-- 模态弹窗 -->
        <div class="modal fade" id="register">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span class="glyphicon glyphicon-remove"></span>
                        </button>
                        <h4 class="modal-title">注册账户</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="oper" value="register"/>
                        <input type="text" id="uname" class="form-control" placeholder="用户名"
                               required/>
                        <br/>
                        <input type="password" id="pwd" class="form-control" placeholder="密码"
                               required/>
                        <br/>
                        <div id="role">
                            <label class="radio-inline">
                                <input type="radio" name="role" value="publisher" required/>发行者
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="role" value="user" required/>普通用户
                            </label>
                        </div>
                        <br/>
                        <button type="submit" class="btn btn-md btn-block btn-primary" onclick="register()">注册
                        </button>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default btn-lg"
                                data-dismiss="modal">关闭
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row footer navbar-fixed-bottom">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <p>我好菜啊</p>
    </div>
</div>
</body>
</html>
