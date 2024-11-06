package com.stone.it.rcms.core.interceptor;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.exception.RcmsApplicationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.InterceptorChain;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 需要接口出口配置
 *
 * @Author iMrJi
 * @Description TODO
 * @Version 1.0
 */
public class ResponseBodyInterceptor extends AbstractPhaseInterceptor<Message> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseBodyInterceptor.class);

    public ResponseBodyInterceptor() {
        // 这儿使用pre_stream，意思为在流关闭之前
        super(Phase.PRE_STREAM);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        LOGGER.info("Response Body Interceptor ........");
        // 获取响应输出流
        OutputStream os = message.getContent(OutputStream.class);
        if (os != null) {
            // 创建一个新输出流来捕获响应内容
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            message.setContent(OutputStream.class, baos);
            // 继续处理消息
            message.getInterceptorChain().doIntercept(message);
            // 获取原始响应内容
            String originalResponse = baos.toString();
            // 将修改后的内容写回输出流
            try {
                JSONObject responseJson = new JSONObject();
                if (originalResponse.startsWith("{") && originalResponse.endsWith("}")) {
                    JSONObject originalResJson = JSONObject.parseObject(originalResponse);
                    if (!originalResJson.containsKey("success")) {
                        if (originalResJson.containsKey("code")) {
                            int code = originalResJson.getIntValue("code");
                            if (code == 200) {
                                responseJson.put("success", true);
                            } else {
                                responseJson.put("success", false);
                            }
                        } else {
                            responseJson.put("success", true);
                        }
                    }
                    responseJson.put("data", originalResJson);
                } else if (originalResponse.startsWith("[") && originalResponse.endsWith("]")) {
                    JSONArray originalResJson = JSONArray.parseArray(originalResponse);
                    responseJson.put("success", true);
                    responseJson.put("data", originalResJson);
                } else {
                    responseJson.put("success", true);
                    responseJson.put("data", originalResponse);
                }
                os.write(JSONObject.toJSONString(responseJson).getBytes());
            } catch (Exception e) {
                LOGGER.error("ResponseBodyInterceptor error", e);
            } finally {
                try {
                    os.close();
                } catch (Exception ex) {
                    LOGGER.error("ResponseBodyInterceptor error", ex);
                }
            }
        }
    }
}
