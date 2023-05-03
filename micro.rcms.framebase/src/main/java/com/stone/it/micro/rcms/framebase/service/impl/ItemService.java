package com.stone.it.micro.rcms.framebase.service.impl;

import com.stone.it.micro.rcms.framebase.dao.IItemDao;
import com.stone.it.micro.rcms.framebase.service.IItemService;
import com.stone.it.micro.rcms.framebase.vo.ClassifyVO;
import com.stone.it.micro.rcms.framebase.vo.ItemVO;
import com.stone.it.micro.rcms.framecore.vo.PageVO;
import com.stone.it.micro.rcms.framecore.vo.PagedResult;
import com.stone.it.micro.rcms.framecore.vo.ResultVO;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * LOOKUP配置
 *
 * @author cj.stone
 * @Date 2023/3/9
 * @Desc
 */
@Named
public class ItemService implements IItemService {

  @Inject
  private IItemDao itemDao;

  @Override
  public PagedResult<ClassifyVO> findPageResult(ClassifyVO classifyVO, PageVO pageVO) {
    return itemDao.findPageResult(classifyVO, pageVO);
  }

  @Override
  public ClassifyVO findClassify(String classifyCode) {
    return null;
  }

  @Override
  public ClassifyVO createClassify(ClassifyVO classifyVO) {
    return null;
  }

  @Override
  public ClassifyVO updateClassify(ClassifyVO classifyVO) {
    return null;
  }

  @Override
  public ClassifyVO deleteClassify(ClassifyVO classifyVO) {
    return null;
  }

  @Override
  public List<ItemVO> findItemListByCode(String classify) {
    return null;
  }

  @Override
  public List<ItemVO> findItemListByCodeLang(String classify, String lang) {
    return null;
  }

  @Override
  public ItemVO createClassifyItem(ItemVO itemVO) {
    return null;
  }

  @Override
  public ItemVO updateClassifyItem(ItemVO itemVO) {
    return null;
  }

  @Override
  public ItemVO deleteClassifyItem(ItemVO itemVO) {
    return null;
  }

}
