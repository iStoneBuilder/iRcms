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
  PagedResult<ClassifyVO> findPageResult(@QueryParam("") ClassifyVO classifyVO,
      @PathParam("") PageVO pageVO);

  @GET
  @Path("/find/record/{classifyCode}")
  ClassifyVO findClassify(@PathParam("classifyCode") String classifyCode);

  @POST
  @Path("/create/record")
  ClassifyVO createClassify(ClassifyVO classifyVO);

  @PUT
  @Path("/update/record")
  ClassifyVO updateClassify(ClassifyVO classifyVO);

  @DELETE
  @Path("/delete/record")
  ClassifyVO deleteClassify(ClassifyVO classifyVO);

  @GET
  @Path("/find/item/record/list/{classify}")
  List<ItemVO> findItemListByCode(@PathParam("classify") String classify);

  @GET
  @Path("/find/item/record/list/{classify}/{lang}")
  List<ItemVO> findItemListByCodeLang(@PathParam("classify") String classify,@PathParam("lang") String lang);

  @POST
  @Path("/create/item/record")
  ItemVO createClassifyItem(ItemVO itemVO);

  @PUT
  @Path("/update/item/record")
  ItemVO updateClassifyItem(ItemVO itemVO);

  @DELETE
  @Path("/delete/item/record")
  ItemVO deleteClassifyItem(ItemVO itemVO);

}
