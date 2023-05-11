package com.stone.it.micro.rcms.framebase.dao;

import com.stone.it.micro.rcms.framebase.vo.ClassifyVO;
import com.stone.it.micro.rcms.framebase.vo.ItemVO;
import com.stone.it.micro.rcms.framecore.vo.PageVO;
import com.stone.it.micro.rcms.framecore.vo.PagedResult;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author cj.stone
 * @Date 2023/3/9
 * @Desc
 */
public interface IItemDao {

  PagedResult<ClassifyVO> findPageResult(ClassifyVO classifyVO, PageVO pageVO);

  ClassifyVO findClassify( String classifyCode);

  int createClassify(ClassifyVO classifyVO);


  int updateClassify(ClassifyVO classifyVO);


  int deleteClassify( String classifyCode);


  List<ItemVO> findClassifyItemByCode( String classifyCode);


  List<ItemVO> findClassifyItemByCodeLang(String classifyCode, String lang);


  ItemVO createClassifyItem(ItemVO itemVO);


  ItemVO updateClassifyItem(ItemVO itemVO);

  ItemVO deleteClassifyItem(String itemId);

}
