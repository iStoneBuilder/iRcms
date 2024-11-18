package com.stone.it.rcms.base.service.impl;

import com.stone.it.rcms.base.dao.IPermissionDao;
import com.stone.it.rcms.base.service.IPermissionService;
import com.stone.it.rcms.core.handler.PermissionHandler;
import com.stone.it.rcms.core.provider.ApplicationContextProvider;
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
        API_PATH_SET = new HashSet<>();
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        // 获取服务根路径
        String contextPath = context.getEnvironment().getProperty("server.servlet.context-path");
        // 获取所有配置的接口暴露
        String[] beanNames = context.getBeanNamesForType(JAXRSServerFactoryBean.class);
        for (String beanName : beanNames) {
            getCxfEndpointPaths(context.getBean(beanName, JAXRSServerFactoryBean.class), contextPath);
        }
        LOGGER.info("ALL API SIZE {},{},{}", CURRENT_API_LIST.size(), AUTH_CODE_SET.size(), API_PATH_SET.size());
        List<String> apis = new ArrayList<>(API_PATH_SET);
        // 查询当前根路径下的接口
        List<PermissionVO> dbExistApis = permissionDao.findPermissionListByPaths(apis);
        List<PermissionVO> dbNotExistApiList = new ArrayList<>();
        for (PermissionVO permission : CURRENT_API_LIST) {
            boolean isExist = false;
            for (PermissionVO dbExistApi : dbExistApis) {
                if (permission.getPath().equals(dbExistApi.getPath())) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                dbNotExistApiList.add(permission);
            }
        }
        // 创建新增接口
        if (!dbNotExistApiList.isEmpty()) {
            permissionDao.createPermission(dbNotExistApiList);
        }
        // 清理已删除接口
        permissionDao.deletePermissionNotInList(apis);
        // 清理不存在的授权关系
        permissionDao.deleteApisRelationAuth();
        // 创建超级管理员权限
        permissionDao.createSuperAdminAuth(new ArrayList<>(AUTH_CODE_SET), "platformAdmin");
        return 1;
    }
}
