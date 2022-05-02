package com.dong.vmqpay.module;

import com.dong.vmqpay.annotation.ModuleHandler;

import static com.dong.vmqpay.module.Constants.MSG_TYPE_HEART;

public class HeartModule {

    @ModuleHandler(msgType = MSG_TYPE_HEART)
    public void heart(){
        System.out.println("heart");
    }
}
