/**
 * Project Name:watchDog
 * File Name:TokenUtil.java
 * Package Name:com.jeeplus.common.security.tokenUtils
 * Date:2019年9月9日上午11:47:45
 * Copyright (c) 2019, http://www.rlctech.com/ All Rights Reserved.
 *
*/

package com.rlc.common.utils;

import java.util.Date;
import java.util.Map;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;

import net.sf.json.JSONObject;

/**
 * ClassName:TokenUtil <br/>
 * 令牌
 * Date:     2019年9月9日 上午11:47:45 <br/>
 * @author   RLC_ZYC
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class TokenUtil {
	static String secret = "XX#$%()(#*!()!KL<><MQLMNQNQJQK gsinnbhjndrow32234545fdf>?N<:{LWPW";
	 /**
     * 签发令牌
     * @param appId appId
     * @param ttlMillis 令牌有效期-分钟
     * @return  String
     *
     */
    public static String createToken(String appId, int ttlMillis) {
    	 String token = "";
    	 try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
//			final Map<String, Object> headerClaims = Maps.newHashMap();
//			headerClaims.put(key, value)
			token = JWT.create()
			        .withIssuer("rlc")
			        .withIssuedAt(new Date())
//			        .withHeader(headerClaims)
			        .withSubject(appId)
					.withExpiresAt(DateUtils.addMinutes(new Date(), ttlMillis))
			        .sign(algorithm);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (JWTCreationException e) {
			e.printStackTrace();
		}
    	 return token;
    }
    /**
     * @description: 签发令牌 有效期30min
     * @author rlc_zyc
     * @date 2021/4/9 10:33
     * @version 1.0
     */
	public static String createToken(String appId) {
		String token = "";
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
//			final Map<String, Object> headerClaims = Maps.newHashMap();
//			headerClaims.put(key, value)
			token = JWT.create()
					.withIssuer("rlc")
					.withIssuedAt(new Date())
//			        .withHeader(headerClaims)
					.withSubject(appId)
					.withExpiresAt(DateUtils.addMinutes(new Date(), 30))
					.sign(algorithm);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (JWTCreationException e) {
			e.printStackTrace();
		}
		return token;
	}
	/**
     * 验证token
     * @param token
     * @return Map<String,Object> key ---success: true(成功)/false(失败);data: 0(成功)/1(失败--令牌过期)/2(失败--无效令牌);message:提示语;
     *                                   当success=true时 会有markData--设备id
     */
    public static Map<String,Object> validateToken(String token) {
    	Map<String,Object> resultMap = Maps.newHashMap();
    	try {
    	    Algorithm algorithm = Algorithm.HMAC256(secret);
    	    JWTVerifier verifier = JWT.require(algorithm)
    	        .withIssuer("rlc")
    	        .build(); //Reusable verifier instance
    	    DecodedJWT jwt = verifier.verify(token);
    	    String payload = jwt.getPayload();
    	    String payloadE = Encodes.decodeBase64String(payload);
//    	    payload = Base64Util.getBase64(payload);
    	    if(StringUtils.isNotBlank(payloadE)) {
    	    	JSONObject payloadJSONObject = JSONObject.fromObject(payloadE);
    	    	if(payloadJSONObject.containsKey("sub")) {
    	    		String markData = String.valueOf(payloadJSONObject.get("sub"));
    	    		resultMap.put("markData", markData);
    	    	}
    	    }
    	    Date date = jwt.getExpiresAt();
    	    System.out.println("---"+DateUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss")+" ____:"+payload);
    	    resultMap.put("success", true);
    	    resultMap.put("data", 0);
    	    resultMap.put("message", "成功");
    	}catch (TokenExpiredException e) {
    		resultMap.put("success", false);
    	    resultMap.put("data", 1);
    	    resultMap.put("message", "令牌过期");
		} catch (JWTVerificationException exception){
    		resultMap.put("success", false);
    	    resultMap.put("data", 2);
    	    resultMap.put("message", "无效令牌");
    	}
    	return resultMap;
    }
    public static void main(String[] args) {
//		String t = createToken("123", 1L);
//		System.out.println("令牌："+t);
		String tt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJybGMiLCJleHAiOjE1NjgwMTc5MzUsImlhdCI6MTU2ODAxNzg3NX0.Q8X67KZpg8sKgsF8TUrXKcKI65eMZNu-TXkufvE0O-Y";
		Map<String,Object> resultMap = validateToken(tt);
		System.out.println("验证："+resultMap.get("message"));
	}
}

