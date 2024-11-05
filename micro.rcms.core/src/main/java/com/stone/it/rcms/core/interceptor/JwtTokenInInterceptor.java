package com.stone.it.rcms.core.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 入口拦截器 JwtToken认证拦截，需要接口入口配置
 *
 * @Author iMrJi
 * @Description TODO
 * @Version 1.0
 */
public class JwtTokenInInterceptor extends AbstractPhaseInterceptor<Message> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenInInterceptor.class);

    public JwtTokenInInterceptor() {
        super(Phase.RECEIVE);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        LOGGER.info("CXF Interceptor In ...........");
    }
}
