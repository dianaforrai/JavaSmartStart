package com.glo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.glo.service.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        System.out.println("[BEFORE] Entering method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.glo.service.*.*(..))", returning = "result")
    public void logMethodSuccess(JoinPoint joinPoint, Object result) {
        System.out.println("[AFTER RETURNING] Successfully executed: " + joinPoint.getSignature().getName() +
                ", Result: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.glo.service.*.*(..))", throwing = "ex")
    public void logMethodException(JoinPoint joinPoint, Throwable ex) {
        System.out.println("[AFTER THROWING] Exception in method: " + joinPoint.getSignature().getName() +
                ", Exception: " + ex.getMessage());
    }
}
