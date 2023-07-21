package com.stone.it.micro.rcms.scheduler.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cj.stone
 * @Date 2023/7/21
 * @Desc
 */
@DisallowConcurrentExecution
public class SchedulerJob implements Job {

  private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerJob.class);

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    // 获取触发器cronTrigger传递的参数
    JobDataMap dataMap = context.getTrigger().getJobDataMap();
    JobDetail jobDetail = context.getJobDetail();
    LOGGER.info("【{}】任务执行开始，执行频率为：{}",dataMap.get("jobDesc"),dataMap.get("jobCron"));
    try {
      Thread.sleep(30000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    LOGGER.info("【{}】任务执行结束",dataMap.get("jobDesc"));
  }
}
