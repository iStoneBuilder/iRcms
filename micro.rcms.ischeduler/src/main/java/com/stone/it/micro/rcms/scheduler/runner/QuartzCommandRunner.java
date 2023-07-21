package com.stone.it.micro.rcms.scheduler.runner;


import com.stone.it.micro.rcms.scheduler.dao.ISchedulerDao;
import com.stone.it.micro.rcms.scheduler.manager.QuartzManager;
import com.stone.it.micro.rcms.scheduler.vo.SchedulerVO;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 * @author cj.stone
 * @Date 2023/7/18
 * @Desc
 */
@Order(1)
@Component
public class QuartzCommandRunner implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(QuartzCommandRunner.class);

  @Resource
  private QuartzManager quartzManager;

  @Resource
  private ISchedulerDao schedulerDao;

  /**
   * 重写run方法
   * @param args incoming main method arguments
   * @throws Exception
   */
  @Override
  public void run(String... args) throws Exception {
    try {
      List<SchedulerVO> jobList = schedulerDao.findJobList(new SchedulerVO());
      LOGGER.info("初始化需要启动的任务列表: " + jobList.size());
      for (SchedulerVO scheduledJob : jobList) {
        // 执行任务
        quartzManager.startJob(scheduledJob);
      }
    } catch (Exception exception) {
      LOGGER.error(exception.getMessage());
    }
  }

}
