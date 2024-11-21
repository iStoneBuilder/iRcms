package com.stone.it.rcms.auth.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cj.stone
 * @Date 2024/11/21
 * @Desc
 */
public class RequestGlobalFilter extends PathMatchingFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestGlobalFilter.class);

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
        throws Exception {
        // 在这里添加全局过滤逻辑
        // 例如：日志记录、性能监控等
        LOGGER.info("****** Shiro RequestGlobalFilter onPreHandle...");
        // 返回true表示继续处理请求，返回false表示中断请求
        return true;
    }
}
