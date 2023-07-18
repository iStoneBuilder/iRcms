package com.stone.it.micro.rcms.scheduler.config;

import com.stone.it.micro.rcms.scheduler.vo.SchedulerVO;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import lombok.Data;
import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 *
 * @author cj.stone
 * @Date 2023/7/18
 * @Desc
 */
@Data
@Configuration
@PropertySource("classpath:quartz.properties")
@ConfigurationProperties(prefix = "springboot.quartz")
public class QuartzConfig {
  /** 注入quartz.properties配置文件中的任务 */
  private List<SchedulerVO> scheduledJobs;
  @Resource
  private JobFactory jobFactory;

  /**
   * @description: SchedulerFactoryBean工厂
   */
  @Bean
  public SchedulerFactoryBean schedulerFactoryBean() {
    //创建 SchedulerFactoryBean 实例
    SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
    try {
      //设置是否覆盖已存在的job,为True表示有相同名称和分组的触发器和任务存在时则替换
      schedulerFactoryBean.setOverwriteExistingJobs(true);
      //设置是否自动启动Scheduler
      schedulerFactoryBean.setAutoStartup(true);
      //设置quartz属性
      schedulerFactoryBean.setQuartzProperties(quartzProperties());
      //设置任务调度器
      schedulerFactoryBean.setJobFactory(jobFactory);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return schedulerFactoryBean;
  }

  /**
   * @description: 读取quartz配置文件中配置相关属性
   * @return java.util.Properties
   * @author 一蓑烟雨
   * @date 2023/4/17 17:18
   */
  @Bean
  public Properties quartzProperties() throws IOException {
    PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
    propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
    //在quartz.properties中的属性被读取并注入后再初始化对象
    propertiesFactoryBean.afterPropertiesSet();
    return propertiesFactoryBean.getObject();
  }
  /**
   * @description: 初始化schedule任务调度器
   * @return org.quartz.Scheduler
   * @author 一蓑烟雨
   * @date 2023/4/16 18:12
   */
  @Bean
  public Scheduler scheduler() {
    return schedulerFactoryBean().getScheduler();
  }
}
