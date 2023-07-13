package com.stone.it.micro.rcms.framebase.service;

import com.stone.it.micro.rcms.framebase.vo.ClassifyVO;
import com.stone.it.micro.rcms.framebase.vo.ItemVO;
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
@Path("/classify")
@Produces("application/json")
@Consumes("application/json")
public interface IItemService {

  @GET
  @Path("/records/{curPage}/{pageSize}")
  PageResult<ClassifyVO> findClassifyPageResult(@QueryParam("") ClassifyVO classifyVO,
      @PathParam("") PageVO pageVO);

  @GET
  @Path("/records/{classify_code}")
  ClassifyVO findClassify(@PathParam("classify_code") String classifyCode);

  @POST
  @Path("/records")
  int createClassify(ClassifyVO classifyVO);

  @PATCH
  @Path("/records/{classify_code}")
  int updateClassify(@PathParam("classify_code") String classifyCode,ClassifyVO classifyVO);

  @DELETE
  @Path("/records/{classify_code}")
  int deleteClassify(@PathParam("classify_code") String classifyCode);

  @GET
  @Path("/records/item/{classify_code}")
  List<ItemVO> findClassifyItemByCode(@PathParam("classify_code") String classifyCode);

  @GET
  @Path("/records/item/{classify_code}/{lang}")
  List<ItemVO> findClassifyItemByCodeLang(@PathParam("classify_code") String classifyCode,@PathParam("lang") String lang);

  @POST
  @Path("/records/item")
  int createClassifyItem(ItemVO itemVO);

  @PATCH
  @Path("/records/item/{item_id}")
  int updateClassifyItem(@PathParam("item_id") String itemId,ItemVO itemVO);

  @DELETE
  @Path("/records/item/{item_id}")
  int deleteClassifyItem(@PathParam("item_id") String itemId);

}
