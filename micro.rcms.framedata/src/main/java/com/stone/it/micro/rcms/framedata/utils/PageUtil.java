package com.stone.it.micro.rcms.framedata.utils;

import com.stone.it.micro.rcms.framecore.vo.PageVO;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.ibatis.binding.MapperMethod.ParamMap;

/**
 *
 * @author cj.stone
 * @Date 2023/7/13
 * @Desc
 */
public class PageUtil {
  public static PageVO getPageRequestParams(Object param) {
    if(param == null){
      return null;
    }
    if(PageVO.class .isAssignableFrom(param.getClass())){
      return handlePageQuery((PageVO) param);
    }
    if(param instanceof ParamMap){
      ParamMap paramMap = (ParamMap) param;
      Iterator iterator = paramMap.entrySet().iterator();
      while (iterator.hasNext()){
        Map.Entry entry = (Entry) iterator.next();
        Object object = entry.getValue();
        if(object!=null && PageVO.class.isAssignableFrom(object.getClass())){
          return handlePageQuery((PageVO) object);
        }
      }
    }
    return null;
  }
  private static PageVO handlePageQuery(PageVO pageVO) {
    if(pageVO.getCurPage()==0){
      pageVO.setStartIndex(0);
    }else{
      pageVO.setStartIndex((pageVO.getCurPage() -1)*pageVO.getPageSize());
    }
    pageVO.setEndIndex(pageVO.getPageSize());
    return pageVO;
  }
}
