package com.dong.vmqpay.netty.pojo;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class Response implements Serializable {
    @NonNull
    private Long id;

    @NonNull
    private int type;

    @NonNull
    private Object result;


    public Response(Request request, Object result) {
        this.id = request.getId();
        this.type = request.getType();
        this.result = result;
    }
}
