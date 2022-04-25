package com.dong.vmqpay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.dong.vmqpay.mapper")
public class VmqPayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VmqPayServerApplication.class, args);
    }

}
