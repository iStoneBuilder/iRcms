package com.stone.it.rcms.mifi.common.dao;

import com.stone.it.rcms.mifi.common.vo.CommonVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/2
 * @Desc
 */
public interface ICommonDao {

    List<CommonVO> findEnterpriseListByTenantId(@Param("tenantId") String tenantId);

    CommonVO findEnterpriseMerchantById(@Param("id") String id);

    List<CommonVO> findEnterpriseListByParentId(@Param("parentId") String parentId);
}
