package org.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    // 生成JWT令牌
    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");
        String jwt=Jwts.builder().signWith(SignatureAlgorithm.HS256, "ZXhhbXBsZQ==")
                .addClaims(dataMap) // 添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // ms单位，设置过期时间为7天
                .compact();
        System.out.println(jwt);
    }

    // 解析JWT令牌
    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc2NTgwMTg5Mn0.AidYdYzOxuAdobVQAPb5N36JC1ea6mCxrVzZJ1t5nuQ";
        Claims claims=Jwts.parser()
                .setSigningKey("ZXhhbXBsZQ==") // 设置密钥
                .parseClaimsJws(token)// 解析JWT
                .getBody(); // 获取自定义数据
        System.out.println(claims);
    }
}
