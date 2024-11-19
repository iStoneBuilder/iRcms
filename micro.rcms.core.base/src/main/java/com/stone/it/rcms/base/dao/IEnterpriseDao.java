package com.stone.it.rcms.base.dao;

import com.stone.it.rcms.base.vo.EnterpriseVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author cj.stone
 * @Date 2024/11/6
 * @Desc
 */
public interface IEnterpriseDao {

    List<EnterpriseVO> findEnterpriseList(EnterpriseVO enterpriseVO);

    /**
     * 根据企业ID查询企业信息
     * 
     * @param enterpriseId
     * @return
     */
    EnterpriseVO findEnterpriseMerchantById(@Param("enterpriseId") String enterpriseId);

    int createEnterpriseMerchant(EnterpriseVO enterpriseVO);

    int updateEnterpriseMerchant(EnterpriseVO enterpriseVO);

    int deleteEnterpriseMerchant(@Param("enterpriseId") String enterpriseId);

    List<EnterpriseVO> findEnterpriseListByPid(EnterpriseVO enterpriseVO);
}
