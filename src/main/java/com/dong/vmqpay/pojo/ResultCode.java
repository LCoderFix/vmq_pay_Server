package com.dong.vmqpay.pojo;

public enum ResultCode {
    SUCCESS(200, "业务成功"),
    FAIL(-1, "业务失败");

    private final int code;
    private final String desc;

    private ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "ResultCode{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }
}
