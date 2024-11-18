package com.stone.it.rcms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cj.stone
 * @Date 2023/4/27
 * @Desc
 */
@SpringBootApplication
public class FlywayApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(FlywayApplication.class);

  public static void main(String[] args) {
    LOGGER.info("Flyway Application server start ...... ");
    SpringApplication.run(FlywayApplication.class, args);
  }

}
