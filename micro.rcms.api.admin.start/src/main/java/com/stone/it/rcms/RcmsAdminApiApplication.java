package com.stone.it.rcms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 管理端API启动模块
 * 
 * @author cj.stone
 * @Date 2024/12/17
 * @Desc
 */
@SpringBootApplication
public class RcmsAdminApiApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(RcmsAdminApiApplication.class);
    private static final String START_MESSAGE = "Rcms Admin Api Application server start ...... ";

    public static void main(String[] args) {
        LOGGER.info(START_MESSAGE);
        SpringApplication.run(RcmsAdminApiApplication.class, args);
    }

}
