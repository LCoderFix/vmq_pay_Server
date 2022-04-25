package com.dong.vmqpay.pojo;

public enum OrderStatus {
    //等待支付
    PAY_WAIT(1),
    PAY_SUCCESS(2),
    PAY_CLOSE(3);

    int status;

    public int getStatus() {
        return status;
    }

    OrderStatus(int status) {
        this.status = status;
    }
}
