<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>股票合约管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet"/>
    <script src="js/jquery-3.1.0.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="node_modules/web3/dist/web3.min.js"></script>
    <script src="js/bignumber.min.js"></script>
    <script src="js/main.js"></script>

</head>
<body>
<script type="text/javascript">
    $.holdReady(true);
    $.getScript('js/main.js', function () {
        $.holdReady(false);
    });
</script>
<!-- 页头：导航部分 -->
<nav class="navbar navbar-default header">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="main.jsp">
                <p>欢迎登录股票合约管理系统</p>
            </a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                       aria-expanded="false">
                        欢迎<span>${sessionScope.user.uname}</span>登录
                        <span class="caret"></span>
                    </a>
                    <ul id="settingInNav" class="dropdown-menu">
                        <li><a href="#" data-toggle="modal" data-target="#change"><span
                                class="glyphicon glyphicon-wrench">&nbsp;</span>修改密码</a></li>
                        <li><a href="#"
                        <%--href="javascript:void(0);" onclick="show()" --%>
                               data-toggle="modal" data-target="#show"><span
                                class="glyphicon glyphicon-info-sign">&nbsp;</span>查看用户信息</a></li>
                        <li><a href="./login.jsp"><span class="glyphicon glyphicon-off">&nbsp;</span>退出登录</a></li>
                        <li class="divider"></li>
                        <li><a href="javascript:void(0)" onclick="issue()"><span
                                class="glyphicon glyphicon-registration-mark">&nbsp;</span>发行股票</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- 查看用户信息模态弹窗 -->
        <div class="modal fade" id="show">
            <div class="modal-dialog" style="width: 60%;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span class="glyphicon glyphicon-remove"></span>
                        </button>
                        <h3 class="modal-title"><strong>用户信息</strong></h3>
                    </div>
                    <div class="modal-body">
                        <table class="table table-responsive table-striped table-hover">
                            <thead>
                            <tr>
                                <th width="20%"
                                    style="word-break:break-all; word-wrap:break-word; white-space:inherit">用户ID
                                </th>
                                <th width="20%"
                                    style="word-break:break-all; word-wrap:break-word; white-space:inherit">用户名
                                </th>
                                <th width="20%"
                                    style="word-break:break-all; word-wrap:break-word; white-space:inherit">用户角色
                                </th>
                                <th width="40%"
                                    style="word-break:break-all; word-wrap:break-word; white-space:inherit">用户地址
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${sessionScope.users}" var="users">
                                <tr>
                                    <td>${users.uid}</td>
                                    <td>${users.uname}</td>
                                    <td>${users.role}</td>
                                    <td>${users.address}</td>
                                </tr>
                            </c:forEach>
                            <%--<tr>--%>
                            <%--<td>--%>
                            <%--<a href="user?pageNumber=${PageInfo.pageNumber-1}&pageSize=${PageInfo.pageSize}"--%>
                            <%--<c:if test="${PageInfo.pageNumber<=1}">--%>
                            <%--onclick="javascript:return false;"--%>
                            <%--</c:if>--%>
                            <%-->上一页</a>--%>
                            <%--</td>--%>
                            <%--<td>--%>
                            <%--<a href="user?pageNumber=${PageInfo.pageNumber+1}&pageSize=${PageInfo.pageSize}"--%>
                            <%--<c:if test="${PageInfo.pageNumber>=pageInfo.total}">--%>
                            <%--onclick="javascript:return false;"--%>
                            <%--</c:if>--%>
                            <%-->下一页</a>--%>
                            <%--</td>--%>
                            <%--</tr>--%>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default btn-lg" data-dismiss="modal">关闭
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <!-- 查看用户信息模态弹窗 -->
        <div class="modal fade" id="change">
            <div class="modal-dialog" style="width: 30%;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span class="glyphicon glyphicon-remove"></span>
                        </button>
                        <h3 class="modal-title"><strong>修改密码</strong></h3>
                    </div>
                    <div class="modal-body">
                        <form action="user" method="post">
                            <input type="hidden" name="oper" value="change"/>
                            <input type="hidden" name="uid" value="${sessionScope.user.uid}"/>
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon">旧密码</span>
                                <input id="oldPassword" type="password" class="form-control" onblur="change()"/>
                                <span class="input-group-addon" id="spanOldPassword"><span class="glyphicon"
                                                                                           id="iconOldPassword"></span></span>
                            </div>
                            <br/>
                            <div class="input-group input-group-lg">
                                <span class="input-group-addon">新密码</span>
                                <input id="newPassword" type="password" class="form-control" name="pwd"/>
                            </div>
                            <br/>
                            <button type="submit" onclick="change()" class="btn btn-success btn-lg btn-block">提交修改
                            </button>
                        </form>
                    </div>
                    <%--<div class="modal-footer">--%>
                    <%----%>
                    <%--</div>--%>
                    <script type="text/javascript">
                        function change() {
                            $("#iconOldPassword").removeClass("glyphicon-remove");
                            $("#iconOldPassword").removeClass("glyphicon-ok");
                            var oldPassword = $("#oldPassword").val();
                            if (oldPassword != ${sessionScope.user.pwd} || oldPassword == "") {
                                $("#spanOldPassword").show();
                                $("#iconOldPassword").addClass("glyphicon-remove");
                            } else {
                                $("#spanOldPassword").show();
                                $("#iconOldPassword").addClass("glyphicon-ok");
                            }
                        }
                    </script>
                </div>
            </div>
        </div>
    </div>
