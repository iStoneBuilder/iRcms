package com.stone.it.micro.rcms.core.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author iMrJi
 * @Description TODO
 * @Date 2022/8/31 9:02 PM
 * @Version 1.0
 */
public class JwtTokenOutInterceptor extends AbstractPhaseInterceptor<Message> {

  private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenOutInterceptor.class);

  public JwtTokenOutInterceptor() {
    // 这儿使用pre_stream，意思为在流关闭之前
    super(Phase.PRE_STREAM);
  }

  @Override
  public void handleMessage(Message message) throws Fault {
    LOGGER.info("CXF Interceptor Out ........");
  }
}
