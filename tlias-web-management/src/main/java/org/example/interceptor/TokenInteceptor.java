package org.example.interceptor;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.CurrentHolder;
import org.example.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class TokenInteceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 1. 获取请求路径
//        String path = request.getRequestURI(); // /employee/login
//        // 2. 判断请求路径是否为登录路径
//        if (path.contains("login")) {
//            log.info("登录请求,放行");
//            return true;
//        }

        // 3. 获取请求头中的令牌
        String token = request.getHeader("token");
        // 4. 判断令牌是否存在
        if (token == null || token.isEmpty()) {
            log.info("令牌不存在,响应401");
            response.setStatus(401);
            return false;
        }

        // 5. 校验令牌，校验失败，返回401
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer empId = (Integer) claims.get("id");
            CurrentHolder.setCurrentLocal(empId);
            log.info("当前用户id为：{}, 将其存入ThreadLocal", empId);
        } catch (Exception e) {
            log.info("令牌校验失败,响应401");
            response.setStatus(401);
            return false;
        }

        // 6. 校验通过，放行
        log.info("令牌校验通过,放行");
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        CurrentHolder.remove();
        log.info("清理ThreadLocal");
    }
}
