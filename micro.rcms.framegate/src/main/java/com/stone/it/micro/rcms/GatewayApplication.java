package com.stone.it.micro.rcms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author cj.stone
 * @Date 2022/10/19
 * @Desc
 */
@EnableEurekaClient
@SpringBootApplication
public class GatewayApplication {


  private static final Logger LOGGER = LoggerFactory.getLogger(GatewayApplication.class);

  public static void main(String[] args) {
    LOGGER.info("Gateway Application server start ...... ");
    SpringApplication.run(GatewayApplication.class, args);
  }

}
