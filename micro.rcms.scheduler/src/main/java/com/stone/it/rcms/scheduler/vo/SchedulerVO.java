package com.stone.it.rcms.scheduler.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

/**
 * 定时任务
 *
 * @author cj.stone
 * @Desc
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SchedulerVO extends QuartzGroupVO {

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
    /** 任务状态(enabled:已启用; suspend:已暂停; disabled:未启用; stopped: 已停用) */
    private String enabledFlag;
    /** 任务执行状态(Y,执行成功;N,执行失败;空,未执行) */
    private String quartzStatus;
    /** 下次执行时间 */
    private String nextExecTime;

}
