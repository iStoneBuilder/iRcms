package com.stone.it.micro.rcms.framedata.interceptor;

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
    return invocation.proceed();
  }


}
