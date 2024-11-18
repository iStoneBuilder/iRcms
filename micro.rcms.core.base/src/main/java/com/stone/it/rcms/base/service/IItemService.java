package com.stone.it.rcms.base.service;

import com.stone.it.rcms.base.vo.ClassifyVO;
import com.stone.it.rcms.base.vo.ItemVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
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
import org.apache.shiro.authz.annotation.RequiresAuthentication;

/**
 * @author cj.stone
 * @Desc
 */
@Path("/classify")
@Produces("application/json")
@Consumes("application/json")
@RequiresAuthentication
public interface IItemService {

  @GET
  @Path("/records/page/{curPage}/{pageSize}")
  PageResult<ClassifyVO> findClassifyPageResult(@QueryParam("") ClassifyVO classifyVO,
    @PathParam("") PageVO pageVO);

  @GET
  @Path("/records/{classify_code}")
  ClassifyVO findClassify(@PathParam("classify_code") String classifyCode);

  @POST
  @Path("/records")
  int createClassify(ClassifyVO classifyVO);

  @PUT
  @Path("/records/{classify_code}")
  int updateClassify(@PathParam("classify_code") String classifyCode, ClassifyVO classifyVO);

  @DELETE
  @Path("/records/{classify_code}")
  int deleteClassify(@PathParam("classify_code") String classifyCode);

  @GET
  @Path("/records/item/page/{curPage}/{pageSize}")
  PageResult<ItemVO> findClassifyItemPageResult(@QueryParam("") ItemVO itemVO,
    @PathParam("") PageVO pageVO);

  @GET
  @Path("/records/item/{classify_code}/{lang}")
  List<ItemVO> findClassifyItemByCodeLang(@PathParam("classify_code") String classifyCode,
    @PathParam("lang") String lang);

  @POST
  @Path("/records/item")
  int createClassifyItem(ItemVO itemVO);

  @PUT
  @Path("/records/item/{item_id}")
  int updateClassifyItem(@PathParam("item_id") String itemId, ItemVO itemVO);

  @DELETE
  @Path("/records/item/{item_id}")
  int deleteClassifyItem(@PathParam("item_id") String itemId);

}
