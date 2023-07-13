package com.stone.it.micro.rcms.framedata.interceptor;

import com.stone.it.micro.rcms.framecore.vo.PageResult;
import com.stone.it.micro.rcms.framecore.vo.PageVO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Mybatis 分页插件
 *
 * @author cj.stone
 * @Date 2023/4/26
 * @Desc
 */
@Intercepts({
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
})
public class PageHelperInterceptor implements Interceptor {

  private static final Logger LOGGER = LoggerFactory.getLogger(PageHelperInterceptor.class);

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    LOGGER.info("PageHelperInterceptor start ... ");
    final Object[] iArgs = invocation.getArgs();
    final MappedStatement mappedStatement = (MappedStatement) iArgs[0];
    //  请求所有参数
    final Object params = iArgs[1];
    // 获取分页参数
    PageVO pageVO = getPageRequestParams(params);
    // 不包含分页参数跳过
    if(pageVO ==null){
      return invocation.proceed();
    }
    // 设置新的分页参数
    buildPageQuery(params,pageVO);
    // 执行查询数据
    Object queryData = invocation.proceed();
    // 查询总数信息
    String sqlId = mappedStatement.getId() + "Count";
    // 创建返回实体
    PageResult pageResult = new PageResult(pageVO, (List) queryData);
    // 判断是否分页查询接口
    return pageResult;
  }


  private void buildPageQuery(Object params,PageVO pageVO) {
    if(params != null && params instanceof ParamMap){
      ParamMap paramMap = (ParamMap) params;
      Iterator iterator = paramMap.entrySet().iterator();
      while (iterator.hasNext()){
        Map.Entry entry = (Entry) iterator.next();
        Object object = entry.getValue();
        if(object!=null && PageVO.class.isAssignableFrom(object.getClass())){
          paramMap.replace(entry.getKey(), pageVO);
        }
      }
    }
  }

  /**
   * 获取分页参数
   * @param param
   * @return
   */
  private PageVO getPageRequestParams(Object param) {
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

  private PageVO handlePageQuery(PageVO pageVO) {
    if(pageVO.getCurPage()==0){
      pageVO.setStartIndex(0);
    }else{
      pageVO.setStartIndex((pageVO.getCurPage() -1)*pageVO.getPageSize());
    }
    pageVO.setEndIndex(pageVO.getPageSize());
    return pageVO;
  }


}
