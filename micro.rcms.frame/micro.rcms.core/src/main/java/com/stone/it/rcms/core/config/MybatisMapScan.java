package com.stone.it.rcms.core.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 *  扫描Java Dao类
 * @author cj.stone
 * @Desc
 */
@Configuration
@MapperScan(basePackages = "com.stone.it.**.dao")
public class MybatisMapScan {

}
