package com.stone.it.rcms.mifi.sim.dao;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.common.vo.CarrierVO;
import com.stone.it.rcms.mifi.sim.vo.MerchantVO;
import java.util.List;
import javax.ws.rs.PathParam;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
public interface IMerchantDao {

    PageResult<MerchantVO> findMerchantPageResult(MerchantVO merchantVO, PageVO pageVO);

    int createMerchant(MerchantVO merchantVO);

    int updateMerchant(MerchantVO merchantVO);

    int deleteMerchant(@Param("merchantCode") String merchantCode, @Param("enterpriseId") String enterpriseId);

    List<CarrierVO> findMerchantCarrierListByMerchantCode(@Param("merchantCode") String merchantCode);

    PageResult<CarrierVO> findMerchantCarrierPageResult(CarrierVO carrierVO, PageVO pageVO);

    int deleteMerchantCarrier(@PathParam("merchantCode") String merchantCode, @Param("carrierCode") String carrierCode);

    int updateMerchantCarrier(CarrierVO carrierVO);

    int createMerchantCarrier(CarrierVO carrierVO);

    List<MerchantVO> findMerchantList(MerchantVO merchantVO);

}
