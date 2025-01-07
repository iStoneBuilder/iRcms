package com.stone.it.rcms.core.util;

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

    private static final String PROPERTIES_FILE_NAME = "application.properties";
    private static final String READ_CONFIG = "rcms.config.properties";
    private static final Properties PROPERTIES = new Properties();
    private static final Map<String, String> PROPERTIES_MAP = new HashMap<>();

    static void propertiesReader() {
        InputStream inputStream = null;
        try {
            // 读取默认配置
            inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
            if (null == inputStream) {
                return;
            }
            PROPERTIES.load(inputStream);
            // 读取动态配置
            String config = PROPERTIES.getProperty(READ_CONFIG);
            if (config != null && config != "") {
                String[] configs = config.split(",");
                for (int i = 0; i < configs.length; i++) {
                    inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(configs[i]);
                    PROPERTIES.load(inputStream);
                }
            }
            initPropertiesMap(PROPERTIES);
            PROPERTIES_MAP.remove(READ_CONFIG);
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
            PROPERTIES_MAP.put((String)keyValue.getKey(), (String)keyValue.getValue());
        }
    }

    /**
     * 获取配置
     *
     * @param key
     * @return
     */
    public static String getValue(String key) {
        // 初始化数据
        if (PROPERTIES_MAP.isEmpty()) {
            propertiesReader();
        }
        String value = PROPERTIES_MAP.get(key);
        // 没有获取到，从环境变量获取
        if (value == null || value.isEmpty()) {
            value = System.getenv(key);
        }
        return value;
    }

    public static String getValue(String key, String defaultValue) {
        // 初始化数据
        if (PROPERTIES_MAP.isEmpty()) {
            propertiesReader();
        }
        String value = PROPERTIES_MAP.get(key);
        // 没有获取到，从环境变量获取
        if (value == null || value.isEmpty()) {
            value = System.getenv(key);
        }
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        return value;
    }

}
