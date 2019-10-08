var BigNumber = require('bignumber.js'); ///bignumber.js只支持15字符以内的数字
var flag = true;
$(document).ready(function () {
    var SmallPanel = $("#SmallPanel");
    SmallPanel.hide();
});

var CONTRACT_ADDRESS = "0x5f77c8297b590b5d0cf4b13895b194d35e5a44ff";
var web3 = new Web3(new Web3.providers.HttpProvider("http://127.0.0.1:7545"));

function resize() {
    var BigPanel = $("#BigPanel");
    var SmallPanel = $("#SmallPanel");
    var gridLeft = $("#gridLeft");
    var gridRight = $("#gridRight");
    var icon = $("#IconPanel>.panel>.panel-footer>span");
    if (flag) {
        BigPanel.hide();
        SmallPanel.show();

        gridLeft.css("width", "3%");
        gridRight.css("width", "97%");

        icon.removeClass("glyphicon-eye-close");
        icon.addClass("glyphicon-eye-open");
        flag = false;
    } else {

        BigPanel.show();
        SmallPanel.hide();

        gridLeft.css("width", "");
        gridRight.css("width", "");

        icon.removeClass("glyphicon-eye-open");
        icon.addClass("glyphicon-eye-close");
        flag = true;
    }
}

$(document).ready(function () {
    $("#manageContent").show();
    $("#transactionContent").hide();
    $("#searchContent").hide();

    $("#settingInNav>li:nth-child(4)>a").bind("click", function () {
        $("#manageContent").show();
        $("#transactionContent").hide();
        $("#searchContent").hide();
        $('#manageContent>ul>li:eq(0) a').tab("show");
    });

    var One = $("#One");
    One.bind("click", function () {
        $("#manageContent").show();
        $("#transactionContent").hide();
        $("#searchContent").hide();
        $('#manageContent>ul>li:eq(0) a').tab("show");
    });

    var issue = $("#collapseOneFirst");
    issue.bind("click", function () {
        $("#manageContent").show();
        $("#transactionContent").hide();
        $("#searchContent").hide();
        $('#manageContent>ul>li:eq(0) a').tab("show");
    });

    var distribute = $("#collapseOneSecond");
    distribute.bind("click", function () {
        $("#manageContent").show();
        $("#transactionContent").hide();
        $("#searchContent").hide();
        $('#manageContent>ul>li:eq(1) a').tab("show");
    });

    var Two = $("#Two");
    Two.bind("click", function () {
        $("#transactionContent").show();
        $("#manageContent").hide();
        $("#searchContent").hide();
        $('#transactionContent>ul>li:eq(0) a').tab("show");
    });

    var transfer = $("#collapseTwoFirst");
    transfer.bind("click", function () {
        $("#transactionContent").show();
        $("#manageContent").hide();
        $("#searchContent").hide();
        $('#transactionContent>ul>li:eq(0) a').tab("show");
    });

    var delegate = $("#collapseTwoSecond");
    delegate.bind("click", function () {
        $("#transactionContent").show();
        $("#manageContent").hide();
        $("#searchContent").hide();
        $('#transactionContent>ul>li:eq(1) a').tab("show");
    });

    var Three = $("#Three");
    Three.bind("click", function () {
        $("#searchContent").show();
        $("#manageContent").hide();
        $("#transactionContent").hide();
        $('#searchContent>ul>li:eq(0) a').tab("show");
    });

    var amount = $("#collapseThreeFirst");
    amount.bind("click", function () {
        $("#searchContent").show();
        $("#manageContent").hide();
        $("#transactionContent").hide();
        $('#searchContent>ul>li:eq(0) a').tab("show");
    });

    var chairperson = $("#collapseThreeSecond");
    chairperson.bind("click", function () {
        $("#searchContent").show();
        $("#manageContent").hide();
        $("#transactionContent").hide();
        $('#searchContent>ul>li:eq(1) a').tab("show");
    });

    var stockname = $("#collapseThreeThird");
    stockname.bind("click", function () {
        $("#searchContent").show();
        $("#manageContent").hide();
        $("#transactionContent").hide();
        $('#searchContent>ul>li:eq(2) a').tab("show");
    });

    var issuer = $("#collapseThreeFourth");
    issuer.bind("click", function () {
        $("#searchContent").show();
        $("#manageContent").hide();
        $("#transactionContent").hide();
        $('#searchContent>ul>li:eq(3) a').tab("show");
    });

    var agents = $("#collapseThreeFifth");
    agents.bind("click", function () {
        $("#searchContent").show();
        $("#manageContent").hide();
        $("#transactionContent").hide();
        $('#searchContent>ul>li:eq(4) a').tab("show");
    });

});

