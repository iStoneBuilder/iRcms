package com.stone.it.rcms.scheduler.core.actuator;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.http.RequestUtil;
import com.stone.it.rcms.core.http.ResponseEntity;
import com.stone.it.rcms.core.util.UUIDUtil;
import com.stone.it.rcms.scheduler.dao.ISchedulerGroupDao;
import com.stone.it.rcms.scheduler.dao.ISchedulerJobDao;
import com.stone.it.rcms.scheduler.vo.QuartzGroupVO;
import com.stone.it.rcms.scheduler.vo.QuartzJobVO;
import com.stone.it.rcms.scheduler.vo.SchedulerVO;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务执行（调用第三方接口执行业务逻辑）
 * 
 * @author cj.stone
 * @Desc
 */
@DisallowConcurrentExecution
public class SchedulerJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerJob.class);

    @Inject
    private ISchedulerGroupDao schedulerGroupDao;

    @Inject
    private ISchedulerJobDao schedulerJobDao;

    @Override
    public void execute(JobExecutionContext context) {
        // 获取触发器cronTrigger传递的参数
        JobDataMap dataMap = context.getTrigger().getJobDataMap();
        LOGGER.info("【{}】任务执行开始 ... ", dataMap.get("jobName"));
        // 获取quartz信息
        SchedulerVO schedulerJob = JSON.parseObject((String)dataMap.get("jobData"), SchedulerVO.class);
        QuartzJobVO quartzJobVO = new QuartzJobVO();
        // 创建Job任务记录
        createQuartzJob(schedulerJob, quartzJobVO);
        try {
            // 执行任务
            executeJob(schedulerJob, quartzJobVO);
        } catch (Exception exception) {
            // 执行失败
            quartzJobVO.setJobStatus("fail");
            quartzJobVO.setResponseCode("500");
            quartzJobVO.setResponseBody(exception.getMessage());
            LOGGER.info("【{}】任务执行异常 ... {}", dataMap.get("jobName"), exception);
        }
        updateQuartzJob(quartzJobVO);
        LOGGER.info("【{}】任务执行结束 ... ", dataMap.get("jobName"));
    }

    private void executeJob(SchedulerVO schedulerVO, QuartzJobVO jobVO) {
        // 解析定时任务的请求头参数
        Map<String, String> header = parseHeader(schedulerVO.getRequestHeaders());
        // 构建认证请求头
        buildAuthHeader(header, schedulerVO);
        ResponseEntity response = executeRequest(schedulerVO.getRequestType(), schedulerVO.getRequestPath(),
            schedulerVO.getRequestParams(), header);
        // 任务执行状态
        jobVO.setJobStatus(response.getMessage());
        // 任务响应编码
        jobVO.setResponseCode(response.getCode());
        // 设置错误信息
        if (null != response.getErrors() && !response.getErrors().isEmpty()) {
            jobVO.setResponseBody(response.getErrors());
        } else {
            // 任务响应体
            jobVO.setResponseBody(response.getBody());
        }
    }

    private void buildAuthHeader(Map<String, String> header, SchedulerVO schedulerVO) {
        // 先判断是否需要认证
        if ("Y".equals(schedulerVO.getIsAuthorized())) {
            // 查询分组信息
            QuartzGroupVO groupInfo = schedulerGroupDao.findQuartzGroupInfoByCode(schedulerVO.getQuartzGroupCode());
            // 创建请求头
            Map<String, String> groupHeader = parseHeader(groupInfo.getRequestHeaders());
            // 执行查询认证信息
            ResponseEntity response = executeRequest(groupInfo.getRequestType(), groupInfo.getRequestPath(),
                groupInfo.getRequestParams(), groupHeader);
            JSONObject body = (JSONObject)JSON.parse(response.getBody());
            if (body == null) {
                LOGGER.info("【{}】任务获取认证异常 ... {}", schedulerVO.getQuartzName(), response.getErrors());
                return;
            }
            header.put(schedulerVO.getAuthKey(), body.getJSONObject("data").getString(groupInfo.getAuthKey()));
        }
    }

    private Map<String, String> parseHeader(String headers) {
        Map<String, String> header = new HashMap<>();
        if (headers != null) {
            JSONObject jsonObject = JSONObject.parseObject(headers);
            @SuppressWarnings("unchecked")
            Map<String, Object> map = jsonObject.toJavaObject(Map.class);
            // Map内容转header
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                header.put(entry.getKey(), entry.getValue().toString());
            }
        }
        return header;
    }

    private ResponseEntity executeRequest(String type, String uri, String body, Map<String, String> header) {
        if ("GET".equals(type)) {
            return RequestUtil.doGet(uri, new HashMap<>(), header);
        }
        return RequestUtil.doPost(uri, body, header);
    }

    private void createQuartzJob(SchedulerVO schedulerVO, QuartzJobVO jobVO) {
        jobVO.setJobId(UUIDUtil.getUuid());
        jobVO.setQuartzId(schedulerVO.getQuartzId());
        jobVO.setJobStatus("running");
        jobVO.setStartTime(new Date());
        jobVO.setTenantId(schedulerVO.getTenantId());
        schedulerJobDao.createJob(jobVO);
    }

    private void updateQuartzJob(QuartzJobVO jobVO) {
        jobVO.setEndTime(new Date());
        // 执行完成更新记录
        schedulerJobDao.updateJob(jobVO);
    }

}
