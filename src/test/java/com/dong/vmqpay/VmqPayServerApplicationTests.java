package com.dong.vmqpay;

import com.dong.vmqpay.mapper.PriceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

@SpringBootTest
class VmqPayServerApplicationTests {

    @Autowired
    PriceMapper mapper;

    @Test
    void contextLoads() {
        System.out.println(SpringVersion.getVersion());
        mapper.insert(1, 111);
        System.out.println(mapper.getPriceCount());
    }

}
