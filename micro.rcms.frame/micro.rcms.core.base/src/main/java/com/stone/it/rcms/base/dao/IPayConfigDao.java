package com.stone.it.rcms.base.dao;

import com.stone.it.rcms.base.vo.PayConfigVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import org.apache.ibatis.annotations.Param;


/**
 *
 * @author cj.stone
 * @Date 2024/12/9
 * @Desc
 */
public interface IPayConfigDao {
    PageResult<PayConfigVO> findPayConfigPageList(PayConfigVO payConfigVO, PageVO pageVO);

    /**
     * 根据支付配置ID查询支付配置详情
     */
    PayConfigVO findPayConfigDetail(PayConfigVO payConfigVO);

    int createPayConfig(PayConfigVO payConfigVO);

    int updatePayConfig(PayConfigVO payConfigVO);

    int deletePayConfig(@Param("payConfigId") String payConfigId);

}
