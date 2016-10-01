package com.example.component;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.annotation.DemoAnno;

@Aspect
@Component
public class DemoInterceptor {

    @Around("@annotation(annotation)")
    public Object invoke(ProceedingJoinPoint proceedingJoinPoint, DemoAnno annotation) throws Throwable {
        System.out.println(proceedingJoinPoint.getArgs()[0]);
        Object returnVal = proceedingJoinPoint.proceed();
        System.out.println(annotation.value());
        return returnVal;
    }

}