//右上角的发行股票
function issue() {
    $("#manageContent").show();
    $("#transactionContent").hide();
    $("#searchContent").hide();
    $('#manageContent>ul>li:eq(0) a').tab("show");
}

$(document).ready(function () {
    var One = $("#smallOne>div:eq(1)>button:eq(0)");
    One.bind("click", function () {
        $("#manageContent").show();
        $("#transactionContent").hide();
        $("#searchContent").hide();
        $('#manageContent>ul>li:eq(0) a').tab("show");
    });

    var issue = $("#smallOne>div:eq(1)>button:eq(1)");
    issue.bind("click", function () {
        $("#manageContent").show();
        $("#transactionContent").hide();
        $("#searchContent").hide();
        $('#manageContent>ul>li:eq(0) a').tab("show");
    });

    var distribute = $("#smallOne>div:eq(1)>button:eq(2)");
    distribute.bind("click", function () {
        $("#manageContent").show();
        $("#transactionContent").hide();
        $("#searchContent").hide();
        $('#manageContent>ul>li:eq(1) a').tab("show");
    });

    var Two = $("#smallTwo>div:eq(1)>button:eq(0)");
    Two.bind("click", function () {
        $("#transactionContent").show();
        $("#manageContent").hide();
        $("#searchContent").hide();
        $('#transactionContent>ul>li:eq(0) a').tab("show");
    });
    var transfer = $("#smallTwo>div:eq(1)>button:eq(1)");
    transfer.bind("click", function () {
        $("#transactionContent").show();
        $("#manageContent").hide();
        $("#searchContent").hide();
        $('#transactionContent>ul>li:eq(0) a').tab("show");
    });

    var delegate = $("#smallTwo>div:eq(1)>button:eq(2)");
    delegate.bind("click", function () {
        $("#transactionContent").show();
        $("#manageContent").hide();
        $("#searchContent").hide();
        $('#transactionContent>ul>li:eq(1) a').tab("show");
    });

    var Three = $("#smallThree>div:eq(1)>button:eq(0)");
    Three.bind("click", function () {
        $("#searchContent").show();
        $("#manageContent").hide();
        $("#transactionContent").hide();
        $('#searchContent>ul>li:eq(0) a').tab("show");
    });

    var amount = $("#smallThree>div:eq(1)>button:eq(1)");
    amount.bind("click", function () {
        $("#searchContent").show();
        $("#manageContent").hide();
        $("#transactionContent").hide();
        $('#searchContent>ul>li:eq(0) a').tab("show");
    });

    var chairperson = $("#smallThree>div:eq(1)>button:eq(2)");
    chairperson.bind("click", function () {
        $("#searchContent").show();
        $("#manageContent").hide();
        $("#transactionContent").hide();
        $('#searchContent>ul>li:eq(1) a').tab("show");
    });

    var stockname = $("#smallThree>div:eq(1)>button:eq(3)");
    stockname.bind("click", function () {
        $("#searchContent").show();
        $("#manageContent").hide();
        $("#transactionContent").hide();
        $('#searchContent>ul>li:eq(2) a').tab("show");
    });

    var issuer = $("#smallThree>div:eq(1)>button:eq(4)");
    issuer.bind("click", function () {
        $("#searchContent").show();
        $("#manageContent").hide();
        $("#transactionContent").hide();
        $('#searchContent>ul>li:eq(3) a').tab("show");
    });

    var agents = $("#smallThree>div:eq(1)>button:eq(5)");
    agents.bind("click", function () {
        $("#searchContent").show();
        $("#manageContent").hide();
        $("#transactionContent").hide();
        $('#searchContent>ul>li:eq(4) a').tab("show");
    });
});

