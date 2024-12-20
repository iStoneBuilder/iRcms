package com.stone.it.rcms.work.mifi.sim.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 卡商北纬公共部分
 * 
 * @author cj.stone
 * @Date 2024/12/20
 * @Desc
 */
public class BeiWeiBaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeiWeiBaseService.class);

    JSONObject buildBaseBody(String iccid) {
        JSONObject body = new JSONObject();
        body.put("cardId", iccid);
        body.put("cardType", "0");
        return body;
    }

    Map<String, String> buildHeader(JSONObject authInfo) {
        String timeStamp = DateUtil.formatDate("yyyyMMddHHmmss");
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("timestamp", timeStamp);
        header.put("appid", authInfo.getString("appKey"));
        header.put("sign", encryptMd5(authInfo.getString("appKey") + authInfo.getString("appSecret") + timeStamp));
        return header;
    }

    private String encryptMd5(String context) {
        try {
            // 获取一个MD5消息摘要实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 更新消息摘要，将输入的文本内容转换为字节数组并进行处理
            md.update(context.getBytes());
            // 计算消息摘要，得到MD5散列值
            byte[] entryContext = md.digest();
            int i;
            StringBuilder buf = new StringBuilder();
            for (byte b : entryContext) {
                // 将字节值转换为无符号整数
                i = b;
                if (i < 0) {
                    // 处理负值
                    i += 256;
                }
                if (i < 16) {
                    // 补充前导0，以保证每个字节都被表示为两位十六进制数
                    buf.append("0");
                }
                // 将字节值转换为十六进制字符串并追加到结果字符串
                buf.append(Integer.toHexString(i));
            }
            // 返回MD5散列值的十六进制表示
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("MD5加密失败", e);
            // 处理NoSuchAlgorithmException异常，通常是因为指定的MD5算法不可用
            return "";
        }
    }

}
