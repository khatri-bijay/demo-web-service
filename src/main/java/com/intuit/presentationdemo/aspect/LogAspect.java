package com.intuit.presentationdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Before("execution(* com.intuit.presentationdemo.controller.*.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info("Starting invocation for {}", joinPoint);
    }

    @After("execution(* com.intuit.presentationdemo.controller.*.*(..))")
    public void after(JoinPoint joinPoint) {
        log.info("Finishing invocation for {}", joinPoint);
    }
}
