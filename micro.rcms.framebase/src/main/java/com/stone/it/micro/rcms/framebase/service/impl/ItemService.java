package com.stone.it.micro.rcms.framebase.service.impl;

import com.stone.it.micro.rcms.framebase.service.IItemService;
import com.stone.it.micro.rcms.framebase.vo.ClassifyVO;
import com.stone.it.micro.rcms.framebase.vo.ItemVO;
import com.stone.it.micro.rcms.framecore.vo.PageVO;
import com.stone.it.micro.rcms.framecore.vo.PagedResult;
import com.stone.it.micro.rcms.framecore.vo.ResultVO;
import java.util.List;
import javax.inject.Named;

/**
 * @author cj.stone
 * @Date 2023/3/9
 * @Desc
 */
@Named
public class ItemService implements IItemService {


  @Override
  public PagedResult<ClassifyVO> findPageResult(ClassifyVO classifyVO, PageVO pageVO) {
    return null;
  }

  @Override
  public ClassifyVO findClassify(String classifyCode) {
    return null;
  }

  @Override
  public ClassifyVO findClassifyItemByCode(String classifyCode) {
    return null;
  }

  @Override
  public ClassifyVO findClassifyItemByCodeLang(String classifyCode, String language) {
    return null;
  }

  @Override
  public ResultVO createClassify(ClassifyVO classifyVO) {
    return null;
  }

  @Override
  public ResultVO updateClassify(ClassifyVO classifyVO) {
    return null;
  }

  @Override
  public ResultVO deleteClassify(ClassifyVO classifyVO) {
    return null;
  }

  @Override
  public ResultVO createClassifyItem(ItemVO itemVO) {
    return null;
  }

  @Override
  public ResultVO updateClassifyItem(ItemVO itemVO) {
    return null;
  }

  @Override
  public ResultVO deleteClassifyItem(ItemVO itemVO) {
    return null;
  }
}
