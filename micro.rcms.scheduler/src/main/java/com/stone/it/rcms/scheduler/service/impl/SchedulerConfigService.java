package com.stone.it.rcms.scheduler.service.impl;

import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.UUIDUtil;
import com.stone.it.rcms.core.util.UserUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.scheduler.dao.ISchedulerConfigDao;
import com.stone.it.rcms.scheduler.core.manager.QuartzManager;
import com.stone.it.rcms.scheduler.service.ISchedulerConfigService;
import com.stone.it.rcms.scheduler.vo.*;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import org.quartz.SchedulerException;

/**
 *
 * @author cj.stone
 * @Desc
 */
@Named
public class SchedulerConfigService implements ISchedulerConfigService {

    @Resource
    private QuartzManager quartzManager;

    @Inject
    private ISchedulerConfigDao schedulerDao;

    @Override
    public PageResult<SchedulerVO> findQuartzPageResult(SchedulerVO schedulerVO, PageVO pageVO) {
        return schedulerDao.findQuartzPageResult(schedulerVO, pageVO);
    }

    @Override
    public SchedulerVO createQuartz(SchedulerVO schedulerVO) throws Exception {
        // 设置任务ID
        schedulerVO.setQuartzId(UUIDUtil.getUuid());
        if (schedulerVO.getQuartzCronZh() == null || schedulerVO.getQuartzCronZh().isEmpty()) {
            schedulerVO.setQuartzCronZh(schedulerVO.getQuartzCron());
        }
        // 创建数据
        schedulerDao.createQuartz(schedulerVO);
        // 定时任务启用
        if ("enable".equals(schedulerVO.getEnabledFlag())) {
            quartzManager.startQuartz(schedulerVO);
        }
        return schedulerVO;
    }

    @Override
    public int deleteQuartz(String quartzId) throws SchedulerException {
        SchedulerVO schedulerVO = schedulerDao.findQuartzInfoById(quartzId, UserUtil.getTenantId());
        if (schedulerVO == null) {
            throw new RcmsApplicationException(500, "定时任务不存在");
        }
        // 删除定时任务
        quartzManager.deleteQuartz(schedulerVO);
        // 删除数据表
        return schedulerDao.deleteQuartz(quartzId, UserUtil.getTenantId());
    }

    @Override
    public SchedulerVO updateQuartz(String quartzId, SchedulerVO schedulerVO) throws Exception {
        schedulerVO.setQuartzId(quartzId);
        // 更新数据
        schedulerDao.updateQuartz(schedulerVO);
        if ("enable".equals(schedulerVO.getEnabledFlag())) {
            // 删除定时任务
            quartzManager.deleteQuartz(schedulerVO);
            // 启动定时任务
            quartzManager.startQuartz(schedulerVO);
        }
        return schedulerVO;
    }

    @Override
    public SchedulerVO operateQuartz(String quartzId, String operate) throws Exception {
        // 查询旧数据 (enabled:已启用; suspend:已暂停; disabled:未启用; stopped: 已停用)
        SchedulerVO schedulerVO = schedulerDao.findQuartzInfoById(quartzId, UserUtil.getTenantId());
        // 操作启用，必须为已未启用，已停用的数据
        if ("start".equals(operate)) {
            if ("stopped".equals(schedulerVO.getEnabledFlag()) || "disabled".equals(schedulerVO.getEnabledFlag())) {
                schedulerVO.setEnabledFlag("enabled");
                quartzManager.startQuartz(schedulerVO);
            } else {
                throw new RcmsApplicationException(500, "只有已停用，未启用的任务才允许执行启用操作！");
            }
        }
        // 操作停用，必须为已启用的数据
        if ("stop".equals(operate)) {
            if ("enabled".equals(schedulerVO.getEnabledFlag())) {
                schedulerVO.setEnabledFlag("stopped");
                quartzManager.deleteQuartz(schedulerVO);
            } else {
                throw new RcmsApplicationException(500, "只有已启用的任务才允许执行暂停操作！");
            }
        }
        // 操作暂停，必须为已启用的数据
        if ("pause".equals(operate)) {
            if ("enabled".equals(schedulerVO.getEnabledFlag())) {
                schedulerVO.setEnabledFlag("suspend");
                quartzManager.pauseQuartz(schedulerVO);
            } else {
                throw new RcmsApplicationException(500, "只有已启用的任务才允许执行暂停操作！");
            }
        }
        // 操作恢复，必须为已暂停的数据
        if ("resume".equals(operate)) {
            if ("suspend".equals(schedulerVO.getQuartzStatus())) {
                schedulerVO.setEnabledFlag("enabled");
                quartzManager.resumeQuartz(schedulerVO);
            } else {
                throw new RcmsApplicationException(500, "只有已暂停的任务才允许执行恢复操作！");
            }
        }
        // 更新数据
        schedulerDao.updateQuartz(schedulerVO);
        return schedulerVO;
    }

}
