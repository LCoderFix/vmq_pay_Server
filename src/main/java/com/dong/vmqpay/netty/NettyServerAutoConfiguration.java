package com.dong.vmqpay.netty;

import com.dong.vmqpay.netty.server.Server;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
@EnableConfigurationProperties(NettyServerAutoConfigurationProperties.class)
public class NettyServerAutoConfiguration {

    @Autowired
    private NettyServerAutoConfigurationProperties properties;

    @Bean
    public Server getServer() {
        log.info("开始加载netty server");
        return new Server(properties.getPort());
    }
}
