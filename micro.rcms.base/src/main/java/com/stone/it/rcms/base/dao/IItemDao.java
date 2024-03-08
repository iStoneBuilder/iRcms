package com.stone.it.rcms.base.dao;


import com.stone.it.rcms.base.vo.ClassifyVO;
import com.stone.it.rcms.base.vo.ItemVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import java.util.List;

/**
 * @author cj.stone
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
