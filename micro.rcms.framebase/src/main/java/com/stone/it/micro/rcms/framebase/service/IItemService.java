package com.stone.it.micro.rcms.framebase.service;

import com.stone.it.micro.rcms.framebase.vo.ClassifyVO;
import com.stone.it.micro.rcms.framebase.vo.ItemVO;
import com.stone.it.micro.rcms.framecore.vo.PageVO;
import com.stone.it.micro.rcms.framecore.vo.PagedResult;
import com.stone.it.micro.rcms.framecore.vo.ResultVO;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
@Path("/classify")
@Produces("application/json")
@Consumes("application/json")
public interface IItemService {

  @GET
  @Path("/find/page/list/{curPage}/{pageSize}")
  public PagedResult<ClassifyVO> findPageResult(@QueryParam("") ClassifyVO classifyVO,
      @PathParam("") PageVO pageVO);

  @GET
  @Path("/find/{classifyCode}")
  public ClassifyVO findClassify(@PathParam("classifyCode") String classifyCode);

  @GET
  @Path("/find/item/{classifyCode}")
  public ClassifyVO findClassifyItemByCode(@PathParam("classifyCode") String classifyCode);

  @GET
  @Path("/find/item/{classifyCode}/{language}")
  public ClassifyVO findClassifyItemByCodeLang(@PathParam("classifyCode") String classifyCode,
      @PathParam("language") String language);

  @POST
  @Path("/create/single")
  public ResultVO createClassify(ClassifyVO classifyVO);

  @PUT
  @Path("/update/single")
  public ResultVO updateClassify(ClassifyVO classifyVO);

  @DELETE
  @Path("/delete/single")
  public ResultVO deleteClassify(ClassifyVO classifyVO);

  @POST
  @Path("/create/item/single")
  public ResultVO createClassifyItem(ItemVO itemVO);

  @PUT
  @Path("/update/item/single")
  public ResultVO updateClassifyItem(ItemVO itemVO);

  @DELETE
  @Path("/delete/item/single")
  public ResultVO deleteClassifyItem(ItemVO itemVO);

}
