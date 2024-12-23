package com.stone.it.rcms.mifi.sim.beiwei;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.http.ResponseEntity;
import com.stone.it.rcms.core.util.DateUtil;
import com.stone.it.rcms.mifi.sim.vo.CarrierVO;
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

    static JSONObject getSimResBody(JSONObject resBody) {
        if (resBody.containsKey("respStatus") && "0000".equals(resBody.getJSONObject("respStatus").getString("code"))) {
            return resBody.getJSONObject("respBody");
        }
        return null;
    }

    static JSONObject buildBaseBody(String iccid) {
        JSONObject body = new JSONObject();
        body.put("cardId", iccid);
        body.put("cardType", "0");
        return body;
    }

    static Map<String, String> buildHeader(CarrierVO carrierVO) {
        String timeStamp = DateUtil.formatDate("yyyyMMddHHmmss");
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("timestamp", timeStamp);
        header.put("appid", carrierVO.getAppKey());
        header.put("sign", encryptMd5(carrierVO.getAppKey() + carrierVO.getAppSecret() + timeStamp));
        return header;
    }

    private static String encryptMd5(String context) {
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
