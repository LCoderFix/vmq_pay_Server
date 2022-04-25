package com.dong.vmqpay.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface PriceMapper {

    @Select("select count(*) from t_pay_tmp_price")
    int getPriceCount();

    @Insert("insert into t_pay_tmp_price (type,price) values(#{type},#{price})")
    void insert(int type, double price);

    @Select("select count(price) from t_pay_tmp_price where type=#{type} and price=#{price}")
    int getCountByPrice(int type, double price);
}
