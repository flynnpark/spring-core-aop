package dev.flynnpark.springcoreaop.proxyBattle.code;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class ProxyDiAspect {
    @Before("execution(* dev.flynnpark.springcoreaop..*.*(..))")
    public void doTrace(JoinPoint joinPoint) {
        log.info("[proxyDiAdvice] {}", joinPoint.getSignature());
    }
}
