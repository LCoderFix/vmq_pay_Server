package com.dong.vmqpay.utils;

import com.dong.vmqpay.pojo.BaseResponse;
import com.dong.vmqpay.pojo.ResultCode;
import com.dong.vmqpay.pojo.StatusCode;

public class ResUtils {

    public static BaseResponse success(Object data) {
        return new BaseResponse.Builder<Object>(ResultCode.SUCCESS.getCode(), StatusCode.SUCCESS.getCode())
                .data(data).build();
    }

    public static BaseResponse success(Object data, String message) {
        return new BaseResponse.Builder<Object>(ResultCode.SUCCESS.getCode(), StatusCode.SUCCESS.getCode())
                .message(message)
                .data(data).build();
    }

    public static BaseResponse success(String message) {
        return new BaseResponse.Builder<Object>(ResultCode.SUCCESS.getCode(), StatusCode.SUCCESS.getCode())
                .message(message).build();
    }

    public static BaseResponse success() {
        return new BaseResponse.Builder<Object>(ResultCode.SUCCESS.getCode(), StatusCode.SUCCESS.getCode())
                .build();
    }

    public static BaseResponse fail() {
        return new BaseResponse.Builder(ResultCode.FAIL.getCode(), StatusCode.FAIL.getCode()).build();
    }
}
