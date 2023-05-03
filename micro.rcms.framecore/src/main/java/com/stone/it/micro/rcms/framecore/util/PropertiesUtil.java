package com.stone.it.micro.rcms.framecore.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author jichen
 */
public class PropertiesUtil {

  private static final Map<String, String> propertiesMap = new HashMap<>();

  /**
   * application.properties 动态需要读取的配置
   */
  private static final String readConfig = "micro_rcms_dynamic_config";


  static void propertiesReader() {
    InputStream inputStream = null;
    Properties properties = new Properties();
    try {
      // 从环境变量读取
      String dynamicConfig = System.getenv(readConfig);
      if (dynamicConfig != null && dynamicConfig != "") {
        String[] configs = dynamicConfig.split(",");
        for (String config : configs) {
          inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(config);
          properties.load(inputStream);
        }
      }
      initPropertiesMap(properties);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private static void initPropertiesMap(Properties properties) {
    Set<Map.Entry<Object, Object>> keyValues = properties.entrySet();
    for (Map.Entry<Object, Object> keyValue : keyValues) {
      String key = (String) keyValue.getKey();
      String value = (String) keyValue.getValue();
      propertiesMap.put(key, value);
    }
  }

  /**
   * 获取配置
   *
   * @param key
   * @return
   */
  public static String getValue(String key) {
    return getValue(key, null);
  }

  public static String getValue(String key, String defaultValue) {
    // 优先从环境变量获取
    String value = System.getenv(key);
    if (value == null || value.isEmpty()) {
      // 初始化数据
      if (propertiesMap.size() == 0) {
        propertiesReader();
      }
      value = propertiesMap.get(key);
    }
    if (value == null || value.isEmpty()) {
      return defaultValue;
    }
    return value;
  }

}
