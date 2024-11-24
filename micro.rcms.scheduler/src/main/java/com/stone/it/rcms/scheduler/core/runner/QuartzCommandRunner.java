package com.stone.it.rcms.scheduler.core.runner;

import com.stone.it.rcms.scheduler.dao.ISchedulerConfigDao;
import com.stone.it.rcms.scheduler.core.manager.QuartzManager;
import com.stone.it.rcms.scheduler.dao.ISchedulerGroupDao;
import com.stone.it.rcms.scheduler.vo.QuartzGroupVO;
import com.stone.it.rcms.scheduler.vo.SchedulerVO;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 加载任务
 *
 * @author cj.stone
 * @Desc
 */
@Order(1)
@Component
public class QuartzCommandRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzCommandRunner.class);

    @Resource
    private QuartzManager quartzManager;

    @Resource
    private ISchedulerConfigDao schedulerDao;

    /**
     * 重写run方法
     * 
     * @param args incoming main method arguments
     * @throws Exception exception
     */
    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("加载启用的任务列表 START ...");
        try {
            SchedulerVO searchVO = new SchedulerVO();
            searchVO.setEnabledFlag("enable");
            // 任务信息列表
            List<SchedulerVO> jobList = schedulerDao.findQuartzList(searchVO);
            LOGGER.info("启用的任务 SIZE: {}", jobList.size());
            for (SchedulerVO scheduledJob : jobList) {
                // 执行任务
                quartzManager.startQuartz(scheduledJob);
            }
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
    }

}
