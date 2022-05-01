package com.dong.vmqpay.netty.server;

import com.dong.vmqpay.netty.pojo.Request;
import com.dong.vmqpay.netty.pojo.Response;
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
        log.info("接收消息成功");
        Request request = ((Request) msg);
        Response response = new Response(request, "success");
        ctx.writeAndFlush(response);
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
