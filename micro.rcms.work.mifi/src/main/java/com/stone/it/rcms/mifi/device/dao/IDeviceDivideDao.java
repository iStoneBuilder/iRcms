package com.stone.it.rcms.mifi.device.dao;

import com.stone.it.rcms.core.common.vo.CommonVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.device.vo.DeviceDivideVO;
import com.stone.it.rcms.mifi.device.vo.DeviceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/11
 * @Desc
 */
public interface IDeviceDivideDao {

    PageResult<DeviceDivideVO> findPageDivideResult(@Param("param1") DeviceDivideVO vo, @Param("param2") PageVO pageVO,
        @Param("param3") List<CommonVO> list);

    void createDivideRecord(@Param("divide") DeviceDivideVO vo, @Param("list") List<DeviceVO> list);

}
