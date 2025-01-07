package com.stone.it.rcms.mifi.app.dao;

import com.stone.it.rcms.mifi.app.vo.MifiThaliVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/30
 * @Desc
 */
public interface IMifiThaliDao {
    List<MifiThaliVO> findDeviceCanUseThali(@Param("deviceSn") String deviceSn);
}
