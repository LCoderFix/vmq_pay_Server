package com.dong.vmqpay.netty.pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

@Data
@RequiredArgsConstructor
public class Request implements Serializable {
    private static AtomicLong atomicLong = new AtomicLong();

    private final Long id = atomicLong.incrementAndGet();

    public int type;

    public String content;

    public Request(int type, String content) {
        this.type = type;
        this.content = content;
    }
}
