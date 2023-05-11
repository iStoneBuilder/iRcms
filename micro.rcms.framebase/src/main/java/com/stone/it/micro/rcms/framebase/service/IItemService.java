package com.stone.it.micro.rcms.framebase.service;

import com.stone.it.micro.rcms.framebase.vo.ClassifyVO;
import com.stone.it.micro.rcms.framebase.vo.ItemVO;
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
@Path("/classify")
@Produces("application/json")
@Consumes("application/json")
public interface IItemService {

  @GET
  @Path("/records/{curPage}/{pageSize}")
  PagedResult<ClassifyVO> findPageResult(@QueryParam("") ClassifyVO classifyVO,
      @PathParam("") PageVO pageVO);

  @GET
  @Path("/records/{classify_code}")
  ResultVO findClassify(@PathParam("classify_code") String classifyCode);

  @POST
  @Path("/records")
  ResultVO createClassify(ClassifyVO classifyVO);

  @PATCH
  @Path("/records/{classify_code}")
  ResultVO updateClassify(@PathParam("classify_code") String classifyCode,ClassifyVO classifyVO);

  @DELETE
  @Path("/records/{classify_code}")
  ResultVO deleteClassify(@PathParam("classify_code") String classifyCode);

  @GET
  @Path("/records/item/{classify_code}")
  ResultVO findClassifyItemByCode(@PathParam("classify_code") String classifyCode);

  @GET
  @Path("/records/item/{classify_code}/{lang}")
  ResultVO findClassifyItemByCodeLang(@PathParam("classify_code") String classifyCode,@PathParam("lang") String lang);

  @POST
  @Path("/records/item")
  ResultVO createClassifyItem(ItemVO itemVO);

  @PATCH
  @Path("/records/item/{item_id}")
  ResultVO updateClassifyItem(@PathParam("item_id") String itemId,ItemVO itemVO);

  @DELETE
  @Path("/records/item/{item_id}")
  ResultVO deleteClassifyItem(@PathParam("item_id") String itemId);

}
