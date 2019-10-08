package service.impl;

import dao.StockDao;
import dao.impl.StockDaoImpl;
import org.apache.log4j.Logger;
import pojo.Stock;
import service.StockService;

public class StockServiceImpl implements StockService {
    //日志处理对象
    Logger logger = Logger.getLogger(StockServiceImpl.class);
    //dao层对象
    StockDao sd = new StockDaoImpl();

    //创建股票
    @Override
    public int createStockService(Stock s) {
        logger.debug(s.getIssuer() + "：发起创建股票请求");
        int index = sd.createStockDao(s);
        if (index > 0) {
            logger.debug(s.getIssuer() + "创建股票成功");
        } else {
            logger.debug(s.getIssuer() + "创建股票失败");
        }
        return index;
    }

    @Override
    public int maxIdService() {
        logger.debug("发起查询股票ID请求");
        int maxId = sd.maxIdDao();
        if (maxId > 0) {
            logger.debug("查询股票ID成功");
        } else {
            logger.debug("查询股票ID失败");
        }
        return maxId;
    }

    @Override
    public int selectBysnameService(int sname) {
        logger.debug("发起查询股票是否同名请求");
        int index = sd.selectBysnameDao(sname);
        if (index > 0) {
            logger.debug("查询股票是否同名成功");
        } else {
            logger.debug("查询股票是否同名失败");
        }
        return index;
    }

    @Override
    public int selectSidBySnameService(int sname) {
        logger.debug("发起根据股票名查询股票ID请求");
        int sid = sd.selectSidBySnameDao(sname);
        if (sid > 0) {
            logger.debug("查询股票ID成功");
        } else {
            logger.debug("查询股票ID失败");
        }
        return sid;
    }

    @Override
    public String selectIssuerBySnameService(int sname) {
        logger.debug("根据股票名查询股票发行者地址请求");
        String issuer = sd.selectIssuerBySnameDao(sname);
        if (issuer != null) {
            logger.debug("根据股票名查询股票发行者地址成功");
        } else {
            logger.debug("根据股票名查询股票发行者地址失败");
        }
        return issuer;
    }
}
