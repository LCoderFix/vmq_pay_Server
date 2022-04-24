package com.dong.vmqpay.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PriceMapper {

    @Select("select count(*) from t_pay_tmp_price")
    int getPriceCount();

    @Insert("insert into t_pay_tmp_price (price) values(#{price})")
    void insert(double price);
}
