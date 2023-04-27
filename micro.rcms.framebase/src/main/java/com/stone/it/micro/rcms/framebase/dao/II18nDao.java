package com.stone.it.micro.rcms.framebase.dao;

import com.stone.it.micro.rcms.framebase.vo.I18nVO;
import com.stone.it.micro.rcms.framecore.vo.BatchVO;
import com.stone.it.micro.rcms.framecore.vo.PageVO;
import com.stone.it.micro.rcms.framecore.vo.PagedResult;
import java.util.List;

/**
 * @author cj.stone
 * @Date 2023/3/9
 * @Desc
 */
public interface II18nDao {

  PagedResult<I18nVO> findPageResult(I18nVO i18nVO,
      PageVO pageVO);

  List<I18nVO> findListByLanguage(String language);

  int batchOperateI18n(BatchVO batchVO);

  int createI18n(I18nVO i18nVO);

  int updateI18n(I18nVO i18nVO);

  int deleteI18n(I18nVO i18nVO);

}
