package com.stone.it.micro.rcms.framebase.service;

import com.stone.it.micro.rcms.framebase.vo.I18nVO;
import com.stone.it.micro.rcms.framecore.vo.BatchVO;
import com.stone.it.micro.rcms.framecore.vo.PageVO;
import com.stone.it.micro.rcms.framecore.vo.PagedResult;
import com.stone.it.micro.rcms.framecore.vo.ResultVO;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
  @Path("/list/page/{curPage}/{pageSize}")
  PagedResult<I18nVO> findPageResult(@QueryParam("") I18nVO i18nVO,
      @PathParam("") PageVO pageVO);

  /**
   * 根据语言查询列表
   *
   * @param language
   * @return
   */
  @GET
  @Path("/list/{language}")
  List<I18nVO> findListByLanguage(@PathParam("language") String language);

  /**
   * 创建
   *
   * @param i18nVO
   * @return
   */
  @POST
  @Path("/record")
  ResultVO createI18n(I18nVO i18nVO);

  /**
   * 更新
   *
   * @param i18nId
   * @param i18nVO
   * @return
   */
  @PATCH
  @Path("/record/{i18n_id}")
  ResultVO updateI18n(@PathParam("i18n_id")String i18nId, I18nVO i18nVO);

  /**
   * 删除
   *
   * @param i18nId
   * @return
   */
  @DELETE
  @Path("/record/{i18n_id}")
  ResultVO deleteI18n(@PathParam("i18n_id")String i18nId);

}
