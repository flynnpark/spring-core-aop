package dev.flynnpark.springcoreaop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {
//    @Around("dev.flynnpark.springcoreaop.order.aop.Pointcuts.allOrder()")
//    public Object doLog(org.aspectj.lang.ProceedingJoinPoint joinPoint) throws Throwable {
//        log.info("[log] {}", joinPoint.getSignature());
//        return joinPoint.proceed();
//    }

//    @Around("dev.flynnpark.springcoreaop.order.aop.Pointcuts.orderAndService()")
//    public Object doTrasnaction(org.aspectj.lang.ProceedingJoinPoint joinPoint) throws Throwable {
//        try {
//            // @Before
//            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
//            Object result = joinPoint.proceed();
//            // @AfterReturning
//            log.info("[트랜잭션 종료] {}", joinPoint.getSignature());
//            return result;
//        } catch (Exception e) {
//            // @AfterThrowing
//            log.info("[트랜잭션 예외] {}", joinPoint.getSignature());
//            throw e;
//        } finally {
//            // @After
//            log.info("[트랜잭션 종료] {}", joinPoint.getSignature());
//        }
//    }

    @Before("dev.flynnpark.springcoreaop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[Before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "dev.flynnpark.springcoreaop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("[AfterReturning] {}, result: {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "dev.flynnpark.springcoreaop.order.aop.Pointcuts.orderAndService()", throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint, Exception exception) {
        log.info("[AfterThrowing] {}, exception: {}", joinPoint.getSignature(), exception.getMessage());
    }

    @After("dev.flynnpark.springcoreaop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[After] {}", joinPoint.getSignature());
    }
}
