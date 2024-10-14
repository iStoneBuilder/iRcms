package com.stone.it.rcms.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cj.stone
 * @Date 2024/10/14
 * @Desc
 */
public class JwtUtils {

  private static String SING = "RCMS";

  /**
   * 根据输入的键值对Map生成JWT令牌
   * 该方法主要用于创建一个带有过期时间及指定算法签名的JWT令牌
   *
   * @param map 包含令牌payload数据的键值对Map，其中键为claim名称，值为claim值
   * @return 返回生成的JWT令牌字符串
   */
  public static String generateToken(Map<String, String> map) {
    // 获取当前时间并创建日历实例
    Calendar instance = Calendar.getInstance();
    // 设置令牌默认7天后过期
    instance.add(Calendar.SECOND, 60 * 5);
    // 创建JWT构建器
    JWTCreator.Builder builder = JWT.create();
    // 设置payload中的claim，使用传入的Map进行遍历设置
    map.forEach(builder::withClaim);
    // 生成带有过期时间的令牌，并使用HMAC256算法进行签名
    // 这里使用了HMAC256算法对令牌进行签名，确保其安全性和完整性
    return builder.withExpiresAt(instance.getTime())
      .sign(Algorithm.HMAC256(SING));
  }


  /**
   * 方法描述 -> 获取token信息方法
   *
   * @param token JWT令牌字符串
   */
  public static Map<String,Object> getTokenInfo(String token){
    // 验证token是否有效
    Map<String, Object> infoMap = new HashMap<>();
    DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    verify.getClaims().forEach((k, v) -> infoMap.put(k, v.asString()));
    infoMap.put("exp", verify.getExpiresAt());
    return infoMap;
  }

  /**
   * 验证token是否有效
   * @param token
   * @return
   */
  private static Map<String, Object> verifyToken(String token) {
    Map<String, Object> map = new HashMap<>();
    map.put("state", false);  // 设置默认状态
    try {
      // 验证令牌
      JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
      // 设置状态为true
      map.put("state", true);
      map.put("msg", "token有效");
    } catch (SignatureVerificationException e) {
      map.put("msg", "无效签名！");
    } catch (TokenExpiredException e) {
      map.put("msg", "token过期");
    } catch (AlgorithmMismatchException e) {
      map.put("msg", "算法不一致");
    } catch (Exception e) {
      map.put("msg", "token无效！");
    }
    return map;
  }


}

