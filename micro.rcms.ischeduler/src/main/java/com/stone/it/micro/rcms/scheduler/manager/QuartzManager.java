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
   * 获取所有任务信息
   *
   * @return
   * @throws SchedulerException
   */
  public List<SchedulerVO> getAllJobInfo() throws SchedulerException {
    List<SchedulerVO> jobList = new ArrayList<SchedulerVO>();
    GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
    Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
    for(JobKey jobKey: jobKeys){
      List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
      for(Trigger trigger: triggers){
        SchedulerVO job = new SchedulerVO();
        job.setJobName(jobKey.getName());
        job.setJobGroup(jobKey.getGroup());
        job.setJobDesc(trigger.getDescription());
        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
        job.setJobStatus(triggerState.name());
        if(trigger instanceof  CronTrigger){
          CronTrigger cronTrigger= (CronTrigger) trigger;
          job.setJobCron(cronTrigger.getCronExpression());
        }
        jobList.add(job);
      }
    }
    return jobList;
  }

  /**
   * 获取某个任务的信息
   *
   * @param scheduledJob
   * @return
   * @throws SchedulerException
   */
  public String getJobInfo(SchedulerVO scheduledJob) throws SchedulerException {
    TriggerKey triggerKey = new TriggerKey(scheduledJob.getJobName(), scheduledJob.getJobGroup());
    LOGGER.info("QuartzManager getJobInfo triggerKey:"+triggerKey);
    CronTrigger cronTrigger= (CronTrigger) scheduler.getTrigger(triggerKey);
    return String.format("time:%s,state:%s",cronTrigger.getCronExpression(),
        scheduler.getTriggerState(triggerKey).name());
  }

  /**
   * 获取任务数量
   *
   * @return
   * @throws SchedulerException
   */
  public int getJobSize() throws SchedulerException {
    GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
    Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
    return jobKeys.size();
  }

  /**
   * 获取触发器状态
   *
   * @param scheduledJob
   * @return NONE:不存在;NORMAL:正常;PAUSED:暂停;COMPLETE:完成;ERROR:错误;BLOCKED:阻塞
   * @throws SchedulerException
   */
  public String getTriggerState(SchedulerVO scheduledJob) throws SchedulerException {
    TriggerKey triggerKey = new TriggerKey(scheduledJob.getJobName(), scheduledJob.getJobGroup());
    Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
    return triggerState.name();
  }

  /**
   * 开启某个任务
   *
   * @param scheduledJob
   * @throws Exception
   */
  public void startJob(SchedulerVO scheduledJob) throws Exception {
    JobKey jobKey = JobKey.jobKey(scheduledJob.getJobName(), scheduledJob.getJobGroup());
    //不存在则添加任务
    if(!scheduler.checkExists(jobKey)){
      addJobTask(scheduledJob);
    }
    //启动
    scheduler.start();
  }

  /**
   * 添加某个任务
   * @param scheduledJob
   * @return
   * @throws Exception
   */
  public boolean addJobTask(SchedulerVO scheduledJob) throws Exception {
    // 利用反射机制获取任务执行类
    Class<? extends Job> jobClass = (Class<? extends Job>)(Class.forName("com.stone.it.micro.rcms.scheduler.job.SchedulerJob").newInstance().getClass());
    // 存放数据
    Map<String,Object> jobMap = new HashMap<String, Object>();
    jobMap.put("job_data",scheduledJob);
    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.putAll(jobMap);
    // 设置任务明细，调用定义的任务逻辑
    JobDetail jobDetail = JobBuilder.newJob(jobClass)
        // 添加认证信息(也可通过usingJobData传参数)
        .withIdentity(scheduledJob.getJobName(), scheduledJob.getJobGroup())
        // 存放数据
        .usingJobData(jobDataMap)
        //执行
        .build();
    //设置任务触发器，cornTrigger规则定义执行规则
    CronTrigger cronTrigger = TriggerBuilder.newTrigger()
        // 通过键值对方式向job实现业务逻辑传参数
        .usingJobData("jobDesc",scheduledJob.getJobDesc())
        .usingJobData("jobCron",scheduledJob.getJobCron())
        .usingJobData("jobData", JSON.toJSONString(scheduledJob))
        // 添加认证信息
        .withIdentity(scheduledJob.getJobName(), scheduledJob.getJobGroup())
        // 程序启动后间隔多久开始执行任务
        .startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND))
        // 执行Cron表达时
        .withSchedule(CronScheduleBuilder.cronSchedule(scheduledJob.getJobCron()))
        // 执行
        .build();
    // 把任务明细和触发器注册到任务调度中
    scheduler.scheduleJob(jobDetail, cronTrigger);
    return true;
  }

  /**
   * 修改任务的Cron表达式
   *
   * @param scheduledJob
   * @return
   * @throws SchedulerException
   */
  public boolean modifyJob(SchedulerVO scheduledJob) throws SchedulerException{
    TriggerKey triggerKey = new TriggerKey(scheduledJob.getJobName(), scheduledJob.getJobGroup());
    CronTrigger cronTrigger= (CronTrigger) scheduler.getTrigger(triggerKey);
    String oldTime = cronTrigger.getCronExpression();
    if (!oldTime.equalsIgnoreCase(scheduledJob.getJobCron())){
      CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduledJob.getJobCron());
      CronTrigger trigger=TriggerBuilder.newTrigger()
          .withIdentity(scheduledJob.getJobName(), scheduledJob.getJobGroup())
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
   * @throws SchedulerException
   */
  public void pauseAllJob()throws SchedulerException{
    scheduler.pauseAll();
  }

  /**
   * 暂停某个任务
   * @param scheduledJob
   * @throws SchedulerException
   */
  public void pauseJob(SchedulerVO scheduledJob)throws SchedulerException{
    JobKey jobKey = JobKey.jobKey(scheduledJob.getJobName(), scheduledJob.getJobGroup());
    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
    if (jobDetail == null) {
      return;
    }
    scheduler.pauseJob(jobKey);
  }

  /**
   * 恢复所有任务
   * @throws SchedulerException
   */
  public void resumeAllJob()throws SchedulerException{
    scheduler.resumeAll();
  }

  /**
   * 恢复某个任务
   * @param scheduledJob
   * @throws SchedulerException
   */
  public void resumeJob(SchedulerVO scheduledJob)throws SchedulerException {
    JobKey jobKey = JobKey.jobKey(scheduledJob.getJobName(), scheduledJob.getJobGroup());
    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
    if (jobDetail == null) {
      return;
    }
    scheduler.resumeJob(jobKey);
  }

  /**
   * 删除任务
   * @param scheduledJob
   * @throws SchedulerException
   */
  public void deleteJob(SchedulerVO scheduledJob)throws SchedulerException {
    JobKey jobKey = JobKey.jobKey(scheduledJob.getJobName(), scheduledJob.getJobGroup());
    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
    if (jobDetail == null) {
      return;
    }
    scheduler.deleteJob(jobKey);
  }
}
