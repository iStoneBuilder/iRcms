package com.stone.it.rcms.core.pay.dao;

import com.stone.it.rcms.core.pay.vo.PayConfigVO;
import com.stone.it.rcms.core.pay.vo.WxConfigVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/9
 * @Desc
 */
public interface IPayConfigDao {

    /**
     * 根据租户ID查询支付配置详情
     */
    List<WxConfigVO> findWxPayConfigByTpp(@Param("tenantId") String tenantId, @Param("paySource") String paySource,
        @Param("payWay") String payWay);

    WxConfigVO findWxPayConfigByPci(@Param("payConfigId") String payConfigId, @Param("paySource") String paySource);
}
