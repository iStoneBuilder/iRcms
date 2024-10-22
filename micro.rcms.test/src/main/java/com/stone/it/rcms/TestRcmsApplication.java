package com.stone.it.rcms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the TestRcms application.
 *
 * @author cj.stone
 * @Date 2024/10/14
 */
@SpringBootApplication
public class TestRcmsApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestRcmsApplication.class);
    private static final String START_MESSAGE = "Gateway Application server start ...... ";

    public static void main(String[] args) {
        LOGGER.info(START_MESSAGE);
        SpringApplication.run(TestRcmsApplication.class, args);
    }

}
