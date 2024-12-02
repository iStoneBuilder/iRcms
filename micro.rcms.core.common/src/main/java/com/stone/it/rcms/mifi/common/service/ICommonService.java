package com.stone.it.rcms.mifi.common.service;

import com.stone.it.rcms.mifi.common.vo.CommonVO;

import java.util.List;

/**
 * 公共服务
 * 
 * @author cj.stone
 * @Date 2024/12/2
 * @Desc
 */
public interface ICommonService {

    // 根据父级ID查询子商户列表
    List<CommonVO> findEnterpriseListByParentId(String parentId);

    // 根据租户ID查询子商户列表
    List<CommonVO> findEnterpriseListByTenantId(String tenantId);
}
