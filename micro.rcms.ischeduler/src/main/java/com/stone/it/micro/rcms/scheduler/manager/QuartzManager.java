package com.stone.it.micro.rcms.scheduler.manager;

import com.stone.it.micro.rcms.scheduler.vo.SchedulerVO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Component;

/**
 *
 * @author cj.stone
 * @Date 2023/7/18
 * @Desc
 */
@Component
public class QuartzManager {
  /** 注入QuartzConfig中定义的任务调度器scheduler */
  @Resource
  private Scheduler scheduler;

  /**
   * @description: 获取所有任务信息
   * @return java.util.List<com.wwu.entity.SchedulerVO>
   * @author 一蓑烟雨
   * @date 2023/4/16 18:59
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
   * @description: 获取某个任务的信息
   * @param scheduledJob
   * @return java.lang.String
   * @author 一蓑烟雨
   * @date 2023/4/16 18:07
   */
  public String getJobInfo(SchedulerVO scheduledJob) throws SchedulerException {
    TriggerKey triggerKey = new TriggerKey(scheduledJob.getJobName(), scheduledJob.getJobGroup());
    System.out.println("triggerKey:"+triggerKey);
    CronTrigger cronTrigger= (CronTrigger) scheduler.getTrigger(triggerKey);
    return String.format("time:%s,state:%s",cronTrigger.getCronExpression(),
        scheduler.getTriggerState(triggerKey).name());
  }

  /**
   * @description: 获取任务数量
   * @return java.lang.String
   * @author 一蓑烟雨
   * @date 2023/4/16 18:07
   */
  public int getJobSize() throws SchedulerException {
    GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
    Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
    return jobKeys.size();
  }

  /**
   * @description: 获取触发器状态
   * @param scheduledJob
   * @return NONE:不存在;NORMAL:正常;PAUSED:暂停;COMPLETE:完成;ERROR:错误;BLOCKED:阻塞
   * @author 一蓑烟雨
   * @date 2023/4/17 18:56
   */
  public String getTriggerState(SchedulerVO scheduledJob) throws SchedulerException {
    TriggerKey triggerKey = new TriggerKey(scheduledJob.getJobName(), scheduledJob.getJobGroup());
    Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
    return triggerState.name();
  }

  /**
   * @description: 开启某个任务
   * @param scheduledJob
   * @return void
   * @author 一蓑烟雨
   * @date 2023/4/16 18:05
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
   * @description: 添加某个任务
   * @param scheduledJob
   * @return void
   * @author 一蓑烟雨
   * @date 2023/4/16 18:01
   */
  public boolean addJobTask(SchedulerVO scheduledJob) throws Exception {
    //利用反射机制获取任务执行类
    Class<? extends Job> jobClass = (Class<? extends Job>)(Class.forName(scheduledJob.getClassMethod()).newInstance().getClass());
    //设置任务明细，调用定义的任务逻辑
    JobDetail jobDetail = JobBuilder.newJob(jobClass)
        //添加认证信息(也可通过usingJobData传参数)
        .withIdentity(scheduledJob.getJobName(), scheduledJob.getJobGroup())
        //执行
        .build();
    //设置任务触发器，cornTrigger规则定义执行规则
    CronTrigger cronTrigger = TriggerBuilder.newTrigger()
        //通过键值对方式向job实现业务逻辑传参数
        .usingJobData("jobDesc",scheduledJob.getJobDesc())
        .usingJobData("jobCron",scheduledJob.getJobCron())
        //添加认证信息
        .withIdentity(scheduledJob.getJobName(), scheduledJob.getJobGroup())
        //程序启动后间隔多久开始执行任务
        .startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND))
        //执行Cron表达时
        .withSchedule(CronScheduleBuilder.cronSchedule(scheduledJob.getJobCron()))
        //执行
        .build();
    //把任务明细和触发器注册到任务调度中
    scheduler.scheduleJob(jobDetail, cronTrigger);
    return true;
  }

  /**
   * @description: 修改任务的Cron表达式
   * @param scheduledJob
   * @return boolean
   * @author 一蓑烟雨
   * @date 2023/4/16 17:46
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
   * @description: 暂停所有任务
   * @return void
   * @author 一蓑烟雨
   * @date 2023/4/16 17:41
   */
  public void pauseAllJob()throws SchedulerException{
    scheduler.pauseAll();
  }

  /**
   * @description: 暂停某个任务
   * @param scheduledJob
   * @return void
   * @author 一蓑烟雨
   * @date 2023/4/16 17:41
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
   * @description: 恢复所有任务
   * @return void
   * @author 一蓑烟雨
   * @date 2023/4/16 17:38
   */
  public void resumeAllJob()throws SchedulerException{
    scheduler.resumeAll();
  }

  /**
   * @description: 恢复某个任务
   * @param scheduledJob
   * @return void
   * @author 一蓑烟雨
   * @date 2023/4/16 17:39
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
   * @description: 删除任务
   * @param scheduledJob
   * @return void
   * @author 一蓑烟雨
   * @date 2023/4/16 17:32
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
