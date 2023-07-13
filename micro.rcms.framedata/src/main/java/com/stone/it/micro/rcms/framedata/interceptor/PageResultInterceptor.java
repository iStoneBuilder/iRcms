package com.stone.it.micro.rcms.framedata.interceptor;

import com.stone.it.micro.rcms.framecore.vo.PageResult;
import com.stone.it.micro.rcms.framecore.vo.PageVO;
import com.stone.it.micro.rcms.framedata.utils.PageUtil;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

/**
 *
 * @author cj.stone
 * @Date 2023/7/13
 * @Desc
 */
@Intercepts({@Signature(args = { Statement.class }, method = "handleResultSets", type = ResultSetHandler.class)})
public class PageResultInterceptor implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    //  请求所有参数
    // final Object params = invocation.getTarget();
    // 分页参数
    // PageVO pageVO = PageUtil.getPageRequestParams(params);
    // 存在分页参数处理结果集
    // if(pageVO != null){
      // 创建返回实体
     // return new PageResult(pageVO, (List) invocation.proceed());
    // }
    return invocation.proceed();
  }

  @Override
  public Object plugin(Object target) {
    return Interceptor.super.plugin(target);
  }

  @Override
  public void setProperties(Properties properties) {
    Interceptor.super.setProperties(properties);
  }
}
