package com.stone.it.rcms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author cj.stone
 * @Date 2024/11/24
 * @Desc
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.stone.it"})
public class TestCornApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestCornApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Quartz Application Start now ........");
        SpringApplication.run(TestCornApplication.class, args);
        LOGGER.info("Quartz Application Running ........");
    }

}
