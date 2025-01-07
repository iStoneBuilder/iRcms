package com.stone.it.rcms.scheduler.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Desc
 */
@Data
public class QuartzJobVO extends BaseVO {

  private String quartzId;
  private String quartzName;
  private String jobId;
  private String jobStatus;
  private String responseCode;
  private String responseBody;
  private Date startTime;
  private Date endTime;

}