//查询管理者账户
function showChairperson() {
    $("#alert").hide();

    var CONTRACT_ADDRESS = "0x5f77c8297b590b5d0cf4b13895b194d35e5a44ff";
    var web3 = new Web3(new Web3.providers.HttpProvider("http://127.0.0.1:7545"));

    function childFunction() {
        $.getJSON("./build/contracts/StockDeal.json", function (data) {
            //创建合约
            var StockDeal = web3.eth.contract(data.abi).at(CONTRACT_ADDRESS);
            var param = { //扣取gas的账户地址
                from: web3.eth.accounts[0],
                gas: 3000000 //不限制gas值会产生out of gas的错误
            };
            //调用合约函数
            var result = StockDeal.chairperson();
            console.log(result);
            $("#SearchChairpersonModal>.modal-dialog>.modal-content>.modal-body>h4:nth-child(1)").html("管理者账户：" + result);
        });
        $("#SearchChairpersonModal").modal('show');
    }

    //激活tab标签：显示前激活模态弹窗
    $('a[href="#chairperson"]').on('show.bs.tab', childFunction());

    //取消事件绑定，可以多次点击
    $('a[href="#chairperson"]').unbind('show.bs.tab', childFunction());

}

$(document).ready(function () {
    $("#alert").hide();
    var CONTRACT_ADDRESS = "0x5f77c8297b590b5d0cf4b13895b194d35e5a44ff";
    var web3 = new Web3(new Web3.providers.HttpProvider("http://127.0.0.1:7545"));

    //查询股票余额
    $("#SearchAmount").click(function () {
        //禁止模态弹出
        $("#SearchAmount").removeAttr("data-target");
        var owner = $("#SearchAmount_StockOwner").val();
        var name = $("#SearchAmount_StockName").val();
        if (!owner || !name) {
            alert("拥有者及股票名不能为空");
            return;
        }
        $.getJSON("./build/contracts/StockDeal.json", function (data) {
            //创建合约
            var StockDeal = web3.eth.contract(data.abi).at(CONTRACT_ADDRESS);
            var param = { //扣取gas的账户地址
                from: web3.eth.accounts[0],
                gas: 3000000 //不限制gas值会产生out of gas的错误
            };
            //调用合约函数
            var result = StockDeal.getStockAmount(owner, name, param);
            console.log(result["c"][0]); //返回的result是一个数组 如下：{ s: 1, e: 4, c: [ 10000 ] }
            $("#SearchAmountModal>.modal-dialog>.modal-content>.modal-body>h4:nth-child(1)").html("拥有者：" + owner);
            $("#SearchAmountModal>.modal-dialog>.modal-content>.modal-body>h4:nth-child(2)").html("股票名：" + name);
            $("#SearchAmountModal>.modal-dialog>.modal-content>.modal-body>h4:nth-child(3)").html("股票数：" + result["c"][0]);
            $("#SearchAmount").attr("data-target", "#SearchAmountModal");
            $("#SearchAmountModal").modal('show');
        });
    });

    //查询股票发行者
    $("#SearchIssuer").click(function () {
        //禁止模态弹出
        $("#SearchIssuer").removeAttr("data-target");
        var owner = $("#SearchIssuer_StockOwner").val();
        var name = $("#SearchIssuer_StockName").val();
        if (!owner || !name) {
            alert("拥有者及股票名不能为空");
            return;
        }

        $.getJSON("./build/contracts/StockDeal.json", function (data) {
            //创建合约
            var StockDeal = web3.eth.contract(data.abi).at(CONTRACT_ADDRESS);
            var param = { //扣取gas的账户地址
                from: web3.eth.accounts[0],
                gas: 3000000 //不限制gas值会产生out of gas的错误
            };
            //调用合约函数
            var result = StockDeal.getStockIssuer(owner, name, param);
            // console.log(result);
            $("#SearchIssuerModal>.modal-dialog>.modal-content>.modal-body>h4:nth-child(1)").html("拥有者：" + owner);
            $("#SearchIssuerModal>.modal-dialog>.modal-content>.modal-body>h4:nth-child(2)").html("股票名：" + name);
            $("#SearchIssuerModal>.modal-dialog>.modal-content>.modal-body>h4:nth-child(3)").html("股票发行者：" + result);
            $("#SearchIssuer").attr("data-target", "#SearchIssuerModal");
            $("#SearchIssuerModal").modal('show');
        });
    });

    //查询股票名
    $("#SearchName").click(function () {
        //禁止模态弹出
        $("#SearchName").removeAttr("data-target");
        var owner = $("#SearchName_StockOwner").val();
        if (!owner) {
            alert("股票名不能为空");
            return;
        }

        $.getJSON("./build/contracts/StockDeal.json", function (data) {
            //创建合约
            var StockDeal = web3.eth.contract(data.abi).at(CONTRACT_ADDRESS);
            var param = { //扣取gas的账户地址
                from: web3.eth.accounts[0],
                gas: 3000000 //不限制gas值会产生out of gas的错误
            };
            //调用合约函数
            StockDeal.getStockName(owner, param);
            //数组角标溢出不报错，故需要判断
            var begin = StockDeal.namecollection(0)["c"][0];
            // console.log(begin);
            var result = new Array();
            result[0] = begin;
            //未完成：从数据库中取出股票名
            for (var i = 1; StockDeal.namecollection(i)["c"][0] != begin; i++) {
                result[i] = StockDeal.namecollection(i)["c"][0];
                console.log(result[i]);
            }

            var target = "#SearchNameModal>.modal-dialog>.modal-content>.modal-body>table>tbody";
            //先清空表格数据
            $(target).empty();
            $(target).html("");
            for (var i in result) {
                $(target).html($(target).html() + "<tr><td>" + owner + "</td><td>" + result[i] + "</td></tr>");
            }

            $("#SearchName").attr("data-target", "#SearchNameModal");
            $("#SearchNameModal").modal('show');
        });
    });

    //查询代理量
    $("#SearchAgent").click(function () {
        //禁止模态弹出
        $("#SearchAgent").removeAttr("data-target");
        var owner = $("#SearchOwner_Address").val();
        var agent = $("#SearchAgent_Address").val();
        var name = $("#SearchAgent_StockName").val();
        if (!owner) {
            alert("被代理者不能为空");
            return;
        }

        if (!agent) {
            alert("代理者不能为空");
            return;
        }

        if (!name) {
            alert("代理股票名不能为空");
            return;
        }

        $.getJSON("./build/contracts/StockDeal.json", function (data) {
            //创建合约
            var StockDeal = web3.eth.contract(data.abi).at(CONTRACT_ADDRESS);
            var param = { //扣取gas的账户地址
                from: web3.eth.accounts[0],
                gas: 3000000 //不限制gas值会产生out of gas的错误
            };
            //调用合约函数
            var result = StockDeal.getStockAgentAmount(owner, agent, name, param);
            console.log(result);
            $("#SearchAgentModal>.modal-dialog>.modal-content>.modal-body>h4:nth-child(1)").html("被代理者：" + owner);
            $("#SearchAgentModal>.modal-dialog>.modal-content>.modal-body>h4:nth-child(2)").html("代理者：" + agent);
            $("#SearchAgentModal>.modal-dialog>.modal-content>.modal-body>h4:nth-child(3)").html("代理股票名：" + name);
            $("#SearchAgentModal>.modal-dialog>.modal-content>.modal-body>h4:nth-child(4)").html("代理股票数：" + result);
            $("#SearchAgent").attr("data-target", "#SearchAgentModal");
            $("#SearchAgentModal").modal('show');
        });
    });
});

