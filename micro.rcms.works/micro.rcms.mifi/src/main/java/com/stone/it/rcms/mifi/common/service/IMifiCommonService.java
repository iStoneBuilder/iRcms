package com.stone.it.rcms.mifi.common.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.mifi.common.vo.CarrierVO;
import com.stone.it.rcms.mifi.common.vo.SimAuthUrlVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author cj.stone
 * @Date 2025/1/7
 * @Desc
 */
@Path("/common")
public interface IMifiCommonService {
    /**
     * 根据ICCID查询运营商信息
     */
    CarrierVO findMerchantCarrierInfoByIccId(String iccid);

    @POST
    @Path("/records/{iccid}/auth-url")
    @RcmsMethod(name = "SIM卡管理.获得实名认证地址", type = "Y")
    @RequiresPermissions("permission:sim:auth.url")
    SimAuthUrlVO authSimUrl(@PathParam("iccid") String iccid);
}
