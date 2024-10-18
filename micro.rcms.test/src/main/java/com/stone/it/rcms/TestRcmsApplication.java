package com.stone.it.rcms;

import com.stone.it.rcms.auth.util.JwtUtils;
import java.util.HashMap;
import java.util.Map;
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
    generateAndLogJwtToken();
  }

  private static void generateAndLogJwtToken() {
    Map<String, String> tokenData = new HashMap<>();
    tokenData.put("name", "cj.stone");
    tokenData.put("age", "25");

    String token = JwtUtils.generateToken(tokenData);
    LOGGER.info("Generated Token: {}", token);
    LOGGER.info("Token Info: {}", JwtUtils.getTokenInfo(token).toString());
  }
}

