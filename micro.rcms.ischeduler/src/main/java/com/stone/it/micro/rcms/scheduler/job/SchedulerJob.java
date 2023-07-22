package com.stone.it.micro.rcms.scheduler.job;

import com.alibaba.fastjson2.JSON;
import com.stone.it.micro.rcms.common.utils.UUIDUtil;
import com.stone.it.micro.rcms.http.RequestUtil;
import com.stone.it.micro.rcms.http.ResponseEntity;
import com.stone.it.micro.rcms.scheduler.dao.ISchedulerDao;
import com.stone.it.micro.rcms.scheduler.vo.QuartzJobVO;
import com.stone.it.micro.rcms.scheduler.vo.SchedulerVO;
import java.util.Date;
import javax.inject.Inject;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
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

  @Inject
  private ISchedulerDao schedulerDao;

  @Override
  public void execute(JobExecutionContext context) {
    // 获取触发器cronTrigger传递的参数
    JobDataMap dataMap = context.getTrigger().getJobDataMap();
    LOGGER.info("【{}】任务执行开始，执行频率为：{}",dataMap.get("jobName"),dataMap.get("jobCron"));
    // 获取quartz信息
    SchedulerVO schedulerJob = JSON.parseObject((String) dataMap.get("jobData"),SchedulerVO.class);
    QuartzJobVO quartzJobVO = new QuartzJobVO();
    // 创建Job任务记录
    createQuartzJob(schedulerJob,quartzJobVO);
    try {
      // 执行任务
      executeJob(schedulerJob,quartzJobVO);
    } catch (Exception exception) {
      // 执行失败
      quartzJobVO.setJobStatus("fail");
      quartzJobVO.setResponseCode("500");
      quartzJobVO.setResponseBody(exception.getMessage());
      exception.printStackTrace();
    }
    updateQuartzJob(quartzJobVO);
    LOGGER.info("【{}】任务执行结束",dataMap.get("jobDesc"));
  }

  private void executeJob(SchedulerVO schedulerVO,QuartzJobVO jobVO){
    ResponseEntity response = RequestUtil.doPost(
        schedulerVO.getRequestPath(), null);
    // 任务执行状态
    jobVO.setJobStatus(response.getMessage());
    // 任务响应编码
    jobVO.setResponseCode(response.getCode());
    // 设置错误信息
    if(null != response.getErrors() && "" != response.getErrors()){
      jobVO.setResponseBody(response.getErrors());
    } else {
      // 任务响应体
      jobVO.setResponseBody(response.getBody());
    }
  }

  private void createQuartzJob(SchedulerVO schedulerVO,QuartzJobVO jobVO){
    jobVO.setJobId(UUIDUtil.getYearMonthDayUuid());
    jobVO.setQuartzId(schedulerVO.getQuartzId());
    jobVO.setQuartzName(schedulerVO.getQuartzName());
    jobVO.setStartTime(new Date());
    schedulerDao.createJob(jobVO);
  }

  private void updateQuartzJob(QuartzJobVO jobVO){
    jobVO.setEndTime(new Date());
    // 执行完成更新记录
    schedulerDao.updateJob(jobVO);
  }

}
