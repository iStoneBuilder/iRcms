package com.stone.it.rcms.core.util;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * 随机字符串工具类
 * 
 * @author cj.stone
 * @Date 2024/11/2
 * @Desc
 */
public class RandomUtil {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String secretGenerator() {
        // 初始化安全随机数生成器
        SecureRandom random = new SecureRandom();
        // 设定secret的字节长度，例如32字节（256位）
        int secretBytes = 32;
        // 创建字节数组来存储随机生成的字节
        byte[] secretBytesArray = new byte[secretBytes];
        // 生成随机字节并填充到数组中
        random.nextBytes(secretBytesArray);
        // 使用Base64编码来转换字节为字符串形式
        return Base64.getEncoder().encodeToString(secretBytesArray);
    }

    public static String stringGenerator(int length) {
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            result.append(CHARACTERS.charAt(index));
        }
        return result.toString();
    }

}
