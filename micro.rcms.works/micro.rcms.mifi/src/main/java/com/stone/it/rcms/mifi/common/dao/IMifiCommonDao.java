package com.stone.it.rcms.mifi.common.dao;

import com.stone.it.rcms.mifi.common.vo.CarrierVO;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author cj.stone
 * @Date 2025/1/7
 * @Desc
 */
public interface IMifiCommonDao {

    CarrierVO findMerchantCarrierInfoByIccId(@Param("iccid") String iccid);

}
