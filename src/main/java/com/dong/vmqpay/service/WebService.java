package com.dong.vmqpay.service;

import com.dong.vmqpay.mapper.PriceMapper;
import com.dong.vmqpay.mapper.WebOrderMapper;
import com.dong.vmqpay.pojo.BaseResponse;
import com.dong.vmqpay.pojo.OrderStatus;
import com.dong.vmqpay.pojo.PayOrder;
import com.dong.vmqpay.pojo.res.CreateOrderRes;
import com.dong.vmqpay.utils.Arith;
import com.dong.vmqpay.utils.ResUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Service
public class WebService {

    @Autowired
    private WebOrderMapper orderMapper;
    @Autowired
    private PriceMapper priceMapper;
    private static Logger logger = Logger.getLogger("WebService.class");


    private boolean isContainPrice(int type, double price) {
        return priceMapper.getCountByPrice(type, price) > 0;
    }

    public BaseResponse createOrder(double price, @RequestParam(required = false) int payType) {
        double realPrice = price;
        while (isContainPrice(payType, realPrice)) {
            realPrice = Arith.add(realPrice, 0.01);
        }
        logger.info(String.format("%s", realPrice));
        priceMapper.insert(payType, realPrice);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderNo = String.format("%s%s", dateFormat.format(new Date()), ((int) (1000 + Math.random() * (9999 - 1000))));
        logger.info(String.format("%s,%s", orderNo, realPrice));
        PayOrder payOrder = new PayOrder();
        payOrder.setOrderNo(orderNo);
        payOrder.setOrderPrice(price);
        payOrder.setOrderRealPrice(realPrice);
        payOrder.setOrderStatus(OrderStatus.PAY_WAIT.getStatus());
        payOrder.setPayUrl("wxp://f2f0nYXXlFpYDBk-7meggR6gGGx8BbA5sK3UU1jrsmE5w2H7T3RneswmjovuBTPkNDVC");
        payOrder.setPayType(1);
        orderMapper.save(payOrder);

        CreateOrderRes res = new CreateOrderRes();
        res.setOrderNo(orderNo);
        return ResUtils.success(res);
    }

    public BaseResponse getOrder(String orderNo) {
        return ResUtils.success(orderMapper.getOrder(orderNo));
    }

    public BaseResponse checkOrder(String orderNo) {
        return ResUtils.success();
    }
}