</nav>

<!-- 主体部分 -->
<div class="container-fluid content">
    <div class="row" style="height: 100%;">
        <!-- 网格左边布局 -->
        <div id="gridLeft" class="col-lg-2 col-md-2 col-sm-3 col-xs-3 content-row-left">
            <!-- 正常菜单 -->
            <div class="panel-group mypanel" id="BigPanel">
                <div class="panel panel-default">
                    <a id="One" class="panel-title" data-toggle="collapse" data-parent="#BigPanel" href="#collapseOne"
                       style="text-decoration: none;">
                        <div class="panel-heading">
                            <span class="glyphicon glyphicon-cog">&nbsp;</span>
                            <h4 style="display: inline;">管理模块</h4>
                            <span class="glyphicon glyphicon-menu-down" style="float: right;"></span>
                        </div>
                    </a>
                    <div id="collapseOne" class="panel-collapse collapse">
                        <a href="#" id="collapseOneFirst" style="text-decoration: none;">
                            <div class="panel-body">发行股票</div>
                        </a>
                        <a href="#" id="collapseOneSecond" style="text-decoration: none;">
                            <div class="panel-body">分配股票</div>
                        </a>
                    </div>

                </div>
                <div class="panel panel-default">
                    <a id="Two" class="panel-title" data-toggle="collapse" data-parent="#BigPanel" href="#collapseTwo"
                       style="text-decoration: none;">
                        <div class="panel-heading">
                            <span class="glyphicon glyphicon-shopping-cart">&nbsp;</span>
                            <h4 style="display: inline;">交易模块</h4>
                            <span class="glyphicon glyphicon-menu-down" style="float: right;"></span>
                        </div>
                    </a>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <a href="#" id="collapseTwoFirst" style="text-decoration: none;">
                            <div class="panel-body">股票买卖</div>
                        </a>
                        <a href="#" id="collapseTwoSecond" style="text-decoration: none;">
                            <div class="panel-body">股票代理</div>
                        </a>
                    </div>
                </div>
                <div class="panel panel-default">
                    <a id="Three" class="panel-title" data-toggle="collapse" data-parent="#BigPanel"
                       href="#collapseThree" style="text-decoration: none;">
                        <div class="panel-heading">
                            <span class="glyphicon glyphicon-search">&nbsp;</span>
                            <h4 style="display: inline;">查询模块</h4>
                            <span class="glyphicon glyphicon-menu-down" style="float: right;"></span>
                        </div>
                    </a>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <a href="#" id="collapseThreeFirst" style="text-decoration: none;">
                            <div class="panel-body">查询股票余额</div>
                        </a>
                        <a href="#" id="collapseThreeSecond" style="text-decoration: none;">
                            <div class="panel-body">查询管理者账户</div>
                        </a>
                        <a href="#" id="collapseThreeThird" style="text-decoration: none;">
                            <div class="panel-body">查询股票名</div>
                        </a>
                        <a href="#" id="collapseThreeFourth" style="text-decoration: none;">
                            <div class="panel-body">查询股票发行者</div>
                        </a>
                        <a href="#" id="collapseThreeFifth" style="text-decoration: none;">
                            <div class="panel-body">查询代理者</div>
                        </a>
                    </div>
                </div>
            </div>

            <!-- 缩略菜单 -->
            <div class="panel-group mypanel" id="SmallPanel">
                <div class="panel panel-default">
                    <div id="smallOne" class="panel-title mytooltip">
                        <div class="panel-heading">
                            <span class="glyphicon glyphicon-cog">&nbsp;</span>
                        </div>
                        <div class="tooltiptext btn-group btn-group-vertical btn-group-mi" role="group">
                            <button class="btn btn-info btn-lg" type="button">管理模块</button>
                            <button class="btn btn-primary" type="button">发行股票</button>
                            <button class="btn btn-primary" type="button">分配股票</button>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div id="smallTwo" class="panel-title mytooltip">
                        <div class="panel-heading">
                            <span class="glyphicon glyphicon-shopping-cart">&nbsp;</span>
                        </div>
                        <div class="tooltiptext btn-group btn-group-vertical btn-group-mi" role="group">
                            <button class="btn btn-info btn-lg" type="button">交易模块</button>
                            <button class="btn btn-primary" type="button">股票买卖</button>
                            <button class="btn btn-primary" type="button">股票代理</button>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div id="smallThree" class="panel-title mytooltip">
                        <div class="panel-heading">
                            <span class="glyphicon glyphicon-search">&nbsp;</span>
                        </div>
                        <div class="tooltiptext btn-group btn-group-vertical btn-group-mi" role="group">
                            <button class="btn btn-info btn-lg" type="button">查询模块</button>
                            <button class="btn btn-primary" type="button">查询股票余额</button>
                            <button class="btn btn-primary" type="button">查询管理者账户</button>
                            <button class="btn btn-primary" type="button">查询股票名</button>
                            <button class="btn btn-primary" type="button">查询股票发行者</button>
                            <button class="btn btn-primary" type="button">查询代理者</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 菜单尾部缩放按钮 -->
            <div class="panel-group mypanel" id="IconPanel">
                <div class="panel panel-default">
                    <div class="panel-footer" onclick="resize()">
                        <span class="glyphicon glyphicon-eye-close center"></span>
                    </div>
                </div>
            </div>
        </div>

        <!-- 网格右边布局 -->
        <div id="gridRight" class="col-lg-10 col-md-10 col-sm-9 col-xs-9 content-row-right">
            <!-- 管理模块 -->
            <div id="manageContent">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#issue" data-toggle="tab">发行股票</a>
                    </li>
                    <li>
                        <a href="#distribute" data-toggle="tab">分配股票</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="issue">
                        <!-- 操作反馈信息 -->
                        <div style="padding-right:-15px; margin-top: 15px;">
                            <div class="alert alert-info alert-dismissable" style="margin-bottom: 15px">
                                <button type="button" class="close" data-dismiss="alert">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </button>
                                <span class="glyphicon glyphicon-info-sign"></span>
                                <strong>现在，你可以创建股票！请输入尚未创建过的六位数字的股票名和您选择的股票数来进行创建股票</strong>
                            </div>
                        </div>
                        <div id="issueInTab">
                            <div class="col-sm-6 col-md-4 col-lg-4" style="margin-left: -15px;">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">发行股票</h3>
                                    </div>
                                    <div class="panel-body nostyle">
                                        <h4 style="text-align: left;">股票创建者：<span
                                                id="issuerInPanel">${sessionScope.user.uname}</span></h4>
                                        <br/>
                                        <input id="issueStockName" type="text" class="form-control"
                                               placeholder="股票名" name="sname"
                                               required>
                                        <br/>
                                        <input id="issueStockAmount" type="text" class="form-control"
                                               placeholder="分配初始股数" name="amount" required>
                                        <br/>
                                        <button id="create" class="btn btn-md btn-primary btn-block"
                                                onclick="createStock()">创建
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="distribute">
                        <!-- 操作反馈信息 -->
                        <div style="padding-right:-15px; margin-top: 15px;">
                            <div class="alert alert-info alert-dismissable" style="margin-bottom: 15px">
                                <button type="button" class="close" data-dismiss="alert">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </button>
                                <span class="glyphicon glyphicon-info-sign"></span>
                                <strong>现在，你可以分配股票！请输入已创建过的六位数字的股票名、分配地址和您选择分配的股票数来为用户分配股票</strong>
                            </div>
                        </div>
                        <div id="distributeInTab">
                            <div class="col-sm-6 col-md-4 col-lg-4" style="margin-left: -15px;">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">发行者分配股票</h3>
                                    </div>
                                    <div class="panel-body nostyle">
                                        <input id="distributeStockName" type="text" class="form-control"
                                               placeholder="分配股票名" required>
                                        <br/>
                                        <input id="distributeTarget" type="text" class="form-control"
                                               placeholder="分配地址"
                                               required>
                                        <br/>
                                        <input id="distributeAmount" type="text" class="form-control"
                                               placeholder="分配股数"
                                               required>
                                        <br/>
                                        <button id="distributeByIssuer" class="btn btn-md btn-primary btn-block"
                                                onclick="distributeByIssuer()">分配
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 交易模块 -->
            <div id="transactionContent">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#transfer" data-toggle="tab">股票买卖</a>
                    </li>
                    <li>
                        <a href="#delegate" data-toggle="tab">股票代理</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="transfer">
                        <!-- 操作反馈信息 -->
                        <div style="padding-right:-15px; margin-top: 15px;">
                            <div class="alert alert-info alert-dismissable" style="margin-bottom: 15px">
                                <button type="button" class="close" data-dismiss="alert">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </button>
                                <span class="glyphicon glyphicon-info-sign"></span>
                                <strong>现在，你可以卖出股票！请输入您拥有的六位数字的股票名、转让地址和您选择转让的股票数来为用户转让股票</strong>
                            </div>
                        </div>
                        <div id="transferInTab">
                            <div class="col-sm-6 col-md-4 col-lg-4" style="margin-left: -15px;">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">转让股票</h3>
                                    </div>
                                    <div class="panel-body nostyle">
                                        <input id="transferStockName" type="text" class="form-control" placeholder="股票名"
                                               required>
                                        <br/>
                                        <input id="transferStockTarget" type="text" class="form-control"
                                               placeholder="转让股票地址" required>
                                        <br/>
                                        <input id="transferStockAmount" type="text" class="form-control"
                                               placeholder="转让股票数" required>
                                        <br/>
                                        <button id="transferStock" onclick="transferStock()"
                                                class="btn btn-md btn-primary btn-block">转让
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="delegate">
                        <!-- 操作反馈信息 -->
                        <div style="padding-right:-15px; margin-top: 15px;">
                            <div class="alert alert-info alert-dismissable" style="margin-bottom: 15px">
                                <button type="button" class="close" data-dismiss="alert">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </button>
                                <span class="glyphicon glyphicon-info-sign"></span>
                                <strong>现在，你可以让别人代理自己的股票！请输入您拥有的六位数字的股票名、代理人地址和您选择代理的股票数来为请求代理股票</strong>
                            </div>
                        </div>
                        <div id="delegateInTab">
                            <div class="col-sm-6 col-md-4 col-lg-4" style="margin-left: -15px;">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">代理股票</h3>
                                    </div>
                                    <div class="panel-body nostyle">
                                        <input id="delegateStockName" type="text" class="form-control" placeholder="股票名"
                                               required>
                                        <br/>
                                        <input id="delegateStockTarget" type="text" class="form-control"
                                               placeholder="代理人地址" required>
                                        <br/>
                                        <input id="delegateStockAmount" type="text" class="form-control"
                                               placeholder="代理股票数" required>
                                        <br/>
                                        <%--bug 直接调用函数delegateStock报错--%>
                                        <button id="delegateStock" onclick="MydelegateStock()"
                                                class="btn btn-md btn-primary btn-block">代理
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 查询模块 -->
            <div id="searchContent">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#amount" data-toggle="tab">查询股票余额</a>
                    </li>
                    <li>
                        <a href="#chairperson" data-toggle="tab" onclick="showChairperson()">查询管理者账户</a>
                    </li>
                    <li>
                        <a href="#stockname" data-toggle="tab">查询股票名</a>
                    </li>
                    <li>
                        <a href="#issuer" data-toggle="tab">查询股票发行者</a>
                    </li>
                    <li>
                        <a href="#agents" data-toggle="tab">查询代理量</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="amount">
                        <!-- 操作反馈信息 -->
                        <div style="padding-right:-15px; margin-top: 15px;">
                            <div class="alert alert-info alert-dismissable" style="margin-bottom: 15px">
                                <button type="button" class="close" data-dismiss="alert">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </button>
                                <span class="glyphicon glyphicon-info-sign"></span>
                                <strong>现在，你可以查询股票拥有量！请输入您期望查询的地址和股票名来为查询该地址对应的股票数</strong>
                            </div>
                        </div>
                        <div id="AmountInTab">
                            <div class="col-sm-6 col-md-4 col-lg-4" style="margin-left: -15px;">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">查询股票余额</h3>
                                    </div>
                                    <div class="panel-body nostyle">
                                        <input id="SearchAmount_StockOwner" type="text" class="form-control"
                                               placeholder="拥有者" required>
                                        <br/>
                                        <input id="SearchAmount_StockName" type="text" class="form-control"
                                               placeholder="股票名" required>
                                        <br/>
                                        <button id="SearchAmount" class="btn btn-md btn-primary btn-block"
                                                data-toggle="modal" data-target="#SearchAmountModal">查询
                                        </button>
                                    </div>
                                    <!-- 模态弹窗 -->
                                    <div class="modal fade" id="SearchAmountModal">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal">
                                                        <span class="glyphicon glyphicon-remove"></span>
                                                    </button>
                                                    <h4 class="modal-title">查询股票余额</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <h4></h4>
                                                    <h4></h4>
                                                    <h4></h4>
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
                        </div>
                    </div>
                    <div class="tab-pane fade" id="chairperson">
                        <div class="col-sm-6 col-md-4 col-lg-4" style="margin-left: -15px;">
                            <!-- 模态弹窗 -->
                            <div class="modal fade" id="SearchChairpersonModal">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">
                                                <span class="glyphicon glyphicon-remove"></span>
                                            </button>
                                            <h4 class="modal-title">查询管理者账户</h4>
                                        </div>
                                        <div class="modal-body">
                                            <h4></h4>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default btn-lg" data-dismiss="modal">关闭
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="stockname">
                        <!-- 操作反馈信息 -->
                        <div style="padding-right:-15px; margin-top: 15px;">
                            <div class="alert alert-info alert-dismissable" style="margin-bottom: 15px">
                                <button type="button" class="close" data-dismiss="alert">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </button>
                                <span class="glyphicon glyphicon-info-sign"></span>
                                <strong>现在，你可以查询用户拥有的所有股票名！请输入您期望查询的地址</strong>
                            </div>
                        </div>
                        <div class="col-sm-6 col-md-4 col-lg-4" style="margin-left: -15px;">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">查询股票名</h3>
                                </div>
                                <div class="panel-body nostyle">
                                    <input id="SearchName_StockOwner" type="text" class="form-control" placeholder="拥有者"
                                           required>
                                    <br/>
                                    <button id="SearchName" class="btn btn-md btn-primary btn-block" data-toggle="modal"
                                            data-target="#SearchNameModal">查询
                                    </button>
                                </div>
                                <!-- 模态弹窗 -->
                                <div class="modal fade" id="SearchNameModal">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">
                                                    <span class="glyphicon glyphicon-remove"></span>
                                                </button>
                                                <h4 class="modal-title">查询股票名</h4>
                                            </div>
                                            <div class="modal-body">
                                                <table class="table table-striped table-responsive">
                                                    <thead>
                                                    <tr>
                                                        <th>拥有者</th>
                                                        <th>股票名</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    </tbody>
                                                </table>
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
                    </div>
                    <div class="tab-pane fade" id="issuer">
                        <!-- 操作反馈信息 -->
                        <div style="padding-right:-15px; margin-top: 15px;">
                            <div class="alert alert-info alert-dismissable" style="margin-bottom: 15px">
                                <button type="button" class="close" data-dismiss="alert">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </button>
                                <span class="glyphicon glyphicon-info-sign"></span>
                                <strong>现在，你可以查询股票的发行者！请输入您期望查询的股票拥有者地址和股票名</strong>
                            </div>
                        </div>
                        <div class="col-sm-6 col-md-4 col-lg-4" style="margin-left: -15px;">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">查询股票发行者</h3>
                                </div>
                                <div class="panel-body nostyle">
                                    <input id="SearchIssuer_StockOwner" type="text" class="form-control"
                                           placeholder="拥有者" required>
                                    <br/>
                                    <input id="SearchIssuer_StockName" type="text" class="form-control"
                                           placeholder="股票名" required>
                                    <br/>
                                    <button id="SearchIssuer" class="btn btn-md btn-primary btn-block"
                                            data-toggle="modal" data-target="#SearchIssuerModal">查询
                                    </button>
                                </div>
                                <!-- 模态弹窗 -->
                                <div class="modal fade" id="SearchIssuerModal">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">
                                                    <span class="glyphicon glyphicon-remove"></span>
                                                </button>
                                                <h4 class="modal-title">查询股票发行者</h4>
                                            </div>
                                            <div class="modal-body">
                                                <h4></h4>
                                                <h4></h4>
                                                <h4></h4>
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
                    </div>
                    <div class="tab-pane fade" id="agents">
                        <!-- 操作反馈信息 -->
                        <div style="padding-right:-15px; margin-top: 15px;">
                            <div class="alert alert-info alert-dismissable" style="margin-bottom: 15px">
                                <button type="button" class="close" data-dismiss="alert">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </button>
                                <span class="glyphicon glyphicon-info-sign"></span>
                                <strong>现在，你可以查询股票的代理量！请输入您期望查询的股票代理者地址和股票名</strong>
                            </div>
                        </div>
                        <div class="col-sm-6 col-md-4 col-lg-4" style="margin-left: -15px;">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">查询股票代理量</h3>
                                </div>
                                <div class="panel-body nostyle">
                                    <input id="SearchOwner_Address" type="text" class="form-control"
                                           placeholder="被代理者" required>
                                    <br/>
                                    <input id="SearchAgent_Address" type="text" class="form-control"
                                           placeholder="代理者" required>
                                    <br/>
                                    <input id="SearchAgent_StockName" type="text" class="form-control"
                                           placeholder="代理股票名" required>
                                    <br/>
                                    <button id="SearchAgent" class="btn btn-md btn-primary btn-block"
                                            data-toggle="modal" data-target="#SearchAgentModal">查询
                                    </button>
                                </div>
                                <!-- 模态弹窗 -->
                                <div class="modal fade" id="SearchAgentModal">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">
                                                    <span class="glyphicon glyphicon-remove"></span>
                                                </button>
                                                <h4 class="modal-title">查询股票代理量</h4>
                                            </div>
                                            <div class="modal-body">
                                                <h4></h4>
                                                <h4></h4>
                                                <h4></h4>
                                                <h4></h4>
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
                    </div>
                </div>
            </div>
            <!-- 操作反馈信息 -->
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="alert">
                <div class="alert alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert">
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>
                    <strong id="alertMessage"></strong><a id="alertTransaction" data-toggle="modal"
                                                          data-target="#TXInfo" class="alert-link"></a>
                </div>
                <div class="modal fade" id="TXInfo">
                    <div class="modal-dialog" style="width: 60%;">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </button>
                                <h3 class="modal-title"><strong>交易详细信息</strong></h3>
                            </div>
                            <div class="modal-body">
                                <table class="table table-responsive table-striped table-hover">
                                    <thead>
                                    <tr>
                                        <th width="30%"
                                            style="word-break:break-all; word-wrap:break-word; white-space:inherit">
                                            交易属性名
                                        </th>
                                        <th width="30%"
                                            style="word-break:break-all; word-wrap:break-word; white-space:inherit">属性含义
                                        </th>
                                        <th width="30%"
                                            style="word-break:break-all; word-wrap:break-word; white-space:inherit">属性值
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default btn-lg" data-dismiss="modal">关闭
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

</script>
</body>
</html>

