package com.stone.it.micro.rcms.framebase.service.impl;

import com.stone.it.micro.rcms.framebase.dao.II18nDao;
import com.stone.it.micro.rcms.framebase.service.II18nService;
import com.stone.it.micro.rcms.framebase.vo.I18nVO;
import com.stone.it.micro.rcms.framecore.util.UUIDUtil;
import com.stone.it.micro.rcms.framecore.vo.BatchVO;
import com.stone.it.micro.rcms.framecore.vo.PageVO;
import com.stone.it.micro.rcms.framecore.vo.PagedResult;
import com.stone.it.micro.rcms.framecore.vo.ResultVO;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 国际化配置
 *
 * @author cj.stone
 * @Date 2023/3/9
 * @Desc
 */
@Named
public class I18nService implements II18nService {

  @Inject
  private II18nDao i18nDao;

  @Override
  public PagedResult<I18nVO> findPageResult(I18nVO i18nVO, PageVO pageVO) {
    return i18nDao.findPageResult(i18nVO, pageVO);
  }

  @Override
  public List<I18nVO> findListByLanguage(String language) {
    return i18nDao.findListByLanguage(language);
  }

  @Override
  public ResultVO batchOperateI18n(BatchVO batchVO) {
    List<I18nVO> createList = batchVO.getCreateItems();
    for (I18nVO i18nVO : createList) {
      this.createI18n(i18nVO);
    }
    List<I18nVO> updateList = batchVO.getUpdateItems();
    for (I18nVO i18nVO : updateList) {
      this.updateI18n(i18nVO);
    }
    List<I18nVO> deleteList = batchVO.getDeleteItems();
    for (I18nVO i18nVO : deleteList) {
      this.deleteI18n(i18nVO);
    }
    return new ResultVO();
  }

  @Override
  public ResultVO createI18n(I18nVO i18nVO) {
    i18nVO.setI18nId(UUIDUtil.getUuid());
    i18nDao.createI18n(i18nVO);
    return new ResultVO();
  }

  @Override
  public ResultVO updateI18n(I18nVO i18nVO) {
    i18nDao.updateI18n(i18nVO);
    return new ResultVO();
  }

  @Override
  public ResultVO deleteI18n(I18nVO i18nVO) {
    i18nDao.deleteI18n(i18nVO);
    return new ResultVO();
  }
}
