package com.dong.vmqpay.netty.client;

import com.dong.vmqpay.netty.pojo.Request;
import com.dong.vmqpay.netty.pojo.Response;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class Client {
    private String remoteHost;
    private ChannelFuture channelFuture;

    private int port;

    public Client(String remoteHost, int port) {
        this.remoteHost = remoteHost;
        this.port = port;
        EventLoopGroup group=new NioEventLoopGroup();
        Bootstrap b=new Bootstrap();
        Bootstrap handler = b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new IdleStateHandler(0, 0, 60, TimeUnit.SECONDS));
                ch.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                ch.pipeline().addLast(new ObjectEncoder());
                ch.pipeline().addLast(new ClientHandler());
            }
        });
        try {
            channelFuture=b.connect(remoteHost, port).sync();
        }catch (Exception e){

        }

    }

    public void send(Request request){
        channelFuture.channel().writeAndFlush(request);
    }


}
