package com.dong.vmqpay;

import com.dong.vmqpay.mapper.PriceMapper;
import com.dong.vmqpay.netty.client.Client;
import com.dong.vmqpay.netty.pojo.Request;
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

    @Test
    void testClient(){
        Client client=new Client("127.0.0.1",8089);
        client.send(new Request(12,"test"));
        while (true){

        }
    }

}
