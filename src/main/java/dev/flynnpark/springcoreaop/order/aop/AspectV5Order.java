package dev.flynnpark.springcoreaop.order.aop;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
public class AspectV5Order {
    @Aspect
    @Order(2)
    public static class LogAspect {
        @Around("dev.flynnpark.springcoreaop.order.aop.Pointcuts.allOrder()")
        public Object doLog(org.aspectj.lang.ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1)
    public static class TransactionAspect {
        @Around("dev.flynnpark.springcoreaop.order.aop.Pointcuts.allOrder()")
        public Object doTrasnaction(org.aspectj.lang.ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[트랜잭션 종료] {}", joinPoint.getSignature());
                return result;
            } catch (Exception e) {
                log.info("[트랜잭션 예외] {}", joinPoint.getSignature());
                throw e;
            } finally {
                log.info("[트랜잭션 종료] {}", joinPoint.getSignature());
            }
        }
    }
}
