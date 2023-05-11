package com.stone.it.micro.rcms.framebase.service.impl;

import com.alibaba.fastjson.JSON;
import com.stone.it.micro.rcms.framebase.dao.IItemDao;
import com.stone.it.micro.rcms.framebase.service.IItemService;
import com.stone.it.micro.rcms.framebase.vo.ClassifyVO;
import com.stone.it.micro.rcms.framebase.vo.ItemVO;
import com.stone.it.micro.rcms.framecore.util.UUIDUtil;
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
    return itemDao.findPageResult(classifyVO,pageVO);
  }

  @Override
  public ResultVO findClassify(String classifyCode) {
    return new ResultVO(itemDao.findClassify(classifyCode));
  }

  @Override
  public ResultVO createClassify(ClassifyVO classifyVO) {
    itemDao.createClassify(classifyVO);
    return new ResultVO(classifyVO);
  }

  @Override
  public ResultVO updateClassify(String classifyCode, ClassifyVO classifyVO) {
    classifyVO.setClassifyCode(classifyCode);
    itemDao.updateClassify(classifyVO);
    return new ResultVO(classifyVO);
  }

  @Override
  public ResultVO deleteClassify(String classifyCode) {
    itemDao.deleteClassify(classifyCode);
    return new ResultVO();
  }

  @Override
  public ResultVO findClassifyItemByCode(String classifyCode) {
    return new ResultVO(itemDao.findClassifyItemByCode(classifyCode));
  }

  @Override
  public ResultVO findClassifyItemByCodeLang(String classifyCode, String lang) {
    return new ResultVO(itemDao.findClassifyItemByCodeLang(classifyCode,lang));
  }

  @Override
  public ResultVO createClassifyItem(ItemVO itemVO) {
    itemVO.setItemId(UUIDUtil.getUuid());
    itemDao.createClassifyItem(itemVO);
    return new ResultVO(itemVO);
  }

  @Override
  public ResultVO updateClassifyItem(String itemId, ItemVO itemVO) {
    itemVO.setItemId(itemId);
    itemDao.updateClassifyItem(itemVO);
    return new ResultVO(itemVO);
  }

  @Override
  public ResultVO deleteClassifyItem(String itemId) {
    itemDao.deleteClassifyItem(itemId);
    return new ResultVO();
  }
}
