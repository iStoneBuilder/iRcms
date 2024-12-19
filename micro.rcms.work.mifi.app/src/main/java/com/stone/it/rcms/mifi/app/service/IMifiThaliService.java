package com.stone.it.rcms.mifi.app.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.mifi.app.vo.MifiThaliVO;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 设备套餐相关接口
 * 
 * @author cj.stone
 * @Date 2024/12/17
 * @Desc
 */
@Path("/thali")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IMifiThaliService {

    /**
     * 查询设备套餐列表
     *
     * @param vo vo
     * @return list
     */
    @GET
    @Path("/{device_sn}")
    @RcmsMethod(name = "用户设备.套餐.列表查询", type = "Y")
    @RequiresPermissions("permission:user-device-thali:list-query")
    List<MifiThaliVO> findDeviceThaliList(@PathParam("device_sn") String deviceSn, @QueryParam("") MifiThaliVO vo);

    @GET
    @Path("/{data_plan_no}")
    @RcmsMethod(name = "用户设备.套餐.详情查询", type = "Y")
    @RequiresPermissions("permission:user-device-thali:detail-query")
    MifiThaliVO findThaliDetail(@PathParam("data_plan_no") String dataPlanNo, @QueryParam("") MifiThaliVO vo);
}
