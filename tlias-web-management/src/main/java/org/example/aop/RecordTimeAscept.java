package org.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.utils.CurrentHolder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Slf4j
//@Order(1)  // 设置同一种通知类型的不同通知方法的优先级，数字越小优先级越高
@Aspect // AOP类
@Component
public class RecordTimeAscept {
    @Pointcut("execution(* org.example.service.impl.*.*(..))")
    public void pt(){}

    // 切面=切入点+通知
    @Around("pt()")  //切入点表达式，实际被AOP控制的方法
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable{
        long begin=System.currentTimeMillis();
        Object result=pjp.proceed();
        long end=System.currentTimeMillis();
        log.info("方法{}执行耗时:{}ms", pjp.getSignature(),end-begin);
        log.info("当前登录的用户ID:{}", CurrentHolder.getCurrentLocal());
        return result;
    }
}
