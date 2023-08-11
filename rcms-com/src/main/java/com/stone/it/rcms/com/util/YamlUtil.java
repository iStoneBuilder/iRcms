//package com.stone.it.rcms.com.util;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
//import org.springframework.core.io.ClassPathResource;
//
///**
// *
// * @author cj.stone
// * @Date 2023/7/23
// * @Desc
// */
//public class YamlUtil {
//
//
//  private static final Map<String, String> yamlMap = new HashMap<>();
//
//  static void yamlReader() {
//    YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
//    factoryBean.setResources(new ClassPathResource("application.yml"));
//    Properties properties = factoryBean.getObject();
//    properties.keySet().forEach(key -> yamlMap.put((String) key, properties.getProperty(
//        (String) key)));
//  }
//
//  public static String getValue(String key) {
//    if(yamlMap.size()==0){
//      yamlReader();
//    }
//    String value = System.getenv(key);
//    if(value==null || value.isEmpty()){
//      value = yamlMap.get(key);
//    }
//    return value;
//  }
//
//}
