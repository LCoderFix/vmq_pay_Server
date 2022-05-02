package com.dong.vmqpay.module;

import com.dong.vmqpay.annotation.ModuleHandler;

public class TestModule {

    @ModuleHandler(msgType = 12)
    public void add(int num) {

    }
}
