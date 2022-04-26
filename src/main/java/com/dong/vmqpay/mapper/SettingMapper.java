package com.dong.vmqpay.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SettingMapper {

    @Select("select count(s_key) from t_pay_setting")
    public int getSettingCount();
}
