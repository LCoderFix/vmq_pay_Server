package com.dong.vmqpay.mapper;

import com.dong.vmqpay.pojo.BaseResponse;
import com.dong.vmqpay.pojo.PayOrder;
import org.apache.ibatis.annotations.*;

@Mapper
public interface WebOrderMapper {

    @Insert("insert into t_pay_order(order_no,order_price,order_status,pay_url,pay_type,order_real_price) values(#{orderNo},#{orderPrice},#{orderStatus},#{payUrl},#{payType},#{orderRealPrice})")
    void save(PayOrder payOrder);

    @Select("select order_no,order_price,order_status,pay_url,UNIX_TIMESTAMP(created_at)*1000 as created_time,pay_type,order_real_price from t_pay_order where order_no=#{orderNo}")
    @Results(id = "orderMap", value = {
            @Result(column = "order_no", property = "orderNo"),
            @Result(column = "order_status", property = "orderStatus"),
            @Result(column = "order_price", property = "orderPrice"),
            @Result(column = "pay_url", property = "payUrl"),
            @Result(column = "created_time", property = "createdTime"),
            @Result(column = "pay_type", property = "payType"),
            @Result(column = "order_real_price", property = "orderRealPrice")
    })
    PayOrder getOrder(String orderNo);


    @Update("update t_pay_order set order_status=3 where UNIX_TIMESTAMP(created_at)*1000<#{expiredTime} and order_status=1")
    int updateExpiredOrderStatus(long expiredTime);
}