//显示底部提示信息
function info(result, isSuccess) {
    $("#alert").hide();

    var CONTRACT_ADDRESS = "0x5f77c8297b590b5d0cf4b13895b194d35e5a44ff";
    var web3 = new Web3(new Web3.providers.HttpProvider("http://127.0.0.1:7545"));

    //提示里面显示交易地址
    $.getJSON("./build/contracts/StockDeal.json", function (data) {
        //创建合约
        $("#alert").show();
        $("#alertTransaction").html(result);
        if (isSuccess) {
            $("#alert>div").removeClass("alert-danger");
            $("#alert>div").addClass("alert-success");
            $("#alertMessage").html("成功！&nbsp;产生交易：");

            //启用链接
            $("#alertTransaction").attr("data-target", "#TXInfo");

            var target = "#TXInfo>.modal-dialog>.modal-content>.modal-body>table>tbody";
            //先清空表格数据
            $(target).empty();
            $(target).html("");
            $(target).html($(target).html() +
                "<tr><td>blockHash</td><td>交易所在区块的哈希地址</td><td style='word-break:break-all; word-wrap:break-word; white-space:inherit'>" +
                web3.eth.getTransactionReceipt(
                    result).blockHash + "</td></tr>");
            $(target).html($(target).html() +
                "<tr><td>blockNumber</td><td>交易所在区块的序号</td><td style='word-break:break-all; word-wrap:break-word; white-space:inherit'>" +
                web3.eth.getTransactionReceipt(
                    result).blockNumber + "</td></tr>");
            $(target).html($(target).html() +
                "<tr><td>from</td><td>发起人地址</td><td style='word-break:break-all; word-wrap:break-word; white-space:inherit'>" +
                web3.eth.getTransactionReceipt(
                    result).from + "</td></tr>");
            $(target).html($(target).html() +
                "<tr><td>to</td><td>接收者地址</td><td style='word-break:break-all; word-wrap:break-word; white-space:inherit'>" +
                web3.eth.getTransactionReceipt(
                    result).to + "</td></tr>");
            $(target).html($(target).html() +
                "<tr><td>transactionHash</td><td>交易哈希</td><td style='word-break:break-all; word-wrap:break-word; white-space:inherit'>" +
                web3.eth.getTransactionReceipt(
                    result).transactionHash + "</td></tr>");
            $(target).html($(target).html() +
                "<tr><td>gasUsed</td><td>交易使用的gas量</td><td style='word-break:break-all; word-wrap:break-word; white-space:inherit'>" +
                web3.eth.getTransactionReceipt(
                    result).gasUsed + "</td></tr>");
        } else {
            $("#alert>div").removeClass("alert-success");
            $("#alert>div").addClass("alert-danger");
            $("#alertMessage").html("失败！&nbsp;错误信息：");

            //链接无法点击 模态弹窗无法弹出
            $("#alertTransaction").attr("data-target", "");
            var target = "#TXInfo>.modal-dialog>.modal-content>.modal-body>table>tbody";
            //先清空表格数据
            $(target).empty();
            $(target).html("");
        }
    });
}

