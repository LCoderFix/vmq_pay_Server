package com.dong.vmqpay.netty.client;

import com.dong.vmqpay.netty.pojo.Request;
import com.dong.vmqpay.netty.pojo.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Response response = (Response) msg;
        if (response.getType()==12){
            log.info("ping");
            return;
        }
        log.info("收到消息，类型为{}",response.getType());
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event= (IdleStateEvent) evt;
            switch (event.state()){
                case ALL_IDLE:
                    Request request=new Request(1,"ping");
                    ctx.channel().writeAndFlush(request);
                default:
            }
        }
    }
}
