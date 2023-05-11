package com.stone.it.micro.rcms.framebase.service.impl;

import com.alibaba.fastjson.JSON;
import com.stone.it.micro.rcms.framebase.dao.II18nDao;
import com.stone.it.micro.rcms.framebase.service.II18nService;
import com.stone.it.micro.rcms.framebase.vo.I18nVO;
import com.stone.it.micro.rcms.framecore.util.UUIDUtil;
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
  public ResultVO findListByLanguage(String lang) {
    return new ResultVO(i18nDao.findListByLanguage(lang));
  }

  @Override
  public ResultVO findI18nById(String i18nId) {
    return new ResultVO(i18nDao.findI18nById(i18nId));
  }

  @Override
  public ResultVO createI18n(I18nVO i18nVO) {
    i18nVO.setI18nId(UUIDUtil.getUuid());
    i18nDao.createI18n(i18nVO);
    return new ResultVO(i18nVO);
  }

  @Override
  public ResultVO updateI18n(String i18nId, I18nVO i18nVO) {
    i18nVO.setI18nId(i18nId);
    i18nDao.updateI18n(i18nVO);
    return new ResultVO(i18nVO);
  }

  @Override
  public ResultVO deleteI18n(String i18nId) {
    i18nDao.deleteI18n(i18nId);
    return new ResultVO();
  }
}