//发行股票
function createStock() {
    $("#alert").hide();

    var name = $("#issueStockName").val();
    var amount = $("#issueStockAmount").val();
    if (!name || !amount) {
        alert("股票名及分配股票数不能为空");
        return;
    }

    var issuer = null;
    var isOfSameName = null; //同名为-1，不同名为地址值

    //若不用ajax进行提交数据而使用form的action提交的话，相当于页面重新跳转，那么js动态加载的面板和info提示就不显示了
    $.ajax({
        url: "user?oper=create&sname=" + name + "&issuer=" + issuer + "&amount=" + amount,
        dataType: 'html',
        error: function () {
            alert("请求出错");
            return true;
        },
        success: function (data) {
            alert(data);
            isOfSameName = data;
            if (isOfSameName == -1) {
                //创建的股票名已经存在
                info("创建的股票名已经存在", false);
            } else if (isOfSameName == -2) {
                //普通用户无法创建股票
                info("普通用户无法创建股票", false);
            } else {
                createStockBlockChain();
            }
        },
        type: 'GET'
    });

    function createStockBlockChain() {
        $.getJSON("./build/contracts/StockDeal.json", function (dataContract) {
            //创建合约
            var StockDeal = web3.eth.contract(dataContract.abi).at(CONTRACT_ADDRESS);
            var param = { //扣取gas的账户地址
                //from: web3.eth.accounts[0], //ganache十个账户中的第一个地址值
                from: isOfSameName,
                gas: 3000000 //不限制gas值会产生out of gas的错误
            };
            alert(isOfSameName);
            //调用合约函数
            var result = StockDeal.createStock(name, amount, param);
            console.log(result);
            //提示里面显示交易地址
            info(result, true);

            //动态创建面板 id都是动态添加上去的
            //注意append的用法，一定是一次添加完整的元素
            //在div内添加元素不要使用append，使用html
            //不能直接将bootstrap的class类写入到append中，因为dom节点在页面加载时已经被渲染，使用委托绑定来完成，包括元素绑定的事件等
            $("#issueInTab").append('<div id="issueSuccess_' + name + '"></div>');
            $("#issueSuccess_" + name).addClass("col-sm-6 col-md-4 col-lg-4");
            $("#issueSuccess_" + name).css("margin-left", "-15px");

            $("#issueSuccess_" + name).html('<div id="issueSuccessPanel_' + name + '"></div>');
            $("#issueSuccessPanel_" + name).addClass("panel panel-default panel-success");

            $("#issueSuccessPanel_" + name).html('<div id="issueSuccessPanelHeading_' + name +
                '"><h3 id="issueSuccessPanelTitle_' + name + '">已发行股票 ' + name + '</h3></div>');
            $("#issueSuccessPanel_" + name).html($("#issueSuccessPanel_" + name).html() +
                '<div id="issueSuccessPanelBody_' + name + '"></div>');
            $("#issueSuccessPanelHeading_" + name).addClass("panel-heading");

            $("#issueSuccessPanelTitle_" + name).addClass("panel-title");

            $("#issueSuccessPanelBody_" + name).addClass("panel-body nostyle");
            $("#issueSuccessPanelBody_" + name).html(
                '<h4 class="panel-title" style="text-align: left;">股票创建者：<p style="font-style: italic;word-break: break-all;" id=Stock_' +
                name +
                '_Issuer>' + isOfSameName + '</p></h4><br/>');
            $("#issueSuccessPanelBody_" + name).html($("#issueSuccessPanelBody_" + name).html() +
                '<h4 class="panel-title" style="text-align: left;">股票总发行数：<span id=Stock_' + name + '_Amount>' + amount +
                '</span></h4>');
        });
    }
}

