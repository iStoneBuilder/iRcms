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
public class RcmsCronApiApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(RcmsCronApiApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Rcms Cron Api Application Start now ........");
        SpringApplication.run(RcmsCronApiApplication.class, args);
    }

}
