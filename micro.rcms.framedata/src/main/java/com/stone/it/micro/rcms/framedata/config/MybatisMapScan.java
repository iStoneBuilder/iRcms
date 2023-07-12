package com.stone.it.micro.rcms.framedata.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 *  扫描Java Dao类
 * @author cj.stone
 * @Date 2023/3/20
 * @Desc
 */
@Configuration
@MapperScan(basePackages = "com.stone.it.**.dao")
public class MybatisMapScan {

}
