package me.egg_nuts.user_manager.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ParameterAop {
    @Pointcut("execution(* dev.bslee.spring_boot.controller..*.*(..))")
    private void cut(){

    }

    @Before("cut()")
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for(Object obj :args){
            System.out.println("type : "+obj.getClass().getSimpleName());
            System.out.println("value : "+obj);
        }

    }

    @AfterReturning(value = "cut()",returning = "responseEntity")
    public void afterReturn(JoinPoint joinPoint,Object responseEntity){
        System.out.println("responseEntity");
        System.out.println(responseEntity);
    }
}
