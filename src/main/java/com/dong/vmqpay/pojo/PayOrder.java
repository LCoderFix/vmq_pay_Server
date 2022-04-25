package com.dong.vmqpay.pojo;

public class PayOrder {
    private String orderNo;

    private double orderPrice;

    private double orderRealPrice;

    private int orderStatus;

    private String payUrl;

    private Long createdTime;

    private Integer payType;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public double getOrderRealPrice() {
        return orderRealPrice;
    }

    public void setOrderRealPrice(double orderRealPrice) {
        this.orderRealPrice = orderRealPrice;
    }

    @Override
    public String toString() {
        return "PayOrder{" +
                "orderNo='" + orderNo + '\'' +
                ", orderPrice=" + orderPrice +
                ", orderStatus=" + orderStatus +
                ", payUrl='" + payUrl + '\'' +
                ", createdTime=" + createdTime +
                ", payType=" + payType +
                '}';
    }
}
