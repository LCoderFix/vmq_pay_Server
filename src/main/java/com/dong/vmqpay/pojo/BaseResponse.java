package com.dong.vmqpay.pojo;

public class BaseResponse<T> {

    private Integer code;

    private Integer status;

    private String message;

    private T data;

    public BaseResponse(Builder<T> builder) {
        this.code = builder.code;
        this.status = builder.status;
        this.message = builder.message;
        this.data = builder.data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class Builder<V> {
        public Integer code;

        public Integer status;

        public String message;

        public V data;

        public Builder(Integer code, Integer status) {
            this.code = code;
            this.status = status;
        }

        public Builder<V> message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(V data) {
            this.data = data;
            return this;
        }

        public BaseResponse<V> build() {
            return new BaseResponse(this);
        }
    }
}