//分配股票
function distributeByIssuer() {
    $("#alert").hide();

    var name = $("#distributeStockName").val();
    var target = $("#distributeTarget").val();
    var amount = $("#distributeAmount").val();

    if (!name) {
        alert("分配股票名不能为空");
        return;
    }
    if (!target) {
        alert("分配地址不能为空");
        return;
    }
    if (!amount) {
        alert("分配股票数不能为空");
        return;
    }

    var issuer = null;

    $.ajax({
        url: "user?oper=distribute&sname=" + name + "&target=" + target + "&amount=" + amount,
        dataType: 'html',
        error: function () {
            alert("请求出错");
            return true;
        },
        success: function (data) {
            issuer = data;
            alert(data);
            if (data == -1) {
                //股票余额不足，无法分配
                info("股票余额不足，无法分配", false);
            } else if (data == -2) {
                //不是该股票的发行者，无法分配
                info("不是该股票的发行者，无法分配", false);
            } else if (data == -3) {
                //已经分配过，无法继续分配
                info("已经分配过，无法继续分配", false);
            } else {
                distributeByIssuerBlockChain()
            }
        },
        type: 'GET'
    });

    function distributeByIssuerBlockChain() {
        $.getJSON("./build/contracts/StockDeal.json", function (dataContract) {
            //创建合约
            var StockDeal = web3.eth.contract(dataContract.abi).at(CONTRACT_ADDRESS);
            alert(issuer);
            var param = { //扣取gas的账户地址
                from: issuer,
                gas: 3000000 //不限制gas值会产生out of gas的错误
            };
            //调用合约函数
            c.distributeByIssuer(name, target, amount, param, function (error, result) {
                if (!error) {
                    alert("分配股票成功！");
                    console.log(result);
                    //提示里面显示交易地址
                    info(result, true);
                } else {
                    alert("分配股票失败");
                    //打印详细出错信息
                    console.log(error);
                    //提示里面显示交易地址
                    info(error, false);
                }
            });
        });
    }
}

