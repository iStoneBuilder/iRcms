package com.stone.it.rcms.base.service.impl;

import com.stone.it.rcms.base.dao.IPermissionDao;
import com.stone.it.rcms.base.service.IPermissionService;
import com.stone.it.rcms.core.handler.PermissionHandler;
import com.stone.it.rcms.core.provider.ApplicationContextProvider;
import com.stone.it.rcms.core.util.UserUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.core.vo.PermissionVO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * @author cj.stone
 * @Date 2024/11/14
 * @Desc
 */
@Named
public class PermissionService extends PermissionHandler implements IPermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionService.class);

    @Inject
    private IPermissionDao permissionDao;

    @Override
    public PageResult<PermissionVO> findPermissionPageResult(PermissionVO permissionVO, PageVO pageVO) {
        return permissionDao.findPermissionPageResult(permissionVO, pageVO);
    }

    @Override
    public int refreshPermission(PermissionVO permissionVO) {
        CURRENT_API_LIST = new ArrayList<>();
        AUTH_CODE_SET = new HashSet<>();
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        // 获取服务根路径
        String contextPath = context.getEnvironment().getProperty("server.servlet.context-path");
        // 服务提供商
        String serviceCode = context.getEnvironment().getProperty("spring.application.name");
        // 获取所有配置的接口暴露
        String[] beanNames = context.getBeanNamesForType(JAXRSServerFactoryBean.class);
        for (String beanName : beanNames) {
            getCxfEndpointPaths(context.getBean(beanName, JAXRSServerFactoryBean.class), contextPath, serviceCode);
        }
        LOGGER.info("ALL API SIZE {},{}", CURRENT_API_LIST.size(), AUTH_CODE_SET.size());
        registerPermission(CURRENT_API_LIST);
        return 1;
    }

    @Override
    public int registerPermission(List<PermissionVO> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        String serviceCode = list.get(0).getServiceCode();
        // 查询当前根路径下的接口
        List<PermissionVO> dbExistApis = permissionDao.findCurrentServiceApiByServiceCode(serviceCode);
        List<PermissionVO> notExistApiList = new ArrayList<>();
        list.forEach(permission -> {
            boolean isExist = false;
            for (PermissionVO dbExistApi : dbExistApis) {
                // 地址和请求类型,服务提供商相同
                if (permission.getPath().equals(dbExistApi.getPath())
                    && permission.getMethod().equals(dbExistApi.getMethod())
                    && permission.getServiceCode().equals(dbExistApi.getServiceCode())) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                notExistApiList.add(permission);
            }
        });
        permissionDao.createPermission(notExistApiList);
        // 删除不存在的API
        permissionDao.deletePermissionNotExist(list, serviceCode);
        // 清理不存在的授权关系
        permissionDao.deleteApisRelationAuth(serviceCode);
        // 创建超级管理员权限
        permissionDao.createSuperAdminAuth(list, "platformAdmin", UserUtil.getUserId());
        return 1;
    }
}
