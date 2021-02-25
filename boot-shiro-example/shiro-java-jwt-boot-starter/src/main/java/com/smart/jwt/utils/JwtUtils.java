package com.smart.jwt.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class JwtUtils {

    public static String create() {
        Calendar instance = Calendar.getInstance();
        // 默认20秒过期  50秒过期 (测试用)
        instance.add(Calendar.SECOND, 1);

        HashMap<String, Object> headMap = new HashMap<>();

        return JWT.create()
                // 一 自定义请求头信息
                .withHeader(headMap)
                // 二 自定义载体信息
                // playload
                .withClaim("username", "admin")
                .withClaim("userId", 1)
                //过期时间
                .withExpiresAt(instance.getTime())
                //=========可选===========
                // 1.jwt所面向的用户
                .withSubject("")
                // 2. 定义在什么时间之前，该jwt都是不可用的
//                .withNotBefore()
                //3. 签发者
                .withIssuer("")
                //4.jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
                .withJWTId("")
                //5.接收jwt的一方
                .withAudience()
                // 签发时间
                .withIssuedAt(new Date())
                //三 签名 指定加密的算法以及秘钥
                .sign(Algorithm.HMAC256("123446"));
    }
    //AlgorithmMismatchException 签名算法不一致
    // TokenExpiredException token过期
    //SignatureVerificationException 无效签名
    // JWTDecodeException 解密Token异常

    public static DecodedJWT verify(String token) throws TokenExpiredException {
        return JWT.require(Algorithm.HMAC256("123446")).build().verify(token);
    }

    public static void main(String[] args) {
        String token = create();
        try {
            verify(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


