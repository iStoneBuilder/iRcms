package com.stone.it.micro.rcms.scheduler.runner;

import com.stone.it.micro.rcms.scheduler.config.QuartzConfig;
import com.stone.it.micro.rcms.scheduler.manager.QuartzManager;
import com.stone.it.micro.rcms.scheduler.vo.SchedulerVO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 * @author cj.stone
 * @Date 2023/7/18
 * @Desc
 */
@Slf4j
@Component
@Order(1)
public class QuartzCommandRunner implements CommandLineRunner {
  @Resource
  private QuartzManager quartzManager;
  @Resource
  private QuartzConfig quartzConfig;

  /**
   * @description: 重写run方法
   * @return void
   * @author 一蓑烟雨
   * @date 2023/4/17 15:37
   */
  @Override
  public void run(String... args) throws Exception {
    try {
      //初始化需要启动的任务列表
      List<SchedulerVO> jobList = initSchedule(args);
      for (SchedulerVO scheduledJob : jobList) {
        //执行任务
        quartzManager.startJob(scheduledJob);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * @description: 初始化需要执行的任务列表
   * @param [args]
   * @return java.util.List<com.wwu.entity.SchedulerVO>
   * @author 一蓑烟雨
   * @date 2023/4/17 15:37
   */
  public List<SchedulerVO> initSchedule(String... args) throws SchedulerException {
    List<SchedulerVO> jobs = new ArrayList<SchedulerVO>();
    try {
      log.info("...根据参数加载配置文件quartz.properties中任务列表开始...");
      //1.遍历启动程序时传入的接口类型参数
      String[] typeParam = null;
      for (String arg : args) {
        if (arg.startsWith("type")){
          typeParam =  arg.substring(5).split(",");
          break;
        }
      }
      //2.遍历quartz.properties配置文件中任务列表
      for (SchedulerVO scheduledJob : quartzConfig.getScheduledJobs()) {
        //如果启动传入了参数，则按照参数加载启动任务
        if (typeParam != null && typeParam.length > 0) {
          //遍历参数值和任务列表，若相同则加入到启动任务列表中
          Arrays.stream(typeParam).forEach(param -> {
            if (param.trim().equals(scheduledJob.getJobName())) {
              jobs.add(scheduledJob);
              log.info("......" + scheduledJob);
            }
          });
        } else {
          //没有启动参数，默认启动配置文件中的所有任务
          jobs.add(scheduledJob);
          log.info("......" + scheduledJob);
        }
      }
      log.info("......共加载{}条任务需要执行......", jobs.size());
    } catch (Exception e) {
      e.printStackTrace();
    }
    log.info("...根据参数加载配置文件quartz.properties中任务列表结束...");
    return jobs;
  }
}
