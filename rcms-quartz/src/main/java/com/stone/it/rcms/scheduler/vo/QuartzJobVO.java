package com.stone.it.rcms.scheduler.vo;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Date 2023/7/22
 * @Desc
 */
@Data
public class QuartzJobVO {

  private String quartzId;
  private String quartzName;
  private String jobId;
  private String jobStatus;
  private String responseCode;
  private String responseBody;
  private Date startTime;
  private Date endTime;

}
