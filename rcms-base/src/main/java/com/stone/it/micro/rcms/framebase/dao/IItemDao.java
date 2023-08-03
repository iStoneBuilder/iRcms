package com.stone.it.micro.rcms.framebase.dao;

import com.stone.it.micro.rcms.framebase.vo.ClassifyVO;
import com.stone.it.micro.rcms.framebase.vo.ItemVO;
import com.stone.it.rcms.com.vo.PageResult;
import com.stone.it.rcms.com.vo.PageVO;
import java.util.List;

/**
 * @author cj.stone
 * @Date 2023/3/9
 * @Desc
 */
public interface IItemDao {

  PageResult<ClassifyVO> findPageResult(ClassifyVO classifyVO, PageVO pageVO);

  ClassifyVO findClassify( String classifyCode);

  int createClassify(ClassifyVO classifyVO);


  int updateClassify(ClassifyVO classifyVO);


  int deleteClassify( String classifyCode);


  List<ItemVO> findClassifyItemByCode( String classifyCode);


  List<ItemVO> findClassifyItemByCodeLang(String classifyCode, String lang);


  int createClassifyItem(ItemVO itemVO);


  int updateClassifyItem(ItemVO itemVO);

  int deleteClassifyItem(String itemId);

}
