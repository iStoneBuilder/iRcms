package com.stone.it.micro.rcms.scheduler.manager;

import com.alibaba.fastjson.JSON;
import com.stone.it.micro.rcms.scheduler.config.QuartzConfig;
import com.stone.it.micro.rcms.scheduler.vo.SchedulerVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author cj.stone
 * @Date 2023/7/18
 * @Desc
 */
@Component
public class QuartzManager {


  private static final Logger LOGGER = LoggerFactory.getLogger(QuartzManager.class);
  /**
   * 注入QuartzConfig中定义的任务调度器scheduler
   * */
  @Resource
  private Scheduler scheduler;

  /**
   * 开启某个任务
   *
   * @param scheduledJob
   * @throws Exception
   */
  public void startQuartz(SchedulerVO scheduledJob) throws Exception {
    JobKey jobKey = JobKey.jobKey(scheduledJob.getQuartzId(), scheduledJob.getQuartzGroup());
    // 不存在则添加任务
    if(!scheduler.checkExists(jobKey)){
      // 利用反射机制获取任务执行类
      Class<? extends Job> jobClass = (Class<? extends Job>)(Class.forName("com.stone.it.micro.rcms.scheduler.job.SchedulerJob").newInstance().getClass());
      // 存放数据
      Map<String,Object> jobMap = new HashMap<String, Object>();
      jobMap.put("jobData",scheduledJob);
      JobDataMap jobDataMap = new JobDataMap();
      jobDataMap.putAll(jobMap);
      // 设置任务明细，调用定义的任务逻辑
      JobDetail jobDetail = JobBuilder.newJob(jobClass)
          // 添加认证信息(也可通过usingJobData传参数)
          .withIdentity(scheduledJob.getQuartzId(), scheduledJob.getQuartzGroup())
          // 存放数据
          .usingJobData(jobDataMap)
          //执行
          .build();
      // 设置任务触发器，cornTrigger规则定义执行规则
      CronTrigger cronTrigger = TriggerBuilder.newTrigger()
          // 通过键值对方式向job实现业务逻辑传参数
          .usingJobData("jobName",scheduledJob.getQuartzName())
          .usingJobData("jobCron",scheduledJob.getQuartzCron())
          // 添加认证信息
          .withIdentity(scheduledJob.getQuartzId(), scheduledJob.getQuartzGroup())
          // 程序启动后间隔多久开始执行任务
          .startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND))
          // 执行Cron表达时
          .withSchedule(CronScheduleBuilder.cronSchedule(scheduledJob.getQuartzCron()))
          // 执行
          .build();
      // 把任务明细和触发器注册到任务调度中
      scheduler.scheduleJob(jobDetail, cronTrigger);
    }
    // 启动
    scheduler.start();
  }

  /**
   * 修改任务的Cron表达式
   *
   * @param scheduledJob
   * @return
   * @throws SchedulerException
   */
  public boolean modifyQuartz(SchedulerVO scheduledJob) throws SchedulerException{
    TriggerKey triggerKey = new TriggerKey(scheduledJob.getQuartzId(), scheduledJob.getQuartzGroup());
    CronTrigger cronTrigger= (CronTrigger) scheduler.getTrigger(triggerKey);
    String oldTime = cronTrigger.getCronExpression();
    if (!oldTime.equalsIgnoreCase(scheduledJob.getQuartzCron())){
      CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduledJob.getQuartzCron());
      CronTrigger trigger=TriggerBuilder.newTrigger()
          .withIdentity(scheduledJob.getQuartzId(), scheduledJob.getQuartzGroup())
          .withSchedule(cronScheduleBuilder)
          .build();
      scheduler.rescheduleJob(triggerKey,trigger);
      return true;
    }else{
      return false;
    }
  }

  /**
   * 暂停所有任务
   *
   * @throws SchedulerException
   */
  public void pauseAllQuartz()throws SchedulerException{
    scheduler.pauseAll();
  }

  /**
   * 暂停某个任务
   *
   * @param scheduledJob
   * @throws SchedulerException
   */
  public void pauseQuartz(SchedulerVO scheduledJob)throws SchedulerException{
    JobKey jobKey = JobKey.jobKey(scheduledJob.getQuartzId(), scheduledJob.getQuartzGroup());
    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
    if (jobDetail == null) {
      return;
    }
    scheduler.pauseJob(jobKey);
  }

  /**
   * 恢复所有任务
   *
   * @throws SchedulerException
   */
  public void resumeAllQuartz()throws SchedulerException{
    scheduler.resumeAll();
  }

  /**
   * 恢复某个任务
   * @param scheduledJob
   * @throws SchedulerException
   */
  public void resumeQuartz(SchedulerVO scheduledJob)throws SchedulerException {
    JobKey jobKey = JobKey.jobKey(scheduledJob.getQuartzId(), scheduledJob.getQuartzGroup());
    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
    if (jobDetail == null) {
      return;
    }
    scheduler.resumeJob(jobKey);
  }

  /**
   * 删除任务
   *
   * @param scheduledJob
   * @throws SchedulerException
   */
  public void deleteQuartz(SchedulerVO scheduledJob)throws SchedulerException {
    JobKey jobKey = JobKey.jobKey(scheduledJob.getQuartzId(), scheduledJob.getQuartzGroup());
    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
    if (jobDetail == null) {
      return;
    }
    scheduler.deleteJob(jobKey);
  }
}
