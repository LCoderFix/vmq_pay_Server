package com.dong.vmqpay.service;

import com.dong.vmqpay.mapper.PriceMapper;
import com.dong.vmqpay.mapper.WebOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.logging.Logger;

@Service
public class TimerService {

    @Autowired
    private WebOrderMapper orderMapper;
    @Autowired
    private PriceMapper priceMapper;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Scheduled(fixedRate = 30 * 1000)
    @Transactional
    public void cleanOrder() throws Exception {
        logger.info("开始清理过期订单");
        long timeNow = new Date().getTime();
        long expiredTime = timeNow - 5 * 60 * 1000;
        int priceCount = priceMapper.delPrice(expiredTime);
        int orderCount = orderMapper.updateExpiredOrderStatus(expiredTime);

        logger.info(String.format("清理过期订单结束，共清理%s个订单", orderCount));

    }
}
