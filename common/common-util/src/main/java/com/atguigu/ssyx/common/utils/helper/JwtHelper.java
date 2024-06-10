package com.atguigu.ssyx.common.utils.helper;


// 什么是token？装。按照一定规则生成登陆用户的唯一标识，对生成唯一标识进行编码加密处理
// JWT工具生成唯一标识token，进行编码加密处理

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;
import java.util.Date;

public class JwtHelper {

    private static long tokenExpiration = 365*24*60*60*1000;
    private static String tokenSignKey = "ssyx";

    public static String createToken(Long userId, String userName) {
        String token = Jwts.builder()
                .setSubject("ssyx-USER") // 分组
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)) // 过期时间
                .claim("userId", userId) // JWT中的私有部分：用户名和密码
                .claim("userName", userName)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey) // 字符串进行加密处理：加密方式、密钥
                .compressWith(CompressionCodecs.GZIP) // 压缩，生成在一行
                .compact();
        return token;
    }

    public static Long getUserId(String token) { // token字符串中得到用户ID
        if(StringUtils.isEmpty(token)) return null;

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);// 根据密钥进行解码
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer)claims.get("userId");
        return userId.longValue();
        // return 1L;
    }

    public static String getUserName(String token) {
        if(StringUtils.isEmpty(token)) return "";

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("userName");
    }

    public static void removeToken(String token) {
        //jwttoken无需删除，客户端扔掉即可。
    }

    public static void main(String[] args) { // 测试代码
        String token = JwtHelper.createToken(7L, "admin"); // 根据用户id、密码生成token
        System.out.println(token);
        System.out.println(JwtHelper.getUserId(token));
        System.out.println(JwtHelper.getUserName(token));
    }
}