package com.dong.vmqpay.module.exec;

import com.dong.vmqpay.netty.pojo.Request;
import com.dong.vmqpay.netty.pojo.Response;
import io.netty.channel.Channel;
import lombok.Data;

import java.lang.reflect.Method;

@Data
public class BaseModuleExecutor implements Runnable {
    private Channel channel;

    private Class clazz;

    private Method method;

    private Request msgObj;

    @Override
    public void run() {
        Response response=new Response(msgObj,null);
        try {
            Object object = clazz.newInstance();
            Object result = method.invoke(object, msgObj.getParams());
            response.setResult(result);
        } catch (Exception e) {

        } finally {
            channel.writeAndFlush(response);
        }
    }
}
