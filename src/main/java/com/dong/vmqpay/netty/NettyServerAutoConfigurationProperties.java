package com.dong.vmqpay.netty;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "netty.server")
@Data
public class NettyServerAutoConfigurationProperties {
    private String enable;

    private Integer port;
}
