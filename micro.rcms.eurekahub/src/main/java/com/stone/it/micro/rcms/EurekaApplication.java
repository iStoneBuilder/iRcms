package com.stone.it.micro.rcms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author jichen
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(EurekaApplication.class);

  public static void main(String[] args) {
    LOGGER.info("Eureka Application start ...... ");
    SpringApplication.run(EurekaApplication.class, args);
  }

}
