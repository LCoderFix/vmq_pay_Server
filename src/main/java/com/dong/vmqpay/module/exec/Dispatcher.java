package com.dong.vmqpay.module.exec;

import com.dong.vmqpay.netty.pojo.Request;
import com.dong.vmqpay.utils.handler.HandlerReflectInfo;
import io.netty.channel.Channel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.dong.vmqpay.utils.handler.ClassUtils.getHandlerReflectInfo;

public class Dispatcher {

    private static final int MAX_THREAD_COUNT = 50;

    private static ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD_COUNT);

    public static void submit(Channel channel, Object msgObj) {
        Request request = (Request) msgObj;
        HandlerReflectInfo handlerReflectInfo = getHandlerReflectInfo(((Request) msgObj).getType());
        System.out.println(handlerReflectInfo);
        BaseModuleExecutor baseModuleExecutor = new BaseModuleExecutor();
        baseModuleExecutor.setChannel(channel);
        baseModuleExecutor.setClazz(handlerReflectInfo.getClazz());
        baseModuleExecutor.setMethod(handlerReflectInfo.getMethod());
        baseModuleExecutor.setMsgObj(request);
        executorService.submit(baseModuleExecutor);
    }
}
