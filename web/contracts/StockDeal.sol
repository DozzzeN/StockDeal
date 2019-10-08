pragma solidity ^0.4.23;
pragma experimental ABIEncoderV2;
// 一个合约代表一个股票
contract StockDeal {
    struct Stock {
        uint256 name; // 股票名
        address issuer; //发行者
        uint amount; //总数：由发行时指定
    }

    struct Agent {
        address agent;
        // 一个合约只代理一种股票
        Stock stock; // Stock[] stock;
    }

    // 对于状态变量，不能设置为 external ，默认是 internal 不可从其他合约中访问 如何调用其他合约中的值（查询）
    //mapping(address => mapping(uint256 => Stock[])) public users; //用户
    mapping(address => Stock[]) users; // 普通用户 amount：余额（转让股票账户）

    mapping(address => Stock) stocks; // 股票发行者（发行者与某个股票一一对应） amount:总发行量
    address public chairperson; // 发行者

    mapping(address => Agent[]) agents; // 用户的所有代理者（代理股票账户）
    Agent[] myallagents; // 用户所有的代理者集合
    uint256[] public namecollection; // 用户的所有股票名称的集合

    // 版本1：一只股票只发行一次
    function createStock (
        uint256 _name,
        uint _amount
    ) public {
        chairperson = msg.sender;
        // 调用合约者为发行者，只有发行者（管理员）可以创建合约来发行股票
        stocks[chairperson].name = _name;
        stocks[chairperson].issuer = chairperson;
        stocks[chairperson].amount = _amount;
        // 发行者也是普通用户，增加其用户账户
        users[chairperson].push(Stock({
            name : _name,
            issuer : chairperson,
            amount : _amount
            }));
    }

    // 只能分配一次 因为要给用户开户：user.push(0)
    // 由发行者分配股票给其他用户
    function distributeByIssuer (
        uint256 _name, // 股票名
        address _target, // 分配对象地址
        uint _amount  // 分配股数
    ) public {
        // 分配者必须是发行者
        require(
            msg.sender == chairperson,
            "Only chairperson can give stock to others."
        );

        // 不能给自己分配
        require(
            msg.sender != _target,
            "Can't give it to yourself."
        );

        users[_target].push(Stock({
            name : _name,
            issuer : msg.sender,
            amount : 0
            }));

        uint length = users[_target].length;
        for (uint i = 0; i < length; i++) {
            Stock storage stock = users[chairperson][i];
            if (stock.name == _name) {
                // 分配金额必须大于余额
                require(
                    stock.amount > _amount,
                    "Your balance is not enough."
                );
                stock.amount -= _amount;
            }
        }

        for (uint j = 0; j < length; j++) {
            Stock storage stock2 = users[_target][j];
            if (stock2.name == _name) {
                stock2.amount += _amount;
            }
        }

    }

    // 转让股票所有权（股票买卖）
    // 目标未接受过股票（不存在账户则创建账户，后进行普通转让）
    // 目标拥有账户后者进行普通转让
    function transfer (
        uint256 _name, // 股票名
        address _target, // 转让目的地址
        uint _amount // 转让股数
    ) public {
        // 不能给自己转让
        require(
            msg.sender != _target,
            "Can't transfer it to yourself."
        );

        uint targetlength = users[_target].length;
        uint msglength = users[msg.sender].length;
        for (uint i = 0; i < msglength; i++) {
            Stock storage stock = users[msg.sender][i];
            if (stock.name == _name) {
                // 分配金额必须大于余额
                require(
                    stock.amount > _amount,
                    "Your balance is not enough."
                );
                stock.amount -= _amount;
            }
        }

        bool flag = false;
        for (uint j = 0; j < targetlength; j++) {
            if (users[_target][j].name == _name) {
                users[_target][j].amount += _amount;
                flag = true;
                break;
            }
        }
        if (flag == false) {
            users[_target].push(Stock({
            name : _name,
            issuer : getStockIssuer(msg.sender, _name), //获得股票发行者
            amount : _amount
            }));
        }

        // 收到指定数量的以太币
        //_target.transfer(msg.value);
    }

    // 代理者转让股票所有权（代理者进行股票买卖）代理者的代理账户扣除相应股票数，目的地址的股票账户增加相应股票数
    function transferDelegate (
        uint256 _name, // 股票名
        address _target, // 转让目的地址
        uint _amount // 转让股数
    ) public {
        // 不能给自己转让
        require(
            msg.sender != _target,
            "Can't transfer it to yourself."
        );

        uint targetlength = users[_target].length;
        uint msglength = agents[msg.sender].length;

        for (uint i = 0; i < msglength; i++) {
            Agent storage _agent = agents[msg.sender][i];
            if (_agent.stock.name == _name) {
		   // 分配金额必须大于余额
                require(
                    _agent.stock.amount > _amount,
                    "Your balance is not enough."
                );
                _agent.stock.amount -= _amount;
            }
        }

        bool flag = false;
        for (uint j = 0; j < targetlength; j++) {
            if (users[_target][j].name == _name) {
                users[_target][j].amount += _amount;
                flag = true;
                break;
            }
        }
        if (flag == false) {
            users[_target].push(Stock({
                name : _name,
                issuer : getStockIssuer(msg.sender, _name), //获得股票发行者
                amount : _amount
                }));
        }

        // 收到指定数量的以太币
        //_target.transfer(msg.value);
    }


    // 首次代理：分配代理者账户、执行代理转账，代理注册过后则进行普通代理转账
    function firstDelegate (
        uint256 _name, // 股票名
        address _target, // 代理人
        uint _amount // 代理股数
    ) public {
        // 不能给自己代理
        require(
            msg.sender != _target,
            "Can't transfer it to yourself."
        );

        address _issuer = getStockIssuer(msg.sender, _name);

        // 建立代理者
        Stock memory _stock = Stock({
            name : _name,
            issuer : _issuer,
            amount : _amount
            });

        // 代理账户添加信息
        agents[msg.sender].push(Agent({
            agent : _target,
            stock : _stock
            }));

        // 代理分配
        uint length = users[msg.sender].length;
        for (uint i = 0; i < length; i++) {
            Stock storage stock = users[msg.sender][i];
            if (stock.name == _name) {
                // 分配金额必须大于余额
                require(
                    stock.amount > _amount,
                    "Your balance is not enough."
                );
                stock.amount -= _amount;
            }
        }

        // 发送指定数量的以太币（代理手续费）
        //_target.transfer(msg.value);
    }

    // 对同一个代理者同一个股票执行代理追加（已经注册过）
    function appendDelegate (
        uint256 _name,
        address _agent,
        uint _amount
    ) public {
        // 代理分配
        uint length = users[msg.sender].length;
        for (uint i = 0; i < length; i++) {
            Stock storage stock = users[msg.sender][i];
            if (stock.name == _name) {
                // 分配金额必须大于余额
                require(
                    stock.amount > _amount,
                    "Your balance is not enough."
                );
                stock.amount -= _amount;
            }
        }

        length = agents[msg.sender].length;
        for (uint j = 0; j < length; j++) {
            if ((agents[msg.sender][j].agent == _agent) && (agents[msg.sender][j].stock.name == _name)) {
                agents[msg.sender][j].stock.amount += _amount;
            }
        }

        // 发送指定数量的以太币（代理手续费）
        //_agent.transfer(msg.value);
    }


    /// 查询部分 查询需要某个股票需要在该股票创建的智能合约下（最好与分配属于同一个合约）
    // 获取用户的某个股票数
    function getStockAmount (
        address _owner, // 拥有者地址
        uint256 _name // 股票名
    ) public view returns (uint _amount) {
        uint length = users[_owner].length;
        for (uint i = 0; i < length; i++) {
            if (users[_owner][i].name == _name) {
                _amount = users[_owner][i].amount;
            }
        }
    }

    // 获取用户被某个代理者代理的某个股票的数量（先查询该用户所有的代理者，再调用该函数查询所有代理股票的数量）
    function getStockAgentAmount (
        address _owner,// 多个地址有错误？
        address _agent, // 代理者地址
        uint256 _name // 代理股票名
    ) public view returns (uint _amount) {
        uint length = agents[_owner].length;
        for (uint i = 0; i < length; i++) {
            if ((agents[_owner][i].agent == _agent) && (agents[_owner][i].stock.name == _name)) {
                _amount = agents[_owner][i].stock.amount;
            }
        }
    }

    // 获得股票发行者 普通用户的股票中检索相应股票编号，然后返回该股票的发行者
    function getStockIssuer (
        address _owner, // 股票拥有者
        uint256 _name // 股票名
    ) public view returns (address _issuer) {
        uint length = users[_owner].length;
        for (uint i = 0; i < length; i++) {
            if (users[_owner][i].name == _name) {
                _issuer = users[_owner][i].issuer;
            }
        }
    }

    // 获得股票名称集合 普通用户的股票中检索所有的股票名
    function getStockName (
        address _owner  // 股票拥有者
    ) public {
        uint length = users[_owner].length;
        for (uint i = 0; i < length; i++) {
            namecollection.push(users[_owner][i].name);
        }
    }

    // 查询我的代理者（有bug，直接使用agent查看）
    function getAgents (
    ) public {
        uint length = agents[msg.sender].length;
        for (uint i = 0; i < length; i++) {
            // 使用数组下标赋值也有错误？
            myallagents.push(Agent({
                agent : agents[msg.sender][i].agent,
                stock : agents[msg.sender][i].stock
                }));
        }
    }
}