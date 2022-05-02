package com.dong.vmqpay.netty.server;

import com.dong.vmqpay.module.exec.Dispatcher;
import com.dong.vmqpay.netty.pojo.Request;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Request request = ((Request) msg);
        log.info("接收消息成功{}",request);
//        Response response = new Response(request, "success");
//        ctx.writeAndFlush(response);
        Dispatcher.submit(ctx.channel(), request);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelInactive");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = ((IdleStateEvent) evt);
            switch (((IdleStateEvent) evt).state()) {
                case ALL_IDLE:
                    ctx.channel().close();
                default:
            }
        }
    }
}