//转让股票
function transferStock() {
    var name = $("#transferStockName").val();
    var target = $("#transferStockTarget").val();
    var amount = $("#transferStockAmount").val();

    if (!name) {
        alert("转让股票名不能为空");
        return;
    }
    if (!target) {
        alert("转让地址不能为空");
        return;
    }
    if (!amount) {
        alert("转让股票数不能为空");
        return;
    }

    var issuer = null;

    $.ajax({
        url: "user?oper=transfer&sname=" + name + "&target=" + target + "&amount=" + amount,
        dataType: 'html',
        error: function () {
            alert("请求出错");
            return true;
        },
        success: function (data) {
            issuer = data;
            alert(data);
            if (data == -1) {
                //股票余额不足，无法分配
                info("股票余额不足，无法分配", false);
            } else {
                transferStockBlockChain()
            }
        },
        type: 'GET'
    });

    function transferStockBlockChain() {
        $.getJSON("./build/contracts/StockDeal.json", function (dataContract) {
            //创建合约
            var StockDeal = web3.eth.contract(dataContract.abi).at(CONTRACT_ADDRESS);
            var param = { //扣取gas的账户地址
                from: issuer,
                gas: 3000000 //不限制gas值会产生out of gas的错误
            };
            //调用合约函数
            StockDeal.transfer(name, target, amount, param, function (error, result) {
                if (!error) {
                    alert("转让股票成功！");
                    console.log(result);
                    //提示里面显示交易地址
                    info(result, true);
                } else {
                    alert("转让股票失败");
                    //打印详细出错信息
                    console.log(error);
                    //提示里面显示交易地址
                    info(error, false);
                }
            });
        });
    }
}

//代理股票
function MydelegateStock() {
    var name = $("#delegateStockName").val();
    var target = $("#delegateStockTarget").val();
    var amount = $("#delegateStockAmount").val();

    if (!name) {
        alert("代理股票名不能为空");
        return;
    }
    if (!target) {
        alert("代理地址不能为空");
        return;
    }
    if (!amount) {
        alert("代理股票数不能为空");
        return;
    }

    //调用者地址
    var issuerAddress = null;
    //错误代码
    var MyErrorFlag = null;
    //是否首次代理
    var isExistDelegate = null;

    $.ajax({
        url: "user?oper=delegate&sname=" + name + "&target=" + target + "&amount=" + amount,
        dataType: 'JSON',
        error: function () {
            alert("请求出错");
            return true;
        },
        success: function (data) {
            issuerAddress = data.issuerAddress;
            MyErrorFlag = data.MyErrorFlag;
            isExistDelegate = data.isExistDelegate;
            if (MyErrorFlag == -1) {
                //股票余额不足，无法代理
                info("股票余额不足，无法代理", false);
            } else {
                alert(issuerAddress + " " + MyErrorFlag + " " + isExistDelegate);
                delegateStockBlockChain();
            }
        },
        type: 'get'
    });

    function delegateStockBlockChain() {
        $.getJSON("./build/contracts/StockDeal.json", function (dataContract) {
            //创建合约
            var StockDeal = web3.eth.contract(dataContract.abi).at(CONTRACT_ADDRESS);
            var param = { //扣取gas的账户地址
                from: issuerAddress,
                gas: 3000000 //不限制gas值会产生out of gas的错误
            };
            if (isExistDelegate == 0) {
                //首次代理
                //调用合约函数
                StockDeal.firstDelegate(name, target, amount, param, function (error, result) {
                    if (!error) {
                        alert("首次代理股票成功！");
                        console.log(result);
                        //提示里面显示交易地址
                        info(result, true);
                    } else {
                        alert("首次代理股票失败");
                        //打印详细出错信息
                        console.log(error);
                        //提示里面显示交易地址
                        info(error, false);
                    }
                });
            } else if (isExistDelegate == 1) {
                //代理追加
                //调用合约函数
                StockDeal.appendDelegate(name, target, amount, param, function (error, result) {
                    if (!error) {
                        alert("追加代理股票成功！");
                        console.log(result);
                        //提示里面显示交易地址
                        info(result, true);
                    } else {
                        alert("追加代理股票失败");
                        //打印详细出错信息
                        console.log(error);
                        //提示里面显示交易地址
                        info(error, false);
                    }
                });
            }

        });
    }
}

$(document).ready(function () {
    //更改密码中清空缓存
    $("#change").on('show.bs.modal', function () {
        $("#spanOldPassword").hide();
        $("#oldPassword").empty();
        $("#newPassword").empty();
    });
});

