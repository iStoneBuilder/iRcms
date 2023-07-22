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

  /**  任务分组编码*/
  private String quartzGroup;
  /** 任务分组名称 */
  private String quartzGroupName;
  /** 任务ID */
  private String quartzId;
  /** 任务名称 */
  private String quartzName;
  /** 任务描述 */
  private String quartzDesc;
  /** 任务表达式 */
  private String quartzCron;
  /** 任务表达式中文 */
  private String quartzCronZh;
  /** 任务状态(enable:启用; suspend:暂停; 恢复:restore; ) */
  private String enabledFlag;
  /** 任务执行状态(Y,执行成功;N,执行失败;空,未执行) */
  private String quartzStatus;
  /** 下次执行时间 */
  private String nextExecTime;
  /*** 请求URI */
  private String execUri;

}
