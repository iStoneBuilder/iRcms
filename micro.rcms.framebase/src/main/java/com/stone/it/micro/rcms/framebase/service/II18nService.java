package com.stone.it.micro.rcms.framebase.service;

import com.stone.it.micro.rcms.framebase.vo.I18nVO;
import com.stone.it.micro.rcms.framecore.vo.PageVO;
import com.stone.it.micro.rcms.framecore.vo.PageResult;
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

/**
 * @author cj.stone
 * @Date 2023/3/9
 * @Desc
 */
@Path("/i18n")
@Produces("application/json")
@Consumes("application/json")
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
  PageResult<I18nVO> findI18nPageResult(@QueryParam("") I18nVO i18nVO,
      @PathParam("") PageVO pageVO);

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
  I18nVO findI18nById(@PathParam("i18n_id")String i18nId);

  /**
   * 创建
   *
   * @param i18nVO
   * @return
   */
  @POST
  @Path("/records")
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
  int updateI18n(@PathParam("i18n_id")String i18nId, I18nVO i18nVO);

  /**
   * 删除
   *
   * @param i18nId
   * @return
   */
  @DELETE
  @Path("/records/{i18n_id}")
  int deleteI18n(@PathParam("i18n_id")String i18nId);

}
