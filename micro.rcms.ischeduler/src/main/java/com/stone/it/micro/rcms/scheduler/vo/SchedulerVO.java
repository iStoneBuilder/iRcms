package com.stone.it.micro.rcms.scheduler.vo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Date 2023/7/18
 * @Desc
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SchedulerVO implements Serializable {

  /** 任务ID */
  private String jobId;
  /** 任务名称 */
  private String jobName;
  /** 任务描述 */
  private String jobDesc;
  /** 任务表达式 */
  private String jobCron;
  /** 任务表达式中文 */
  private String jobCronZh;
  /**  任务分组编码*/
  private String jobGroup;
  /** 任务分组名称 */
  private String jobGroupName;
  /** 执行方法 */
  private String classMethod;
  /** 任务执行状态(Y,执行成功;N,执行失败;空,未执行) */
  private String jobStatus;
  /** 任务可用状态(Y:可用;N:不可用) */
  private String enabledFlag;
  /** 下次执行时间 */
  private String nextExecTime;

}
