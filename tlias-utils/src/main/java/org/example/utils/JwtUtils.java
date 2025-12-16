package org.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT工具类，用于生成和解析JWT令牌
 */
public class JwtUtils {
    
    private static final String SECRET_KEY = "ZXhhbXBsZQ=="; // 密钥
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 12; // 12小时过期时间
    
    /**
     * 生成JWT令牌
     * 
     * @param claims 要存储在令牌中的数据
     * @return 生成的JWT令牌字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
    }
    
    /**
     * 解析JWT令牌
     * 
     * @param token 要解析的JWT令牌
     * @return 包含令牌中数据的Claims对象
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
