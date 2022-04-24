package com.dong.vmqpay.controller;

import com.dong.vmqpay.pojo.BaseResponse;
import com.dong.vmqpay.utils.ResUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class WebController {


    @GetMapping("/test")
    public BaseResponse test() {
        return ResUtils.success(new String("111"));
    }

}
