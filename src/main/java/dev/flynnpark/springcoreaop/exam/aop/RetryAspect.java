package dev.flynnpark.springcoreaop.exam.aop;

import dev.flynnpark.springcoreaop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Objects;

@Slf4j
@Aspect
public class RetryAspect {
    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[retry] {}, retry={}", joinPoint.getSignature(), retry);
        int maxRetry = retry.value();
        Exception exceptionHolder = null;
        for (int retryCount = 1; retryCount <= maxRetry; retryCount++) {
            log.info("[retry] retryCount={}/{}", retryCount, maxRetry);
            try {
                return joinPoint.proceed();
            } catch (Exception e) {
                exceptionHolder = e;
            }
        }
        throw Objects.requireNonNull(exceptionHolder);
    }
}
