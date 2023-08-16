package com.stone.it.rcms.orm.interceptor;

import com.stone.it.rcms.com.vo.PageResult;
import com.stone.it.rcms.com.vo.PageVO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mybatis分页插件
 *
 * @author cj.stone
 * @Date 2023/4/26
 * @Desc
 */
@Intercepts({
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
})
public class PageHelperInterceptor implements Interceptor {

  private static final Logger LOGGER = LoggerFactory.getLogger(PageHelperInterceptor.class);

  private final static String SQL_ID_SUFFIX = "Count";

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    final Object[] iArgs = invocation.getArgs();
    final MappedStatement mappedStatement = (MappedStatement) iArgs[0];
    //  请求所有参数
    final Object params = iArgs[1];
    // 获取分页参数
    PageVO pageVO = getPageRequestParams(mappedStatement,params);
    // 不包含分页参数获取本来就是查询总数 -> 跳过
    if(pageVO ==null){
      return invocation.proceed();
    }
    LOGGER.info("PageHelperInterceptor start ... ");
    // 设置新的分页参数
    buildPageQuery(params,pageVO);
    // 查询分页数据
    PageResult pageResult = getQueryResultData(invocation,iArgs,mappedStatement,params,pageVO);
    // 判断是否分页查询接口
    List<PageResult> resultList = new ArrayList<>();
    resultList.add(pageResult);
    return resultList;
  }

  private PageResult getQueryResultData(Invocation invocation, Object[] iArgs, MappedStatement mappedStatement, Object params, PageVO pageVO) {
    // 查询总数信息
    String sqlCountId = mappedStatement.getId() + "Count";
    int totalSize = 1000;
    // 设置总数
    pageVO.setTotalRows(totalSize);
    // 设置总页数
    pageVO.setTotalPage((int) Math.ceil(totalSize/pageVO.getPageSize()));
    pageVO.setStartIndex(0);
    pageVO.setEndIndex(0);
    PageResult pageResult = new PageResult(pageVO,new ArrayList());
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

  public static PageVO getPageRequestParams(MappedStatement mappedStatement,Object param) {
    if(mappedStatement.getId().endsWith(SQL_ID_SUFFIX) || param == null){
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
    // 设置分页 开始，结束 参数
    if(pageVO.getCurPage()==0){
      pageVO.setStartIndex(0);
    }else{
      pageVO.setStartIndex((pageVO.getCurPage() -1)*pageVO.getPageSize());
    }
    pageVO.setEndIndex(pageVO.getPageSize());
    return pageVO;
  }

  private MappedStatement newMappedStatement (MappedStatement ms, SqlSource newSqlSource,String sqlId) {
    MappedStatement.Builder builder = new
        MappedStatement.Builder(ms.getConfiguration(), sqlId, newSqlSource, ms.getSqlCommandType());
    builder.resource(ms.getResource());
    builder.fetchSize(ms.getFetchSize());
    builder.statementType(ms.getStatementType());
    builder.keyGenerator(ms.getKeyGenerator());
    if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
      builder.keyProperty(ms.getKeyProperties()[0]);
    }
    builder.timeout(ms.getTimeout());
    builder.parameterMap(ms.getParameterMap());
    builder.resultMaps(ms.getResultMaps());
    builder.resultSetType(ms.getResultSetType());
    builder.cache(ms.getCache());
    builder.flushCacheRequired(ms.isFlushCacheRequired());
    builder.useCache(ms.isUseCache());
    return builder.build();
  }

}
