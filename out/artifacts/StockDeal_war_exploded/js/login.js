function register() {
    var uname = $("#uname").val();
    var pwd = $("#pwd").val();
    var role = $("input[name='role']:checked").val();
    var address = null;
    var userAmount = null;

    var CONTRACT_ADDRESS = "0x5f77c8297b590b5d0cf4b13895b194d35e5a44ff";
    var web3 = new Web3(new Web3.providers.HttpProvider("http://127.0.0.1:7545"));

    $.getJSON("./build/contracts/StockDeal.json", function (data) {
        //异步写入数据库
        var ajax;
        if (window.XMLHttpRequest) {//火狐
            ajax = new XMLHttpRequest();
        } else if (window.ActiveXObject) {//ie
            ajax = new ActiveXObject("Msxml2.XMLHTTP");
        }
        ajax.open("get", "user?oper=amount", true);
        ajax.send(null);

        ajax.onreadystatechange = function () {
            if (ajax.readyState == 4) {
                if (ajax.status == 200 || ajax.status == 0) {
                    // var privateKey = ajax.responseText;
                    // privateKey = '0x' + privateKey;
                    // console.log(privateKey);
                    // //要使用这些创建用户的方法必须是web3@1.0版本，目前是0.2版本
                    // //web3.eth.accounts.privateKeyToAccount(privateKey);
                    // //address = web3.eth.accounts.create();
                    // console.log(address);

                    userAmount = ajax.responseText;
                    //返回一个新的用户地址，默认已经有50个预备地址
                    address = web3.eth.accounts[userAmount];
                    alert(address);
                }
            }
        };
    });

    window.setTimeout(function () {
        var ajax2;
        if (window.XMLHttpRequest) {//火狐
            ajax2 = new XMLHttpRequest();
        } else if (window.ActiveXObject) {//ie
            ajax2 = new ActiveXObject("Msxml2.XMLHTTP");
        }
        //浏览器中的ajax是并行处理的，因此若想要第一个ajax执行完再执行第二个ajax
        //解决一：将第二个设为异步
        //解决二：在第一个ajax中设置succes的回调函数
        ajax2.open("get", "user?oper=register&uname=" + uname + "&pwd=" + pwd + "&role=" + role + "&address=" + address, false);
        ajax2.send(null);

        $(".modal-footer").click();

        location.reload();
    },500);
}

//登录时就加载用户数据：解决在html页面加载之前需要ajax请求数据来渲染页面的问题
$(document).ready(function () {
    //异步读取数据库
    var ajax;
    if (window.XMLHttpRequest) {//火狐
        ajax = new XMLHttpRequest();
    } else if (window.ActiveXObject) {//ie
        ajax = new ActiveXObject("Msxml2.XMLHTTP");
    }
    ajax.open("get", "user?oper=show", true);
    ajax.send(null);
    ajax.onreadystatechange = function () {
        if (ajax.readyState == 4) {
            if (ajax.status == 200) {
            }
        }
    }
});
