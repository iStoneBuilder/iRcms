package com.stone.it.rcms.base.service;

import com.stone.it.rcms.base.vo.EnterpriseVO;
import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
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
 * @Date 2024/11/1
 * @Desc
 */
@Path("/enterprise")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IEnterpriseService {

    /**
     * 分页查询企业(商户)列表
     *
     * @param enterpriseVO
     * @param pageVO
     * @return
     */
    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    @RcmsMethod(name = "企业分页查询")
    @RequiresPermissions("permission:enterprise:page:query")
    PageResult<EnterpriseVO> findEnterprisePageResult(@QueryParam("") EnterpriseVO enterpriseVO,
        @PathParam("") PageVO pageVO);

    /**
     * 企业(商户)详情
     *
     * @param enterprise_id
     * @return
     */
    @GET
    @Path("/records/{enterprise_id}")
    @RcmsMethod(name = "企业详情查询")
    @RequiresPermissions("permission:enterprise:record:query")
    EnterpriseVO findEnterpriseMerchantById(@PathParam("enterprise_id") String enterprise_id);

    /**
     * 新增企业(商户)信息
     *
     * @param enterpriseVO
     * @return
     */
    @POST
    @Path("/records")
    @RcmsMethod(name = "新增企业信息")
    @RequiresPermissions("permission:enterprise:create")
    int createEnterpriseMerchant(EnterpriseVO enterpriseVO);

    /**
     * 修改企业(商户)信息
     *
     * @param enterpriseVO
     * @return
     */
    @PATCH
    @Path("/records/{enterprise_id}")
    @RcmsMethod(name = "修改企业信息")
    @RequiresPermissions("permission:enterprise:update")
    int updateEnterpriseMerchant(@PathParam("enterprise_id") String enterprise_id, EnterpriseVO enterpriseVO);

    /**
     * 删除企业(商户)信息
     *
     * @param enterprise_id
     * @return
     */
    @DELETE
    @Path("/records/{enterprise_id}")
    @RcmsMethod(name = "删除企业信息")
    @RequiresPermissions("permission:enterprise:delete")
    int deleteEnterpriseMerchant(@PathParam("enterprise_id") String enterprise_id);

}
