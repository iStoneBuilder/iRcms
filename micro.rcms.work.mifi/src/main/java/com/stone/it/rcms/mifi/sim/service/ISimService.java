package com.stone.it.rcms.mifi.sim.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.sim.vo.SimVO;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 *
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */

@Path("/sim")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface ISimService {

    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    @RcmsMethod(name = "SIM卡管理.分页查询")
    @RequiresPermissions("permission:sim:page:query")
    PageResult<SimVO> findSimPageResult(@QueryParam("") SimVO simVO, @PathParam("") PageVO pageVO);

    @GET
    @Path("/records/{iccid}")
    @RcmsMethod(name = "SIM卡管理.详情")
    @RequiresPermissions("permission:sim:detail")
    SimVO findSimDetail(@PathParam("iccid") String iccid, @QueryParam("") SimVO simVO);

    @POST
    @Path("/records")
    @RcmsMethod(name = "SIM卡管理.导入")
    @RequiresPermissions("permission:sim:import")
    int createSim(List<SimVO> simVO);

    @PUT
    @Path("/records/{iccid}")
    @RcmsMethod(name = "SIM卡管理.更新")
    @RequiresPermissions("permission:sim:update")
    int updateSim(@PathParam("iccid") String iccid, SimVO simVO);

    @DELETE
    @Path("/records/{iccid}")
    @RcmsMethod(name = "SIM卡管理.删除")
    @RequiresPermissions("permission:sim:delete")
    int deleteSim(@PathParam("iccid") String iccid);

    @PUT
    @Path("/records/{iccid}/sync")
    @RcmsMethod(name = "SIM卡管理.流量校准")
    @RequiresPermissions("permission:sim:sync")
    int syncSimDp(@PathParam("iccid") String iccid, SimVO simVO);

    @PUT
    @Path("/records/{iccid}/limit")
    @RcmsMethod(name = "SIM卡管理.流量限速")
    @RequiresPermissions("permission:sim:limit")
    int limitSimDp(@PathParam("iccid") String iccid, SimVO simVO);

}
