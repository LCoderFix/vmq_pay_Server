package com.dong.vmqpay.netty.pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

@Data
@RequiredArgsConstructor
@ToString
public class Request implements Serializable {
    private static AtomicLong atomicLong = new AtomicLong();

    private final Long id = atomicLong.incrementAndGet();

    private int type;

    private String content;

    private Object[] params;

    public Request(int type, String content) {
        this.type = type;
        this.content = content;
    }
}
