package com.dong.vmqpay.utils.handler;

import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Method;

@Data
@ToString
public class HandlerReflectInfo {
    //业务handler 所属类
    private Class<?> clazz;

    //业务处理方法
    private Method method;

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
