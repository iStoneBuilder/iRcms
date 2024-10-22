package com.stone.it.rcms.base.service;

import com.stone.it.rcms.base.vo.I18nVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import java.util.List;
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
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * @author cj.stone
 * @Desc
 */
@Path("/i18n")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public interface II18nService {

    /**
     * 分页查询
     *
     * @param i18nVO
     * @param pageVO
     * @return
     */
    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    @RequiresPermissions("find.i18n.page")
    PageResult<I18nVO> findI18nPageResult(@QueryParam("") I18nVO i18nVO, @PathParam("") PageVO pageVO);

    /**
     * 根据语言查询列表
     *
     * @param lang
     * @return
     */
    @GET
    @Path("/records/list/{lang}")
    List<I18nVO> findI18nListByLanguage(@PathParam("lang") String lang);

    /**
     * 详情
     *
     * @param i18nId
     * @return
     */
    @GET
    @Path("/records/{i18n_id}")
    I18nVO findI18nById(@PathParam("i18n_id") String i18nId);

    /**
     * 创建
     *
     * @param i18nVO
     * @return
     */
    @POST
    @Path("/records")
    @RequiresPermissions("create.i18n")
    int createI18n(I18nVO i18nVO);

    /**
     * 更新
     *
     * @param i18nId
     * @param i18nVO
     * @return
     */
    @PATCH
    @Path("/records/{i18n_id}")
    int updateI18n(@PathParam("i18n_id") String i18nId, I18nVO i18nVO);

    /**
     * 删除
     *
     * @param i18nId
     * @return
     */
    @DELETE
    @Path("/records/{i18n_id}")
    int deleteI18n(@PathParam("i18n_id") String i18nId);

}
