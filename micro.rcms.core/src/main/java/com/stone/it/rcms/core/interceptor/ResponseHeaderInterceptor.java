package com.stone.it.rcms.core.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cj.stone
 * @Date 2024/11/5
 * @Desc
 */
public class ResponseHeaderInterceptor extends AbstractPhaseInterceptor<Message> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseHeaderInterceptor.class);

    public ResponseHeaderInterceptor() {
        // 这儿使用pre_stream，意思为在流关闭之前
        super(Phase.PRE_PROTOCOL);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        LOGGER.info("Response Header Interceptor ........");

    }
}
