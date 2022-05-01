package com.dong.vmqpay.utils;

import com.dong.vmqpay.mapper.SettingMapper;
import com.dong.vmqpay.netty.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private SettingMapper settingMapper;

    @Autowired
    private Server server;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("系统启动中");
        if (settingMapper.getSettingCount() == 0) {
            logger.info("检测到系统首次启动，正在进行初始化");
        }
        server.run();
    }
}
