package com.stone.it.micro.rcms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author cj.stone
 * @Date 2023/5/10
 * @Desc
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.stone.it"})
public class RcmsDemoApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(RcmsDemoApplication.class);
  public static void main(String[] args) {
    LOGGER.info("Demo Application Start now ........");
    SpringApplication.run(RcmsDemoApplication.class, args);
    LOGGER.info("Demo Application Running ........");
  }
}
