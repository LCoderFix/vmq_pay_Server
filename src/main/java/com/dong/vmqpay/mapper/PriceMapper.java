package com.dong.vmqpay.mapper;


import org.apache.ibatis.annotations.*;

@Mapper
public interface PriceMapper {

    @Select("select count(*) from t_pay_tmp_price")
    int getPriceCount();

    @Insert("insert into t_pay_tmp_price (type,price) values(#{type},#{price})")
    void insert(int type, double price);

    @Select("select count(price) from t_pay_tmp_price where type=#{type} and price=#{price}")
    int getCountByPrice(int type, double price);

    @Delete("delete from t_pay_tmp_price where price in (select order_real_price from t_pay_order where UNIX_TIMESTAMP(created_at)*1000<#{expiredTime} and order_status=1) and type in " +
            "(select type from t_pay_order where UNIX_TIMESTAMP(created_at)*1000<#{expiredTime} and order_status=1)")
    int delPrice(long expiredTime);
}
